package ImageEditSolutions.api_server.mapper;

import ImageEditSolutions.api_server.dto.request.ProjectReqDto;
import ImageEditSolutions.api_server.entity.Project;

public class ProjectMapper {

    public static Project mapToProject(ProjectReqDto projectReqDto, String imageUrl) {
        return new Project(
                projectReqDto.getUploadId(),
                imageUrl
        );
    }
}
