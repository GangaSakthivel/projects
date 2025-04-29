package com.example.userManagement.controller;


import com.example.userManagement.Exceptions.ResourceNotFoundException;
import com.example.userManagement.model.User;
import com.example.userManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable Long id){
        return this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with the id " + id));
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id){
        User existingUser = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with the id " + id));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(Long id){
        User existingUser = this.userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found with the id " + id));
        this.userRepository.delete(existingUser);
        //return ResponseEntity.ok(existingUser);
        return ResponseEntity.ok().build();
    }

//findById() returns Optional<User>
//orElseThrow() is called on that Optional
//If the user exists, it returns the User object
//If not, it throws your ResourceNotFoundException
}
