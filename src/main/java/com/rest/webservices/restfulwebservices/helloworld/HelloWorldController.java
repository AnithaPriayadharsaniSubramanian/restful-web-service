package com.rest.webservices.restfulwebservices.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET,path = "/hello-world")
    public String helloWorld(){
        return "Hello World";
    }
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("HelloWorldbean1");
    }
    @GetMapping("/hello-world-internationlised")
    public String helloWorldInternationalised(){
        return messageSource.getMessage("morning.message",null, LocaleContextHolder.getLocale());
    }
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("HelloWorld,%s",name));

    }


}
