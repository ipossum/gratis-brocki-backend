package ch.zhaw.gratisbrockibackend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@Component
public class BCryptPasswordEncoderImpl extends BCryptPasswordEncoder {
}
