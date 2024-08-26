package com.toychat.prj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.toychat.prj.common.jwt.JwtUtil;
import com.toychat.prj.entity.User;
import com.toychat.prj.entity.UserDetailsImpl;
import com.toychat.prj.repository.UserRepository;
import com.toychat.prj.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
public class AdminLoginController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService myUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;
    
	@PostMapping("/login")
    public User authenticate(@RequestBody User user, HttpSession session) throws AuthenticationException {
		
		// 인증 처리
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getId(), user.getPw()));
		
		// 로그인정보 리턴
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
		user.setNick(userDetails.getUser().getNick());
		user.setJwt(jwtToken);

        return user;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
    	System.out.println("Registering..");
    	System.out.println(user.toString());
        User existingUser = userService.findByUserId(user.getId());
        if (existingUser != null) {
            throw new RuntimeException("User already exists");
        }
        user.setPw(passwordEncoder.encode(user.getPw())); // 비밀번호 암호화
        userRepository.save(user);
        return "User registered successfully";
    }	
}
