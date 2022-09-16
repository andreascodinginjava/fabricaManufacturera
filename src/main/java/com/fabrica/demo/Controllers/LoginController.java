package com.fabrica.demo.Controllers;

import com.fabrica.demo.Services.Impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @Autowired
    LoginService service;

    @GetMapping("/{username}/{password}")
    public ResponseEntity<Boolean> login(@PathVariable("username") String username, @PathVariable("password") String password) {
        return new ResponseEntity<Boolean>(service.login(username, password), HttpStatus.OK);
    }
}
