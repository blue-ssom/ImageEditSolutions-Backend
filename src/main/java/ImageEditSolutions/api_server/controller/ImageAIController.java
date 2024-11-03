package ImageEditSolutions.api_server.controller;

import ImageEditSolutions.api_server.global.CustomException;
import ImageEditSolutions.api_server.global.ErrorCode;
import ImageEditSolutions.api_server.service.ImageAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image-ai")
@Tag(name = "AI Image Management", description = "AI 이미지 생성 및 수정 관련 API")
public class ImageAIController {

    @Autowired
    ImageAIService imageAIService;

    private final String deeplApiKey = "9fb5c944-b94d-4f00-b099-44d455cf7375:fx";
    private static final String deeplUrl = "https://api-free.deepl.com/v2/translate";
    private final RestTemplate restTemplate;

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

    @PostMapping("/translate")
    public ResponseEntity<?> translate(@RequestBody Map<String, String> request){

        String prompt = request.get("prompt");
        
        // 빈값 체크
        if (prompt == null || prompt.trim().isEmpty()) {
           throw new CustomException(ErrorCode.EMPTY_PROMPT);
        }

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "DeepL-Auth-Key " + deeplApiKey); // API 키 추가

        // 요청 바디 설정
        Map<String, Object> body = Map.of(
                "text", Collections.singletonList(prompt),
                "target_lang", "EN"
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        // DeepL API 호출
        ResponseEntity<String> deeplResponse = restTemplate.exchange(
                deeplUrl, // 요청을 보낼 DeepL API의 URL
                HttpMethod.POST, // HTTP POST 메서드를 사용하여 요청
                entity, // 요청 헤더와 바디를 포함하는 HttpEntity 객체
                String.class); // 응답 타입으로 String 클래스를 지정

        // DeepL의 응답을 그대로 프론트엔드로 반환
        return ResponseEntity.status(deeplResponse.getStatusCode()).body(deeplResponse.getBody());
    }

}
