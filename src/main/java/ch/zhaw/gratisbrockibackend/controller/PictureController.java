package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/pictures")
@RestController
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping
    public List<Picture> getPictures(){
        return pictureService.getPictures();
    }
}