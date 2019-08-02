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
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {

    //retriveAllUsr
    //retrieveone
    //addUsr

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping(value = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();

    }

    @GetMapping(value = "/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id){
        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }

        EntityModel<User> model = new EntityModel(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));
        return model;

    }
   @PostMapping(value = "/jpa/users")

   public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
       return ResponseEntity.created(location).build();

    }
    /*@PostMapping(value = "/jpa/users/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody User user,@PathVariable int id){
        User updatedUser = userRepository.s(user,id);
        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedUser.getId()).toUri();
        return ResponseEntity.created(location).build();

    }*/

    @DeleteMapping(value = "/jpa/users/{id}")
    public void  deleteUser(@PathVariable int id){
         userRepository.deleteById(id);


    }
    @GetMapping(value = "/jpa/users/{id}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable int id){
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }
           return  userOptional.get().getPost();


    }
    @PostMapping(value = "/jpa/users/{id}/posts")
    public ResponseEntity createPosts(@PathVariable int id,@RequestBody Post post){
        Optional<User> userOptional= userRepository.findById(id);
        if(!userOptional.isPresent()){
            throw new UserNotFoundException("id-"+id);
        }

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();

    }


}
