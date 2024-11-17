package ImageEditSolutions.api_server.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Simple Get", description = "단순 GET 요청에 대한 응답 API")
public class SimpleGetController {
    @GetMapping("/")
    @Operation(summary = "단순 GET 요청 응답")
    @ApiResponse(responseCode = "200", description = "OK 응답 반환")
    public ResponseEntity<String> simpleGetResponse() {
        return ResponseEntity.ok("OK");  // 200 OK 응답 반환
    }
}
