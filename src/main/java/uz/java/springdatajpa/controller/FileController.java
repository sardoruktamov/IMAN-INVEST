package uz.java.springdatajpa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.java.springdatajpa.dto.FileDto;
import uz.java.springdatajpa.dto.FileWithBodyDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.service.FileService;

import java.io.IOException;

/**
 * @author a.ergashev
 * Date: 5/9/2023
 * Time: 5:35 AM
 */
@RestController
@RequestMapping("file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseDto<FileDto> upload(@RequestBody MultipartFile file) throws IOException {
        return fileService.uploadFile(file);
    }

    @GetMapping("/{id}")
    public ResponseDto<FileWithBodyDto> download(@PathVariable Integer id) throws IOException {
        return fileService.download(id);
    }
}
