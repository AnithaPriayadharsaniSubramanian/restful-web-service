package com.rest.webservices.restfulwebservices.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2,message = "Name should min be 2 chars  ")
    private String  name;
    @Past
    private Date birthDate;
    @OneToMany(mappedBy = "user")
    private List<Post> post;
    public User(Integer id, String name, Date birthDate) {
    }
}
