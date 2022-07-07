package ch.zhaw.gratisbrockibackend.controller;

import ch.zhaw.gratisbrockibackend.domain.File;
import ch.zhaw.gratisbrockibackend.dto.FileDto;
import ch.zhaw.gratisbrockibackend.dto.FileResponseDto;
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
     * and returns it as a converted FileResponseDto
     * Testing via Postman: send as "form-data" with key "file" and the actual file as "value"
     *
     * @param dbFile the file as MultipartFile
     * @throws IOException throw regular IOException
     */
    @PostMapping
    public FileResponseDto uploadNewFile (@RequestParam("file") MultipartFile dbFile) throws IOException {
        File file = fileService.storeFile(dbFile, 1L); // TODO: this method should receive the correct itemId ("1L" as placeholder)
        FileResponseDto fileResponseDto = fileMapper.toFileResponseDto(file);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/v1/files/")
                .path(file.getId().toString())
                .toUriString();

        fileResponseDto.setUrl(fileDownloadUri);
        fileResponseDto.setSize(dbFile.getSize());

        return fileResponseDto;
    }

    @GetMapping("/{id}")
    public FileDto getFile(@PathVariable Long id) {
        return fileMapper.toFileDto(fileService.getFile(id));
    }

    @GetMapping
    public List<FileResponseDto> getFiles() {
        return fileService.getAllFiles().map(file -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/api/v1/files/")
                    .path(file.getId().toString())
                    .toUriString();
            return new FileResponseDto(
                    file.getFilename(),
                    fileDownloadUri,
                    file.getFiletype(),
                    file.getData().length);
        }).collect(Collectors.toList());
    }

}
