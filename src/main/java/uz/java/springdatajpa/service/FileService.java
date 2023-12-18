package uz.java.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.java.springdatajpa.dto.FileDto;
import uz.java.springdatajpa.dto.FileWithBodyDto;
import uz.java.springdatajpa.dto.ResponseDto;
import uz.java.springdatajpa.model.FileModel;
import uz.java.springdatajpa.repository.FileRepository;
import uz.java.springdatajpa.service.mapper.FileMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import java.util.UUID;

/**
 * @author a.ergashev
 * Date: 5/9/2023
 * Time: 5:35 AM
 */
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final FileRepository fileRepository;

    public ResponseDto<FileDto> uploadFile(MultipartFile file) throws IOException {
        String filePath = saveFile(file);

        FileModel fileModel = new FileModel();
        fileModel.setName(file.getOriginalFilename());
        fileModel.setExt(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        fileModel.setPath(filePath);
        fileModel.setStatus(0);
        fileModel.setCreatedAt(LocalDateTime.now());

        fileRepository.save(fileModel);

        return ResponseDto.<FileDto>builder()
                .message("OK")
                .success(true)
                .data(fileMapper.toDto(fileModel))
                .build();
    }

    private String saveFile(MultipartFile file) throws IOException {
        String folders = "/upload/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        File filePath = new File(folders);
        if (!filePath.exists()){
            filePath.mkdirs();
        }

        String fileName = String.format("%s/%s", folders,  UUID.randomUUID());
        Files.copy(file.getInputStream(), Path.of(fileName));

        return fileName;
    }

    public ResponseDto<FileWithBodyDto> download(Integer id) throws IOException {
        Optional<FileModel> file = fileRepository.findByIdAndStatus(id, 0);
        if (file.isEmpty()){
            return ResponseDto.<FileWithBodyDto>builder()
                    .code(-1)
                    .message("File with ID " + id + " is not exists")
                    .build();
        }

        FileWithBodyDto res = fileMapper.toDtoWithData(file.get());

        String filePath = file.get().getPath();
        res.setData(Files.readAllBytes(Path.of(filePath)));

        return ResponseDto.<FileWithBodyDto>builder()
                .success(true)
                .message("OK")
                .data(res)
                .build();
    }
}
