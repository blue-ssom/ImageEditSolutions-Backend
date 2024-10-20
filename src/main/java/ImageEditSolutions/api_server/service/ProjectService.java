package ImageEditSolutions.api_server.service;

import ImageEditSolutions.api_server.dto.request.ProjectReqDto;
import ImageEditSolutions.api_server.dto.response.ProjectResDto;

public interface ProjectService {
    void saveProject(ProjectReqDto projectReqDto);
    ProjectResDto downloadProject(String uploadId);
}
