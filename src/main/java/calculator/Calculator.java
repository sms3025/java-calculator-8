package calculator;

import java.util.regex.Pattern;
import utils.UserInputClass;

public class Calculator {
    private String delimiterRegex = "[,:]";

    public Calculator() {
    }

    public void execute() {
        String userInputString = UserInputClass.inputString();
        Long addSum = 0L;

        if(userInputString == null || userInputString.isEmpty()) {
            System.out.println("결과 : " + addSum);
            return;
        }

        if(userInputString.startsWith("//")) {
            Integer indexOfNewLine = userInputString.indexOf("\\n");
            if (indexOfNewLine != 3) {
                throw new IllegalArgumentException("올바른 커스텀 구분자가 아닙니다.");
            }

            String customDelimiter = String.valueOf(userInputString.charAt(2));
            String customDelimiterRegex = Pattern.quote(customDelimiter);
            delimiterRegex =  "(" + delimiterRegex + "|" + customDelimiterRegex + ")";
            userInputString = userInputString.substring(indexOfNewLine + 2);
        }

        String[] userNumberTokensAsString = userInputString.split(delimiterRegex , -1);

        for(String userNumberTokenAsString : userNumberTokensAsString) {
            if(userNumberTokenAsString == null || userNumberTokenAsString.isEmpty()) {
                throw new IllegalArgumentException("잘못된 형식의 문자열입니다.");
            }

            if(!userNumberTokenAsString.matches("^[1-9]\\d*$")) {
                throw new IllegalArgumentException("양수가 아닌 값입니다.");
            }

            try {
                Long userInputNumber = Long.parseLong(userNumberTokenAsString);
                addSum = Math.addExact(addSum, userInputNumber);
            } catch (NumberFormatException | ArithmeticException e) {
                throw new IllegalArgumentException("너무 큰 값을 입력했습니다.");
            }
        }
        System.out.println("결과 : " + addSum);
    }
}
