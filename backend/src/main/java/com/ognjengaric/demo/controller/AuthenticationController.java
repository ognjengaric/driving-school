package com.ognjengaric.demo.controller;

import com.ognjengaric.demo.domain.Role;
import com.ognjengaric.demo.domain.User;
import com.ognjengaric.demo.dto.LoginDTO;
import com.ognjengaric.demo.dto.TokenRoleDTO;
import com.ognjengaric.demo.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Qualifier("customUserDetailsService")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    TokenUtils tokenUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){

        final Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUserId(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = (User)userDetailsService.loadUserByUsername(loginDTO.getUserId());

        if(user == null)
            return ResponseEntity.badRequest().build();

        String jwt = tokenUtils.generateToken(user.getId().toString());
        String role = ((Role)user.getAuthorities().toArray()[0]).getAuthority();

        return ResponseEntity.ok(new TokenRoleDTO(jwt, role));
    }

    @PatchMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        try {
            HttpSession session = request.getSession(false);

            if(session != null)
                session.invalidate();

            SecurityContextHolder.clearContext();

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
