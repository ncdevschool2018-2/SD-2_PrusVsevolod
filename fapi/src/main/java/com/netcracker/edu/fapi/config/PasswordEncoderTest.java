package com.netcracker.edu.fapi.config;

public class PasswordEncoderTest implements org.springframework.security.crypto.password.PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
    }
}
