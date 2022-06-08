package ch.zhaw.gratisbrockibackend.service;

import com.example.demoinitial.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AppUserDetailsService extends UserDetailsService{

	User findByEmail(String email);

}
