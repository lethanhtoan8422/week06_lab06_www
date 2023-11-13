package vn.edu.iuh.fit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.models.User;
import vn.edu.iuh.fit.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public List<User> findAll(){
        return userService.findAllUser();
    }

    @GetMapping("/by-email/{email}")
    public User findByEmail(@PathVariable("email") String email){
        return userService.findByEmail(email).orElse(null);
    }
}
