package com.tcs.training.userms.controller;

import java.util.Optional;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.training.userms.model.User;
import com.tcs.training.userms.repo.UserRepo;
import com.tcs.training.userms.util.ResponseData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class UserMsController {

    @Autowired
    private UserRepo userRepo;

    // private Logger log = LoggerFactory.getLogger(UserMsController.class);

    @PostMapping("/user/post")
    public ResponseData<User> addUser(@RequestBody User entity) {
        Example<User> exampleUser = Example.of(entity);
        Optional<User> user = userRepo.findOne(exampleUser);
        if (user.isPresent()) {
            return new ResponseData<User>(409, "User already exist", entity);
        } else {
            userRepo.saveAndFlush(entity);
            return new ResponseData<User>(200, "User created successfully", entity);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseData<User> getMethodName(@PathVariable Long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent()) {
            return new ResponseData<User>(200, "User fetched successfully", user.get());
        } else {
            return new ResponseData<User>(404, "User does not exsist in the system", null);
        }
    }

    @PatchMapping("/user/patch")
    public ResponseData<User> editUser(@RequestBody User entity) {
        Optional<User> userRow = userRepo.findById(entity.getId());
        if (userRow.isPresent()) {
            User user = userRow.get();
            user.updateUser(entity);
            return new ResponseData<User>(200, "User updated successfully", userRepo.save(user));
        } else {
            return new ResponseData<User>(404, "User does not exsist in the system", null);
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseData<User> deleteUser(@PathVariable Long id) {
        Optional<User> deletedUser = userRepo.findById(id);
        if (deletedUser.isPresent()) {
            userRepo.deleteById(id);
            return new ResponseData<User>(200, "User deleted successfully", deletedUser.get());
        } else {
            return new ResponseData<User>(404, "User does not exsist in the system", null);
        }
    }
}
