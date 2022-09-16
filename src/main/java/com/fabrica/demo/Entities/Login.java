package com.fabrica.demo.Entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LogIn")
@Setter
@Getter
@Data
public class Login {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
}
