package com.netifi.proteus.demo.vowelcount.service;

import io.scalecube.services.annotations.Service;
import io.scalecube.services.annotations.ServiceMethod;
import reactor.core.publisher.Flux;

@Service("VowelCountService")
public interface VowelCountServiceClient {

    @ServiceMethod
    Flux<VowelCountResponse> countVowels(Flux<VowelCountRequest> request);
}
