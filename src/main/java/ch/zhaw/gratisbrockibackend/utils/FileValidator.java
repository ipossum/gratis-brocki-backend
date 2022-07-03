package ch.zhaw.gratisbrockibackend.utils;

import ch.zhaw.gratisbrockibackend.exceptions.FileException;
import ch.zhaw.gratisbrockibackend.exceptions.ItemException;
import ch.zhaw.gratisbrockibackend.repository.FileRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Setter
@Getter
@Component
public class FileValidator { // TODO: add additional, more sophisticated plausibility checks

    FileRepository fileRepository;

    public void exists (Long id) throws FileException {
        if (!fileRepository.existsById(id)){
            throw new FileException("File not found");
        }
    }

    public void plausibilityCheck(MultipartFile multipartFile) throws FileException {

        if (multipartFile.getOriginalFilename() == null){
            throw new FileException("Filename is missing");
        }
    }
}
