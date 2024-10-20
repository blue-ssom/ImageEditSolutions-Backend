package ImageEditSolutions.api_server.service;

import ImageEditSolutions.api_server.dto.request.ProjectReqDto;

public interface ProjectService {
    void saveProject(ProjectReqDto projectReqDto);
}
