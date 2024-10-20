package ImageEditSolutions.api_server.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EMPTY_UPLOAD_ID(HttpStatus.BAD_REQUEST, "UPLOAD-001", "업로드 아이디를 입력해주세요."),
    UPLOAD_ID_ALREADY_EXISTS(HttpStatus.CONFLICT, "UPLOAD-002", "이미 존재하는 아이디입니다."),
    UPLOAD_ID_NOT_FOUND(HttpStatus.NOT_FOUND,"UPLOAD-003", "해당 업로드 아이디를 찾을 수 없습니다."),

    EMPTY_FILE(HttpStatus.BAD_REQUEST, "FILE-001","파일이 첨부되지 않았습니다."),
    INVALID_FILE_EXTENSION(HttpStatus.BAD_REQUEST,"FILE-002", "잘못된 파일 형식입니다. 허용되는 파일 형식: jpg, jpeg, png, gif."),
    FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "FILE-003", "파일 업로드 중 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
