package calculator;

import java.util.regex.Pattern;
import utils.UserInput;

public class Calculator {

    private String delimiterRegex = "[,:]";

    public Calculator() {
    }

    public void execute() {
        String userInputString = UserInput.inputString();
        Long addSum = 0L;

        if (isNullOrEmpty(userInputString)) {
            System.out.println("결과 : " + addSum);
            return;
        }

        if (isCustomDelimiter(userInputString)) {
            Integer indexOfNewLine = userInputString.indexOf("\\n");
            if (isWrongIndexOfNewLine(indexOfNewLine)) {
                throw new IllegalArgumentException("올바른 커스텀 구분자가 아닙니다.");
            }
            String customDelimiter = String.valueOf(userInputString.charAt(2));
            String customDelimiterRegex = Pattern.quote(customDelimiter);
            delimiterRegex = "(" + delimiterRegex + "|" + customDelimiterRegex + ")";
            userInputString = userInputString.substring(indexOfNewLine + 2);
        }

        String[] userNumberTokensAsString = userInputString.split(delimiterRegex, -1);

        for (String userNumberTokenAsString : userNumberTokensAsString) {
            if (isNullOrEmpty(userNumberTokenAsString)) {
                throw new IllegalArgumentException("잘못된 형식의 문자열입니다.");
            }

            if (isNotPositiveNumber(userNumberTokenAsString)) {
                throw new IllegalArgumentException("양수가 아닌 값입니다.");
            }

            addSum = addParsedNumber(userNumberTokenAsString, addSum);
        }
        System.out.println("결과 : " + addSum);
    }

    private Long addParsedNumber(String userNumberTokenAsString, Long addSum) {
        try {
            Long userInputNumber = Long.parseLong(userNumberTokenAsString);
            addSum = Math.addExact(addSum, userInputNumber);
        } catch (NumberFormatException | ArithmeticException e) {
            throw new IllegalArgumentException("너무 큰 값을 입력했습니다.");
        }
        return addSum;
    }

    private boolean isCustomDelimiter(String userInputString) {
        return userInputString.startsWith("//");
    }

    private boolean isWrongIndexOfNewLine(Integer indexOfNewLine) {
        return indexOfNewLine != 3;
    }

    private boolean isNotPositiveNumber(String userNumberTokenAsString) {
        return !userNumberTokenAsString.matches("^[1-9]\\d*$");
    }

    private boolean isNullOrEmpty(String userInputString) {
        return userInputString == null || userInputString.isEmpty();
    }
}
