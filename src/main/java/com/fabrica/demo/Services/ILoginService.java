package com.fabrica.demo.Services;

import com.fabrica.demo.Entities.Login;
import org.springframework.stereotype.Service;

@Service
public interface ILoginService {
    public Boolean login(String username, String password);
}
