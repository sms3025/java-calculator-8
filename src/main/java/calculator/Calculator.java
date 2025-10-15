package calculator;

import utils.UserInputClass;

public class Calculator {
    private String userInputString;
    private Long addSum = 0L;

    public Calculator() {
    }

    public void execute() {
        userInputString = UserInputClass.InputString();
    }
}
