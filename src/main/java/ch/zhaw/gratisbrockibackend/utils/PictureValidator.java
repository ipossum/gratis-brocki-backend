package ch.zhaw.gratisbrockibackend.utils;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.dto.PictureUpdateDto;
import ch.zhaw.gratisbrockibackend.exceptions.PictureException;
import ch.zhaw.gratisbrockibackend.repository.PictureRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Setter
@Getter
@Component
public class PictureValidator {

    PictureRepository pictureRepository;

    public void exists (Long id) throws PictureException {
        if (!pictureRepository.existsById(id)){
            throw new PictureException("Picture not found");
        }
    }

    public void plausibilityCheck (Picture picture) {

        String name = picture.getName();
        String url = picture.getUrl();

        commonPlausibilityCheck(name, url);
    }

    public void plausibilityCheck (PictureUpdateDto pictureUpdateDto) {

        String name = pictureUpdateDto.getName();
        String url = pictureUpdateDto.getUrl();

        commonPlausibilityCheck(name, url);
    }

    private void commonPlausibilityCheck(String name, String url) throws PictureException {
        if (name == null
                || name.length() < 3) {
            throw new PictureException("Invalid name — make sure minimum length is 3 characters");
        }
        if (name.length() > 50) {
            throw new PictureException("Invalid name - make sure maximum length is 50 characters");
        }
        if (url == null
                || url.length() <= 20) {
            throw new PictureException("Invalid URL");
        }
        if (url.length() > 255) {
            throw new PictureException("URL to long - length must be shorter than 255 signs");
        }
        if (!(url.startsWith("https://") || url.startsWith("http://"))) {
            throw new PictureException("Invalid URL - make sure the url has the right format");
        }
    }

}