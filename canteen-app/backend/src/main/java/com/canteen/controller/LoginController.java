package com.canteen.controller;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {

    static class User {

        public String username;
        public String password;
        public String role;

        User(String username,
             String password,
             String role) {

            this.username = username;
            this.password = password;
            this.role = role;
        }
    }

    private final List<User> users = Arrays.asList(

        new User(
            "admin",
            "admin123",
            "ADMIN"
        ),

        new User(
            "santhosh",
            "1234",
            "USER"
        ),

        new User(
            "rahul",
            "1111",
            "USER"
        ),

        new User(
            "priya",
            "2222",
            "USER"
        )
    );

    @PostMapping("/login")
    public Map<String, String> login(
            @RequestBody Map<String, String> req) {

        String username =
            req.get("username");

        String password =
            req.get("password");

        for (User u : users) {

            if (u.username.equals(username)
                    && u.password.equals(password)) {

                Map<String, String> res =
                    new HashMap<>();

                res.put(
                    "username",
                    u.username
                );

                res.put(
                    "role",
                    u.role
                );

                return res;
            }
        }

        throw new RuntimeException(
            "Invalid credentials"
        );
    }
}