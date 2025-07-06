package org.st3phan.docstock.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nume;
    private String prenume;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;
}
