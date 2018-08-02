/**
 * Copyright 2018 Netifi Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netifi.proteus.demo.vowelcount;

import java.io.IOException;
import java.util.Collections;

import com.netifi.proteus.demo.vowelcount.service.DefaultVowelCountService;
import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableProteus(group = "com.netifi.proteus.demo.vowelcount")
public class Application {

    public static void main(String... args) throws IOException {
        SpringApplication.run(Application.class, args);

        Microservices.builder()
                     .services(c -> Collections.singleton(new DefaultVowelCountService(c)))
                     .discoveryPort(4802)
                     .servicePort(8082)
                     .seeds(
                             Address.create("192.168.1.91", 8080),
                             Address.create("192.168.1.91", 4800)
                     )
                     .start();

        System.in.read();
    }
}
