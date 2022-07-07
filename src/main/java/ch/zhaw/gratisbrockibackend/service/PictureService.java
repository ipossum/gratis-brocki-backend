package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.dto.PictureUpdateDto;
import ch.zhaw.gratisbrockibackend.repository.PictureRepository;
import ch.zhaw.gratisbrockibackend.utils.PictureValidator;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    private final PictureValidator pictureValidator;

    public Picture addNewPicture(Picture picture) {
        pictureValidator.plausibilityCheck(picture);
        return pictureRepository.save(picture);
    }

    public Page<Picture> getPictures (Specification<Picture> spec, Pageable page){

        return pictureRepository.findAll(spec, page);
    }

    public Picture updatePicture(Long id, PictureUpdateDto pictureUpdateDto) {
        pictureValidator.plausibilityCheck(pictureUpdateDto);
        Picture picture = pictureRepository.findPictureById(id);
        picture.setName(pictureUpdateDto.getName());
        picture.setUrl(pictureUpdateDto.getUrl());
        return pictureRepository.save(picture);
    }

    public void deletePicture(Long id) {
        pictureValidator.exists(id);
        pictureRepository.deleteById(id);
    }
}