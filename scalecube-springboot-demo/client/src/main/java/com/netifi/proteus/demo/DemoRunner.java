/**
 * Copyright 2018 Netifi Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netifi.proteus.demo;

import com.netifi.proteus.demo.core.RandomString;
import com.netifi.proteus.demo.vowelcount.service.VowelCountRequest;
import com.netifi.proteus.demo.vowelcount.service.VowelCountServiceClient;
import io.scalecube.services.Microservices;
import io.scalecube.services.ServiceCall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DemoRunner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoRunner.class);

    @Autowired
    private RandomString randomString;


    @Override
    public void run(String... args) throws Exception {

        Microservices microservices = Microservices.builder()
                                                   .servicePort(8080)
                                                   .discoveryPort(4800)
                                                   .startAwait();
        CountDownLatch latch = new CountDownLatch(1);
        VowelCountServiceClient client =
                microservices.call().create().api(VowelCountServiceClient.class);

        System.in.read();

        // Generate stream of random strings
        Flux<VowelCountRequest> requests = Flux.range(1, 100)
                .map(cnt -> VowelCountRequest.newBuilder()
                        .message(randomString.next(10, ThreadLocalRandom.current()))
                        .build());

        // Send stream of random strings to vowel count service
        client.countVowels(requests)
                .onBackpressureDrop()
                .doOnComplete(latch::countDown)
                .subscribe(response -> {
                    LOGGER.info("Total Vowels: {}", response.getVowelCnt());
                });

        latch.await();
    }
}
