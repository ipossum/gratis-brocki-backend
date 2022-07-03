package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.File;
import ch.zhaw.gratisbrockibackend.repository.FileRepository;
import ch.zhaw.gratisbrockibackend.utils.FileValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class FileService {
    private FileRepository fileRepository;

    private final FileValidator fileValidator;

    public File storeFile (MultipartFile multipartFile) throws IOException {
        fileValidator.plausibilityCheck(multipartFile);
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        File file = new File();
        file.setFilename(filename);
        file.setFiletype(multipartFile.getContentType());
        file.setData(multipartFile.getBytes());
        return fileRepository.save(file);
    }

    public File getFile (Long id) {
        fileValidator.exists(id);
        return fileRepository.findFileById(id);
    }

    public Stream<File> getAllFiles() {
        return fileRepository.findAll().stream();
    }

}
