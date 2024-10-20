package ImageEditSolutions.api_server.service.impl;

import ImageEditSolutions.api_server.dto.request.ProjectReqDto;
import ImageEditSolutions.api_server.entity.Project;
import ImageEditSolutions.api_server.global.CustomException;
import ImageEditSolutions.api_server.global.ErrorCode;
import ImageEditSolutions.api_server.mapper.ProjectMapper;
import ImageEditSolutions.api_server.repository.ProjectRepository;
import com.amazonaws.services.s3.AmazonS3;
import ImageEditSolutions.api_server.service.ProjectService;

import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    AmazonS3 s3Client;

    @Autowired
    private String bucketName;

    private ProjectRepository projectRepository;

    @Override
    public void saveProject(ProjectReqDto projectReqDto) {
        String uploadId = projectReqDto.getUploadId();
        MultipartFile multipartFile = projectReqDto.getMultipartFile();

    try{

        // 업로드 아이디 null 체크
        if (uploadId == null || uploadId.trim().isEmpty())
           throw new CustomException(ErrorCode.EMPTY_UPLOAD_ID);

        // 업로드 아이디 존재 여부 확인
        if (projectRepository.existsByUploadId(uploadId)) {
            throw new CustomException(ErrorCode. UPLOAD_ID_ALREADY_EXISTS);
        }

        // 파일 null 체크
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_FILE);
        }

        // 파일 확장자 확인
        String fileName = multipartFile.getOriginalFilename();
        if (fileName == null || !isValidExtension(fileName)) {
            throw new CustomException(ErrorCode.INVALID_FILE_EXTENSION);
        }

        // 파일 이름 생성
        String uniqueFileName = generateFileName(uploadId, multipartFile.getOriginalFilename());

        // 파일 메타데이터 설정
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(multipartFile.getSize());
        metadata.setContentType(multipartFile.getContentType());

        // S3에 파일 업로드
        s3Client.putObject(bucketName, uniqueFileName, multipartFile.getInputStream(), metadata);

        // 업로드된 파일의 URL 생성
        String imageUrl = s3Client.getUrl(bucketName, uniqueFileName).toString();

        // 7. 프로젝트 엔티티 생성 및 DB 저장
        Project project = ProjectMapper.mapToProject(projectReqDto, imageUrl);
        projectRepository.save(project);
        } catch (IOException e) {
            // IOException 발생 시 예외 처리
            throw new CustomException(ErrorCode.FILE_UPLOAD_ERROR);
        }
    }

    // 확장자 유효성 검사
    private boolean isValidExtension(String fileName) {
        // 확장자 유효성 검사용 리스트
        List<String> validExtensions = Arrays.asList("jpg", "jpeg", "png", "gif");
        // 파일명에서 마지막 '.' 의 위치를 찾음
        int lastDotIndex = fileName.lastIndexOf(".");
        // 파일 확장자가 존재하지 않으면 유효하지 않음
        if (lastDotIndex == -1) {
            return false;
        }
        // 확장자 추출
        String extension = fileName.substring(lastDotIndex + 1).toLowerCase();
        // 유효한 확장자인지 확인
        return validExtensions.contains(extension);
    }

    // 파일 이름 생성
    private String generateFileName(String uploadId, String originalFilename) {
        int lastDotIndex = originalFilename.lastIndexOf(".");
        String extension = originalFilename.substring(lastDotIndex);
        return uploadId + "/" + UUID.randomUUID().toString() + extension;
    }
}
