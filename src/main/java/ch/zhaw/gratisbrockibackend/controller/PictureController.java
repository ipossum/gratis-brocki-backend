package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.dto.PictureCreationDto;
import ch.zhaw.gratisbrockibackend.dto.PictureDto;
import ch.zhaw.gratisbrockibackend.mapper.PictureMapper;
import ch.zhaw.gratisbrockibackend.service.PictureService;
import com.turkraft.springfilter.boot.Filter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("api/v1/pictures")
@RestController
public class PictureController {

    private final PictureService pictureService;

    private final PictureMapper pictureMapper;

    @GetMapping
    public Page<PictureDto> getPictures(@Filter Specification<Picture> spec, Pageable page){
        return pictureService.getPictures(spec, page)
                .map(pictureMapper::toPictureDto);
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