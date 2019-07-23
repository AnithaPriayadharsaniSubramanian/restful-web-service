package com.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    //retriveAllUsr
    //retrieveone
    //addUsr
    @Autowired
    private UserDAOService userDAOService;

    @GetMapping(value = "/users")
    public List<User> retrieveAllUsers(){
        return userDAOService.findAll();

    }

    @GetMapping(value = "/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id){
        User user =  userDAOService.findOne(id);
        if(user==null){
            throw new UserNotFoundException("id-"+id);
        }

        EntityModel<User> model = new EntityModel(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));
        return model;

    }
   @PostMapping(value = "/users")

   public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDAOService.saveUser(user);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
       return ResponseEntity.created(location).build();

    }
    @PostMapping(value = "/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user,@PathVariable int id){
        User updatedUser = userDAOService.updateUser(user,id);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @DeleteMapping(value = "/users/{id}")
    public void  deleteUser(@PathVariable int id){
        User deleteUser = userDAOService.deleteUser(id);

        if(deleteUser==null){
            throw new UserNotFoundException("id=="+id);
        }
    }



}
