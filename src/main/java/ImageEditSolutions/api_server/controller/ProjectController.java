package ImageEditSolutions.api_server.controller;

import ImageEditSolutions.api_server.dto.request.ProjectReqDto;
import ImageEditSolutions.api_server.dto.response.ProjectResDto;
import ImageEditSolutions.api_server.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @PostMapping("/upload")
    public ResponseEntity<String> saveProject(ProjectReqDto projectReqDto){
        projectService.saveProject(projectReqDto);
        return ResponseEntity.ok("프로젝트가 성공적으로 업로드되었습니다.");
    }

    @GetMapping("/download")
    public ResponseEntity<ProjectResDto> downloadProject(@RequestParam String uploadId) {
        ProjectResDto projectResDto = projectService.downloadProject(uploadId);
        return ResponseEntity.ok(projectResDto);
    }
}
