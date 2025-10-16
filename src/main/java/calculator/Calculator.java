package calculator;

import java.util.regex.Pattern;
import utils.UserInputClass;

public class Calculator {
    private String userInputString;
    private Long addSum = 0L;
    private String delimiterRegex = "[,:]";

    public Calculator() {
    }

    public void execute() {
        userInputString = UserInputClass.InputString();
        if(userInputString == null || userInputString.isBlank()) {
           System.out.println("값: " + addSum);
           return;
        }

        if(userInputString.startsWith("//")) {
            int indexOfNewLine = userInputString.indexOf("\\n");
            if (indexOfNewLine != 3) {
                throw new IllegalArgumentException("올바른 커스텀 구분자가 아닙니다.");
            }
            String customDelimiter = String.valueOf(userInputString.charAt(2));
            String customDelimiterRegex = Pattern.quote(customDelimiter);
            delimiterRegex =  "(" + delimiterRegex + "|" + customDelimiterRegex + ")";
            userInputString = userInputString.substring(indexOfNewLine + 2);
        }

        String[] NumberTokens = userInputString.split(delimiterRegex);
        for(String token : NumberTokens) {
            System.out.println(token);
            if(token == null || token.isBlank()) {
                throw new IllegalArgumentException("잘못된 형식의 문자열입니다.");
            }
            if(!token.matches("^[1-9]\\d*$")) {
                throw new IllegalArgumentException("양수가 아닌 값입니다.");
            }
        }

    }
}
