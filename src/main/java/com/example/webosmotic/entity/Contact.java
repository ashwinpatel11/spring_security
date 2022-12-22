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
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "invalid email")
    private String email;

    @NotBlank(message = "first name can not blank")
    @Size(min = 2, max = 10, message = "first name should have at least 3 to 10 characters")
    private String firstName;

    @NotBlank(message = "last name can not blank")
    @Size(min = 2, max = 10, message = "last name should have at least 3 to 10 characters")
    private String lastName;

    @Size(min = 10,max=10, message = "mob number should have 10 digit")
    private String mob;

    private Long pid;

}
