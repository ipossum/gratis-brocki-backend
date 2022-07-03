package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    File findFileById(Long id);

}
