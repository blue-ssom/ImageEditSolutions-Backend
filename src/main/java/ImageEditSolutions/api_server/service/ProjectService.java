package ImageEditSolutions.api_server.service;

import ImageEditSolutions.api_server.dto.response.ProjectResDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {
    void uploadProject(String uploadId, MultipartFile multipartFile);
    ProjectResDto downloadProject(String uploadId);
}
