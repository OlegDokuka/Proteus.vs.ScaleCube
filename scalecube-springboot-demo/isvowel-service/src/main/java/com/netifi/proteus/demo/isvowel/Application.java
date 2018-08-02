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
package com.netifi.proteus.demo.isvowel;

import java.io.IOException;

import com.netifi.proteus.demo.isvowel.service.DefaultIsVowelService;
import io.scalecube.services.Microservices;
import io.scalecube.transport.Address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableProteus(group = "com.netifi.proteus.demo.isvowel")
public class Application {

    public static void main(String... args) throws IOException {

       Microservices
                .builder()
                .discoveryPort(8081)
                .servicePort(4801)
                .seeds(
                    Address.create("localhost", 8082),
                    Address.create("localhost", 8080)
                )
                .services(new DefaultIsVowelService())
                .start();


        SpringApplication.run(Application.class, args);

       System.in.read();
    }
}