package utils;

public enum ErrorCode {
    WRONG_CUSTOM_DELIMITER("올바른 커스텀 구분자가 아닙니다."),
    WRONG_FORMATTING_STRING("잘못된 형식의 문자열입니다."),
    NOT_POSITIVE_NUMBER("양수가 아닌 값입니다."),
    TOO_BIG_NUMBER("너무 큰 값을 입력했습니다.");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
