package com.chon.myapi.controller;

import com.chon.myapi.client.AuthClient;
import com.chon.myapi.client.dto.AuthRequestDto;
import com.chon.myapi.client.dto.AuthResponseDto;
import com.chon.myapi.property.AuthProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthProperty authProperty;

    @Autowired
    private AuthClient authClient;

    @GetMapping(value = "/token")
    public @ResponseBody
    AuthResponseDto token(@RequestParam String code) {
        AuthRequestDto authRequestDto = new AuthRequestDto();

        authRequestDto.setCode(code);
        authRequestDto.setClient_id(authProperty.getClientId());
        authRequestDto.setClient_secret(authProperty.getClientSecret());
        authRequestDto.setGrant_type(authProperty.getGrantType());
        authRequestDto.setRedirect_uri(authProperty.getRedirectUri());

        return authClient.token(authRequestDto);
    }
}
