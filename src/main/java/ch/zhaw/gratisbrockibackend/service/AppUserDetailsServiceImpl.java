package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.auth.UserDetailsImpl;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public AppUserDetailsServiceImpl(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if( user == null ){
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}
}
