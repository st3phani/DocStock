package org.st3phan.docstock.user;

import org.st3phan.docstock.user.dto.UserCreateRequest;
import org.st3phan.docstock.user.dto.UserCreateResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateRequest request) {
        if (request == null) {
            return null;
        }
        User user = new User();
        user.setNume(request.getNume());
        user.setPrenume(request.getPrenume());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return user;
    }

    public UserCreateResponse toResponse(User user) {
        if (user == null) {
            return null;
        }
        UserCreateResponse resp = new UserCreateResponse();
        resp.setId(user.getId());
        resp.setNume(user.getNume());
        resp.setPrenume(user.getPrenume());
        resp.setUsername(user.getUsername());
        resp.setEmail(user.getEmail());
        return resp;
    }
}
