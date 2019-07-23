package com.rest.webservices.restfulwebservices.helloworld;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class HelloWorldBean {
    private String message;
}
