package com.example.webosmotic.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "invalid email")
    private String email;

    @NotBlank
    @Size(min = 5, max = 20, message = "password can not be blank")
    private String password;

    @NotBlank(message = "name can not blank")
    @Size(min = 2, max = 10, message = "user name should have at least 2 to 10 characters")
    private String name;


}
