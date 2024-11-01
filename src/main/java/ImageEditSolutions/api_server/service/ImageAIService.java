package ImageEditSolutions.api_server.service;

import java.util.List;

public interface ImageAIService {
    List<byte[]> createImageAI(String text);
}
