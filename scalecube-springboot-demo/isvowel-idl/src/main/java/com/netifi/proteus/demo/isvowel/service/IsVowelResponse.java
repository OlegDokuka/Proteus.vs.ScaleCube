package com.netifi.proteus.demo.isvowel.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newBuilder")
public class IsVowelResponse {

    private Boolean isVowel;
}
