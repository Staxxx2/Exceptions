import java.util.Scanner;

public class Exception2hw4 {
    public static void main(String[] args) {
        try {
            String input = getInput();
            System.out.println("Вы ввели,: " + input);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Пустые строки вводить нельзя!.");
        }
    }

    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Ввод не может быть пустым");
        }
        return input;
    }
}
