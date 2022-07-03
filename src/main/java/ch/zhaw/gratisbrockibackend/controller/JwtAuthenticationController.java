package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.auth.TokenAuthenticationService;
import ch.zhaw.gratisbrockibackend.auth.UserAuthentication;
import ch.zhaw.gratisbrockibackend.dto.LoginDto;
import ch.zhaw.gratisbrockibackend.service.UserDetailsImpl;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("api/v1")
public class JwtAuthenticationController {

    private final TokenAuthenticationService tokenAuthenticationService;
    private final AuthenticationManager authenticationManagerBean;

    public JwtAuthenticationController(@Qualifier("appUserDetailsServiceImpl") UserDetailsService userDetailsService,
                                       @Value("${gratsibrockibackend.auth.headername:bearer}") TokenAuthenticationService.AuthHeaderName  authHeaderName,
                                       @Value("${gratsibrockibackend.auth.secret:secretkey}") String secretKey,
                                       AuthenticationManager authenticationManagerBean) {

        this.tokenAuthenticationService = new TokenAuthenticationService(authHeaderName, secretKey, userDetailsService);
        this.authenticationManagerBean = authenticationManagerBean;
    }

    @PostMapping("/authenticate")
    public JWTToken authorize(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        );
        final Authentication authentication = authenticationManagerBean.authenticate(authenticationToken);
        final UserDetailsImpl authenticatedUserDetails = (UserDetailsImpl)authentication.getPrincipal();
        final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUserDetails);

        // add the JWT token to the response header
        final String jwt = tokenAuthenticationService.addAuthentication(response, userAuthentication);

        SecurityContextHolder.getContext().setAuthentication(userAuthentication);
        System.out.println("successful authentication");
        return new JWTToken(jwt);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
