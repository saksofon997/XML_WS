package com.spring.zuul.filter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user")
public interface AuthClient {
    @GetMapping("/auth/verify/{token}")
    boolean verify(@PathVariable("token") String token);

}
