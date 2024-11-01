package ImageEditSolutions.api_server.controller;

import ImageEditSolutions.api_server.service.ImageAIService;
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
@RequestMapping("/image-ai")
@RequiredArgsConstructor
public class ImageAIController {

    @Autowired
    ImageAIService imageAIService;

    @GetMapping
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
