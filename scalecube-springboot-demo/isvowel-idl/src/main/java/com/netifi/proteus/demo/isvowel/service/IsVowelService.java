package com.netifi.proteus.demo.isvowel.service;

import io.scalecube.services.annotations.Service;
import io.scalecube.services.annotations.ServiceMethod;
import reactor.core.publisher.Mono;

@Service("IsVowelService")
public interface IsVowelService {

    @ServiceMethod
    Mono<IsVowelResponse> isVowel(IsVowelRequest request);
}
