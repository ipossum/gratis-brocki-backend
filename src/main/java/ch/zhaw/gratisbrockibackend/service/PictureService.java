package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.Picture;
import ch.zhaw.gratisbrockibackend.dto.PictureCreationDto;
import ch.zhaw.gratisbrockibackend.dto.PictureDto;
import ch.zhaw.gratisbrockibackend.mapper.PictureMapper;
import ch.zhaw.gratisbrockibackend.repository.PictureRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@AllArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    private final PictureMapper pictureMapper;

    public List<PictureDto> getPictures() {
        return pictureRepository.findAll()
                .stream()
                .map(pictureMapper::toPictureDto)
                .toList();
    }

    public ResponseEntity<PictureDto> addNewPicture(PictureCreationDto pictureCreationDto) {
        try {
            // TODO: add some validation logic with pictureValidator
            Picture picture = pictureMapper.toPicture(pictureCreationDto);
            pictureRepository.save(picture);
            return ResponseEntity.ok(pictureMapper.toPictureDto(picture));
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    public void deletePicture(Long id) {
        try {
            pictureRepository.deleteById(id);
        } catch (HttpClientErrorException.BadRequest e) {
            e.printStackTrace();
        }
    }
}