package calculator;

import java.util.regex.Pattern;
import utils.ErrorCode;
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
                invokeIllegalArgumentException(ErrorCode.WRONG_CUSTOM_DELIMITER);
            }

            String customDelimiter = String.valueOf(userInputString.charAt(2));
            String customDelimiterRegex = Pattern.quote(customDelimiter);
            delimiterRegex = "(" + delimiterRegex + "|" + customDelimiterRegex + ")";
            userInputString = userInputString.substring(indexOfNewLine + 2);
        }

        String[] userNumberTokensAsString = userInputString.split(delimiterRegex, -1);

        for (String userNumberTokenAsString : userNumberTokensAsString) {
            if (isNullOrEmpty(userNumberTokenAsString)) {
                invokeIllegalArgumentException(ErrorCode.WRONG_FORMATTING_STRING);
            }

            if (isNotPositiveNumber(userNumberTokenAsString)) {
                invokeIllegalArgumentException(ErrorCode.NOT_POSITIVE_NUMBER);
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
            invokeIllegalArgumentException(ErrorCode.TOO_BIG_NUMBER);
        }
        return sum;
    }

    private void invokeIllegalArgumentException(ErrorCode errorCode) {
        throw new IllegalArgumentException(errorCode.getErrorMessage());
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
