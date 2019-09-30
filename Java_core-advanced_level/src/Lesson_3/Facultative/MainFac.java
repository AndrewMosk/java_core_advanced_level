package Lesson_3.Facultative;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFac {
    public static void main(String[] args) {
        String password = getPassword();
        System.out.println(validatePassword(password));
    }
    private static String getPassword(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input a password: ");
        String password = scanner.next();
        scanner.close();

        return password;
    }

    private static boolean validatePassword(String password){
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])\\S{8,}");
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
