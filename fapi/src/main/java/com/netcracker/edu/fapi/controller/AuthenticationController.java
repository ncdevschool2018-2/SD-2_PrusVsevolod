package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.config.JwtTokenUtil;
import com.netcracker.edu.fapi.model.AuthToken;
import com.netcracker.edu.fapi.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import static com.netcracker.edu.fapi.model.Constants.TOKEN_PREFIX;

@RestController
//час - время жизни токена
@RequestMapping("/api/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getLogin(),
                        loginUser.getPassword()
                )
        );
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value = "/expDate", method = RequestMethod.GET)
    public ResponseEntity<?> GetExpDate(@RequestHeader(name = "authorization")String token){
        token = token.replace(TOKEN_PREFIX, "");
        return ResponseEntity.ok(jwtTokenUtil.getExpirationDateFromToken(token));
    }

}
