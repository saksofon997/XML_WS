package com.spring.zuul.client;

import com.spring.zuul.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user")
public interface UserClient {

    @GetMapping("/auth/verify/{token}")
    boolean verify(@PathVariable("token") String token);

    @GetMapping("/user/{id}")
    ResponseEntity<UserDTO> getUser(@PathVariable Long id,
                                    @RequestHeader("x-auth") String auth);
}
