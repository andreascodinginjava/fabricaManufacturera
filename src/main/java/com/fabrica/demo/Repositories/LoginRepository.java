package com.fabrica.demo.Repositories;

import com.fabrica.demo.Entities.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<Login, String> {
    public Optional<Login> findAllByUsernameAndPassword(String username, String password);
}
