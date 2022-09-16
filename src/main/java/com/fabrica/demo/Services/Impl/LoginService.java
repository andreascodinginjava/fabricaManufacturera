package com.fabrica.demo.Services.Impl;

import com.fabrica.demo.Entities.Login;
import com.fabrica.demo.Repositories.LoginRepository;
import com.fabrica.demo.Services.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService implements ILoginService {
    @Autowired
    LoginRepository loginRepository;

    @Override
    public Boolean login(String username, String password) {
        Optional<Login> resultLogin = loginRepository.findAllByUsernameAndPassword(username, password);

        if (resultLogin.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
