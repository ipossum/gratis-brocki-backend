package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.repository.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    public List<Picture> getPictures() {
        return pictureRepository.findAll();
    }

}