package com.thedevjournal.mystocks.service.impl;

import com.thedevjournal.mystocks.dto.request.LoginRequestDto;
import com.thedevjournal.mystocks.dto.request.RegisterRequestDto;
import com.thedevjournal.mystocks.dto.response.LoginResponseDto;
import com.thedevjournal.mystocks.enums.RoleType;
import com.thedevjournal.mystocks.exception.AppException;
import com.thedevjournal.mystocks.model.User;
import com.thedevjournal.mystocks.repository.UserRepository;
import com.thedevjournal.mystocks.security.JwtTokenProvider;
import com.thedevjournal.mystocks.service.RoleService;
import com.thedevjournal.mystocks.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleService roleService;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsernameOrEmail(),request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);
        return LoginResponseDto.builder().accessToken(token).build();
    }

    @Transactional
    @Override
    public void create(RegisterRequestDto request, List<RoleType> roles) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .build();
        roles.forEach(r -> user.addRole(roleService.findByName(r)));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void register(RegisterRequestDto request) {
        boolean existsByUsernameOrEmail = userRepository.existsByUsernameOrEmail(request.getUsername(),
                request.getEmail());
        if (existsByUsernameOrEmail) {
            throw new AppException("User with username/email already exists");
        }
        create(request, List.of(RoleType.ROLE_USER));
    }

    @Override
    public Long count() {
        return userRepository.count();
    }
}
