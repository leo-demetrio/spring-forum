package com.leodemetrio.forum.controller;

import com.leodemetrio.forum.dto.LoginRequestPost;
import com.leodemetrio.forum.dto.TokenDto;
import com.leodemetrio.forum.service.TokenService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/auth")
@Profile(value = {"prod", "test"})
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(
            AuthenticationManager authenticationManager,
            TokenService tokenService
    ){
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginRequestPost login){
        UsernamePasswordAuthenticationToken dataLogin = login.convert();

        try{
            Authentication authentication = authenticationManager.authenticate(dataLogin);
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.ok().body(new TokenDto(token,"Bearer"));
        }catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
