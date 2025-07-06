package org.st3phan.docstock.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.st3phan.docstock.user.dto.UserCreateRequest;
import org.st3phan.docstock.user.dto.UserCreateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserCreateResponse> register(@Valid @RequestBody UserCreateRequest request) {
        log.info("POST /api/users/register called for username {}", request.getUsername());
        UserCreateResponse response = userService.registerUser(request);
        return ResponseEntity.ok(response);
    }
}
