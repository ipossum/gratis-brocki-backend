package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.Item;
import ch.zhaw.gratisbrockibackend.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE m.id = :id")
    Message findMessageByID(Long id);

}