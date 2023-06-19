import java.util.Scanner;

public class Exception2hw1 {

    public static void main(String[] args) {
        float userNumber = getFractionalInput();
        System.out.println("Пожалуйста, введите допустимое дробное число: " + userNumber);
    }

    public static float getFractionalInput() {
        Scanner scanner = new Scanner(System.in);
        float input = 0.0f;
        while (true) {
            System.out.print("Please enter a fractional number: ");
            if (scanner.hasNextFloat()) {
                input = scanner.nextFloat();
                break;
            } else {
                System.out.println("Неверный Ввод. Пожалуйста, введите допустимое дробное число");
                scanner.next();
            }
        }
        return input;
    }
}