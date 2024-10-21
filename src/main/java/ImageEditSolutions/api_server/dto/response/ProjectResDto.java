package ImageEditSolutions.api_server.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "프로젝트 응답  데이터")
public class ProjectResDto {
    @Schema(description = "프로젝트의 고유 식별자")
    private Long Id;

    @Schema(description = "프로젝트 업로드에 사용되는 고유 아이디")
    private String uploadId;

    @Schema(description = "프로젝트에 업로드된 이미지의 URL")
    private String imageUrl;

    @Schema(description = "프로젝트가 생성된 날짜와 시간")
    private LocalDateTime createdAt;

    @Schema(description = "프로젝트가 마지막으로 업데이트된 날짜와 시간")
    private LocalDateTime updatedAt;
}
