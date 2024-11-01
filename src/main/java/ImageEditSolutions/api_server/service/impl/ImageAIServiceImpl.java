package ImageEditSolutions.api_server.service.impl;

import ImageEditSolutions.api_server.global.CustomException;
import ImageEditSolutions.api_server.global.ErrorCode;
import ImageEditSolutions.api_server.service.ImageAIService;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class ImageAIServiceImpl implements ImageAIService {

    // 이미지 생성 요청 URL
    private static final String IMAGE_AI_URL = "https://image.pollinations.ai/prompt/";

    @Autowired
    RestTemplate restTemplate; // URL 주소로 요청을 보낼 수 있는 라이브러리

    @Override
    public List<byte[]> createImageAI(String text) {

        // 빈 값 체크
        if (text == null || text.trim().isEmpty()) {
            throw new CustomException(ErrorCode.EMPTY_PROMPT);
        }

        List<byte[]> responses = new ArrayList<>();

        // 총 4개의 이미지를 생성하기 위해 반복문을 이용함
        for (int i = 0; i < 4; i++) {
            byte[] response = restTemplate.getForEntity(
                    IMAGE_AI_URL + text + i,
                    byte[].class
            ).getBody();
            responses.add(response);
        }

        return responses;
    }

}
