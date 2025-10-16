package calculator;

public class Application {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        try {
            calculator.execute();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
