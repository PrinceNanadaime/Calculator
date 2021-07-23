package Calc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if(input.isEmpty()){
            System.out.println("You input nothing!!");
            throw new NullPointerException();
        }
        try {
            Integer.parseInt(String.valueOf(input.charAt(0)));
            Arabic arabicExpression = new Arabic();
            arabicExpression.arabicCount(input);
        }
        catch (NumberFormatException e){
            Roman romanExpression = new Roman();
            romanExpression.romanCount(input);
        }
    }
}
