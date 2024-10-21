package ImageEditSolutions.api_server.mapper;

import ImageEditSolutions.api_server.dto.response.ProjectResDto;
import ImageEditSolutions.api_server.entity.Project;

public class ProjectMapper {

    public static Project mapToProject(String uploadId, String imageUrl) {
        return new Project(
                uploadId,
                imageUrl
        );
    }

    public static ProjectResDto mapToProjectResDto(Project project) {
        return new ProjectResDto(
                project.getId(),
                project.getUploadId(),
                project.getImageUrl(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
