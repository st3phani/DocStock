package org.st3phan.docstock.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.st3phan.docstock.user.dto.UserCreateRequest;
import org.st3phan.docstock.user.dto.UserCreateResponse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    void register_success() throws Exception {
        UserCreateRequest request = new UserCreateRequest();
        request.setNume("Ion");
        request.setPrenume("Popescu");
        request.setUsername("ion");
        request.setEmail("ion@example.com");
        request.setPassword("pass");

        UserCreateResponse response = new UserCreateResponse();
        response.setId(1L);
        response.setNume("Ion");
        response.setPrenume("Popescu");
        response.setUsername("ion");
        response.setEmail("ion@example.com");

        when(userService.registerUser(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("ion"));
    }

    @Test
    void register_validationError() throws Exception {
        UserCreateRequest request = new UserCreateRequest();
        request.setNume("");
        request.setPrenume("Popescu");
        request.setUsername("ion");
        request.setEmail("not-an-email");
        request.setPassword("pass");

        mockMvc.perform(post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
