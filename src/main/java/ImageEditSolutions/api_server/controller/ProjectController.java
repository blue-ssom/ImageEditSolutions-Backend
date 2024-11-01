package ImageEditSolutions.api_server.controller;

import ImageEditSolutions.api_server.dto.response.ProjectResDto;
import ImageEditSolutions.api_server.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@Tag(name = "Project Management", description = "프로젝트 업로드 및 다운로드 관련 API")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "프로젝트 저장하기")
    @Parameter(name = "uploadId", description = "프로젝트 업로드에 사용되는 고유 아이디")
    @ApiResponse(responseCode = "200", description = "프로젝트 저장하기 성공")
    public ResponseEntity<String> uploadProject( @RequestParam("uploadId") String uploadId,
            @Parameter(description = "프로젝트에 업로드할 이미지 파일. 허용되는 파일 형식: jpg, jpeg, png, gif")
            @RequestParam("multipartFile") MultipartFile multipartFile){
        projectService.uploadProject(uploadId, multipartFile);
        return ResponseEntity.ok("프로젝트가 성공적으로 업로드되었습니다.");
    }

    @GetMapping("/download")
    @Operation(summary = "프로젝트 가져오기")
    @Parameter(name = "uploadId", description = "프로젝트 업로드에 사용되는 고유 아이디")
    @ApiResponse(responseCode = "200", description = "프로젝트 가져오기 성공", content = @Content(schema = @Schema(implementation = ProjectResDto.class)))
    public ResponseEntity<ProjectResDto> downloadProject(@RequestParam String uploadId) {
        ProjectResDto projectResDto = projectService.downloadProject(uploadId);
        return ResponseEntity.ok(projectResDto);
    }

}
