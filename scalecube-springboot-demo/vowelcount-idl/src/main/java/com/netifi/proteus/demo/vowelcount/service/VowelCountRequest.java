package com.netifi.proteus.demo.vowelcount.service;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newBuilder")
public class VowelCountRequest {

    private String message;
}
