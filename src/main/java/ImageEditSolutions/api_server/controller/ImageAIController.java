package ImageEditSolutions.api_server.controller;

import ImageEditSolutions.api_server.service.ImageAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Base64;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image-ai")
@Tag(name = "AI Image Management", description = "AI 이미지 생성 및 수정 관련 API")
public class ImageAIController {

    @Autowired
    ImageAIService imageAIService;

    @GetMapping
    @Operation(summary = "AI 이미지 생성하기")
    @Parameter(name = "prompt", description = "생성할 이미지의 설명을 나타내는 문자열")
    @ApiResponse(responseCode = "200", description = "AI 이미지 생성하기 성공")
    public ResponseEntity<List<String>> createImageAI(@RequestParam String prompt) {

        // 이미지 생성
        List<byte[]> images = imageAIService.createImageAI(prompt);

        // Base64로 인코딩
        List<String> base64Images = images.stream()
                .map(img -> Base64.getEncoder().encodeToString(img))
                .toList();

        return ResponseEntity.ok(base64Images);
    }

}