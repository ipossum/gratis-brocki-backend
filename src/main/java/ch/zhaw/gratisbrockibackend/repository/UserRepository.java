package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.tokens.Token;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByFullName(String name);
    User findByEmail(String email);

}
