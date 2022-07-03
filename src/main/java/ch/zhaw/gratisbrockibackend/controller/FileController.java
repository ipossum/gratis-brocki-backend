package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.domain.File;
import ch.zhaw.gratisbrockibackend.dto.FileDto;
import ch.zhaw.gratisbrockibackend.mapper.FileMapper;
import ch.zhaw.gratisbrockibackend.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping("api/v1/files")
@RestController
public class FileController {

    private final FileService fileService;

    private final FileMapper fileMapper;

    /**
     * Method that takes a file sent as a MultipartFile, sends it onwards to FileService
     * and returns it as a converted FileDto
     * Testing via Postman: send as "form-data" with key "file" and the actual file as "value"
     *
     * @param dbFile the file as MultipartFile
     * @throws IOException throw regular IOException
     */
    @PostMapping
    public FileDto uploadNewFile (@RequestParam("file") MultipartFile dbFile) throws IOException {
        File file = fileService.storeFile(dbFile);
        FileDto fileDto = fileMapper.toFileDto(file);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/files/")
                .path(file.getId().toString())
                .toUriString();

        fileDto.setUrl(fileDownloadUri);
        fileDto.setSize(dbFile.getSize());

        return fileDto;
    }

    @GetMapping("/{id}")
    public File getFile(@PathVariable Long id) {
        return fileService.getFile(id);
    }

    @GetMapping
    public List<FileDto> getFiles() {
        return fileService.getAllFiles().map(file -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/v1/files/")
                    .path(file.getId().toString())
                    .toUriString();
            return new FileDto(
                    file.getFilename(),
                    fileDownloadUri,
                    file.getFiletype(),
                    file.getData().length);
        }).collect(Collectors.toList());
    }

}
