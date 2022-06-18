package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Picture findPictureById(Long id);
}
