package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.dto.PictureUpdateDto;
import ch.zhaw.gratisbrockibackend.mapper.PictureMapper;
import ch.zhaw.gratisbrockibackend.repository.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@AllArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    private final PictureMapper pictureMapper;

    public Page<Picture> getPictures (Specification<Picture> spec, Pageable page){
        // TODO: add some validation (e.g. disallow anything but userId in specification?)
        return pictureRepository.findAll(spec, page);
    }

    public Picture addNewPicture(Picture picture) {
        try {
            // TODO: add some validation logic with pictureValidator
            return pictureRepository.save(picture);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
            return null;
        }
    }

    public Picture updatePicture(Long id, PictureUpdateDto pictureUpdateDto) {
        Picture picture = pictureRepository.findPictureById(id);
        // TODO: add some validation logic with pictureValidator
        picture.setName(pictureUpdateDto.getName());
        picture.setUrl(pictureUpdateDto.getUrl());
        return pictureRepository.save(picture);
    }

    public void deletePicture(Long id) {
        try {
            pictureRepository.deleteById(id);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
        }
    }
}