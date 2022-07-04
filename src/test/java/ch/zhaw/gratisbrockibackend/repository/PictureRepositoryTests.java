/*
package ch.zhaw.gratisbrockibackend.repository;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PictureRepositoryTests {

    @Autowired
    private PictureRepository pictureRepository;

    public Picture createPicture() {
        Picture picture = new Picture();
        picture.setName("testPicture");
        picture.setItem(null);
        picture.setUrl("https://www.testBilder.ch/testBild");
        return picture;
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void savePictureTest() {
        Picture picture = createPicture();
        pictureRepository.save(picture);
        Assertions.assertThat(picture.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getPictureTest() {
        Picture picture = pictureRepository.findPictureById(1L);
        Assertions.assertThat(picture.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getListOfPicturesTest() {
        List<Picture> pictures = pictureRepository.findAll();
        Assertions.assertThat(pictures.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateItemTest() {
        Picture updatedPicture = pictureRepository.findPictureById(1L);
        Assertions.assertThat(updatedPicture.getName()).isEqualTo("testPicture");
        updatedPicture.setName("updatedPicture");
        pictureRepository.save(updatedPicture);
        Assertions.assertThat(updatedPicture.getName()).isEqualTo("updatedPicture");
    }

    @Test
    @Order(5)
    public void deletePictureTest() {
        pictureRepository.deleteById(1L);
        Assertions.assertThat(pictureRepository.findPictureById(1L)).isNull();
    }

}
*/
