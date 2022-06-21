package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.dto.PictureCreationDto;
import ch.zhaw.gratisbrockibackend.dto.PictureDto;
import ch.zhaw.gratisbrockibackend.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("api/v1/pictures")
@RestController
public class PictureController {

    private final PictureService pictureService;

    @GetMapping
    public List<PictureDto> getPictures(){
        return pictureService.getPictures();
    }

    @PostMapping
    public ResponseEntity<PictureDto> addNewPicture(@RequestBody PictureCreationDto pictureCreationDto){
        return pictureService.addNewPicture(pictureCreationDto);
    }

    @DeleteMapping("/{id}")
    public void deletePicture(@PathVariable("id") Long id) {
        pictureService.deletePicture(id);
    }
}