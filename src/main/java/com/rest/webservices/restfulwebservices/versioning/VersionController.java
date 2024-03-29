package com.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping(value="/person/param",params="version=1")
    public PersonV1 personV1(){
        return new PersonV1("Anitha");
    }
    @GetMapping(value="/person/param",params="version=2")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Anitha","subbu"));
    }
    @GetMapping(value="/person/header",headers="X-API-VERSION=1")
    public PersonV1 headerV1(){
        return new PersonV1("Anitha");
    }
    @GetMapping(value="/person/header",headers="X-API-VERSION=2")
    public PersonV2 headerV2(){
        return new PersonV2(new Name("Anitha","subbu"));
    }
    @GetMapping(value="/person/produces",produces="application/v1+json")
    public PersonV1 producesV1(){
        return new PersonV1("Anitha");
    }
    @GetMapping(value="/person/produces",produces="application/v2+json")
    public PersonV2 producesV2(){
        return new PersonV2(new Name("Anitha","subbu"));
    }
}
