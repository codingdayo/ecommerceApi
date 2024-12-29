package codingdayo.com.Ecommerce.controller;

import codingdayo.com.Ecommerce.dto.LoginRequest;
import codingdayo.com.Ecommerce.dto.Response;
import codingdayo.com.Ecommerce.dto.UserDto;
import codingdayo.com.Ecommerce.service.interfac.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody UserDto registrationRequest){
        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> loginUser(@RequestBody LoginRequest login){

        return ResponseEntity.ok(userService.loginUser(login));
    }
}
