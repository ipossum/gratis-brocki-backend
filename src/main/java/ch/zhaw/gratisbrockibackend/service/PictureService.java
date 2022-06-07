package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public List<Picture> getPictures() {
        return pictureRepository.findAll();
    }

}