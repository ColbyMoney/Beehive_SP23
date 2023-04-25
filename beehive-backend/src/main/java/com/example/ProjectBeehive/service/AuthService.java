package com.example.ProjectBeehive.service;

import com.example.ProjectBeehive.payload.LoginDto;
import com.example.ProjectBeehive.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
