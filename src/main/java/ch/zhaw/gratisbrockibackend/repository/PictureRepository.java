package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT p FROM Picture p WHERE p.id = :id")
    Picture findPictureByID(Long id);
}
