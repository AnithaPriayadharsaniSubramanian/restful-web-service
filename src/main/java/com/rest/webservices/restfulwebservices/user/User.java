package com.rest.webservices.restfulwebservices.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    @Size(min=2,message = "Name should min be 2 chars  ")
    private String  name;
    @Past
    private Date birthDate;
}
