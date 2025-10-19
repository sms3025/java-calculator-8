package calculator;

import java.util.regex.Pattern;
import utils.PrintMessage;
import utils.UserInput;

public class Calculator {

    private String delimiterRegex = "[,:]";

    public Calculator() {
    }

    public void execute() {
        String userInputString = UserInput.inputString();
        Long sum = 0L;

        if (isNullOrEmpty(userInputString)) {
            PrintMessage.printSum(sum);
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

            sum = addParsedNumber(userNumberTokenAsString, sum);
        }
        PrintMessage.printSum(sum);
    }

    private Long addParsedNumber(String userNumberTokenAsString, Long sum) {
        try {
            Long userInputNumber = Long.parseLong(userNumberTokenAsString);
            sum = Math.addExact(sum, userInputNumber);
        } catch (NumberFormatException | ArithmeticException e) {
            throw new IllegalArgumentException("너무 큰 값을 입력했습니다.");
        }
        return sum;
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
