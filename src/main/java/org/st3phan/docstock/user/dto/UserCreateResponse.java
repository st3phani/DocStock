package org.st3phan.docstock.user.dto;

import lombok.Data;

@Data
public class UserCreateResponse {
    private Long id;
    private String nume;
    private String prenume;
    private String username;
    private String email;
}
