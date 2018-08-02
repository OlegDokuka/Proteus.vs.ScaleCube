package com.netifi.proteus.demo.isvowel.service;

import io.scalecube.services.annotations.Service;
import reactor.core.publisher.Mono;

@Service("IsVowelService")
public interface IsVowelServiceClient {

    Mono<IsVowelResponse> isVowel(IsVowelRequest request);
}
