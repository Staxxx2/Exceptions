import java.io.*;
import java.util.Scanner;

public class Phonebook {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Запрашивать у пользователя данные
        System.out.print(
                "Введите данные через пробел (фамилия, имя, отчество, дата рождения (дд.мм.гггг), номер телефона, пол(m,f)): ");
        String input = scanner.nextLine();

        // Разделяем входные части
        String[] parts = input.split("\\s+");

        // Проверка длины ввода
        if (parts.length != 6) {
            System.out.println("Ошибка: Ожидается 6 записей данных, получено " + parts.length);
            return;
        }

        // Парсим и проверяем ввод
        String lastName = parts[0];
        String firstName = parts[1];
        String patronymic = parts[2];
        String dateOfBirthStr = parts[3];
        String phoneNumberStr = parts[4];
        String genderStr = parts[5];

        // Проверяем дату рождения
        DateOfBirth dateOfBirth;
        try {
            dateOfBirth = new DateOfBirth(dateOfBirthStr);
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка: неверный формат даты рождения: " + ex.getMessage());
            return;
        }

        // Проверяем номер телефона
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(phoneNumberStr);
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка: неверный формат номера телефона: " + ex.getMessage());
            return;
        }

        // Проверяем пол
        Gender gender;
        try {
            gender = Gender.fromString(genderStr);
        } catch (IllegalArgumentException ex) {
            System.out.println("Ошибка: неверный формат пола: " + ex.getMessage());
            return;
        }

        // Записьт в файл
        String fileName = lastName + ".txt";
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter;
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);

            String dataString = lastName + " " + firstName + " " + patronymic + " " + dateOfBirth.toString() + " "
                    + phoneNumber + " " + gender.toString();
            bufferedWriter.write(dataString);
            bufferedWriter.newLine();

            bufferedWriter.close();
            fileWriter.close();

            System.out.println("контакт сохраняется в файл: " + fileName);
        } catch (IOException ex) {
            System.out.println("Ошибка: не удалось записать данные в файл " + fileName + ": " + ex.getMessage());
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException ex2) {
                    System.out.println("ошибка: Не удалось закрыть файл записи: " + ex2.getMessage());
                }
            }
        }
    }
}

class DateOfBirth {
    private final int day;
    private final int month;
    private final int year;

    DateOfBirth(String dateString) {
        String[] parts = dateString.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат");
        }

        day = Integer.parseInt(parts[0]);
        month = Integer.parseInt(parts[1]);
        year = Integer.parseInt(parts[2]);
    }

    @Override
    public String toString() {
        return String.format("%02d.%02d.%04d", day, month, year);
    }
}

enum Gender {
    MALE("m"),
    FEMALE("f");

    private final String abbreviation;

    Gender(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    static Gender fromString(String s) throws IllegalArgumentException {
        for (Gender gender : Gender.values()) {
            if (gender.abbreviation.equalsIgnoreCase(s)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Недопустимая аббревиатура пола: " + s);
    }

    @Override
    public String toString() {
        return abbreviation;
    }
}
