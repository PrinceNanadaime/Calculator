package Calc;

public class Arabic {
    private static char[] arabicSymbols = {' ', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '/', '+', '-', '*'};
    public void arabicCount(String s){
        if(!checkingForOnlyArabicSymbols(s)) {
            throw new NumberFormatException();
        }
        else if (!methodForCorrectValueOfInputNumbers(s)){
            throw new NumberFormatException();
        }
        else {
            System.out.println(getResult(s));
        }
    }

    public boolean verifyForDigit(char ch){
        try{
            Integer.parseInt(String.valueOf(ch));
        }
        catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public static boolean checkingForOnlyArabicSymbols(String s){
        try {
            int counter = 0;
            for (int i = 0; i < s.length(); ) {
                for (int j = 0; j < arabicSymbols.length; j++) {
                    if (s.charAt(i) == arabicSymbols[j]) {
                        i++;
                    }
                    if (s.charAt(i) != arabicSymbols[j] && j == arabicSymbols.length - 1) {
                        counter++;
                        if (counter > 2) {
                            return false;
                        }
                    }
                }
            }
        }
        catch (StringIndexOutOfBoundsException e){
        }
        return true;
    }

    public boolean methodForCorrectValueOfInputNumbers(String s){
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if(verifyForDigit(s.charAt(i))){
                count++;
                if(count > 4){
                    throw new NumberFormatException();
                }
            }
            if(s.charAt(i) == '0'){
                if(s.charAt(i-1) != '1'){
                    throw new NumberFormatException();
                }
            }
            if(i==s.length() - 2){
                if(verifyForDigit(s.charAt(i)) && verifyForDigit(s.charAt(i+1))){
                    if(s.charAt(i) != '1'){
                        throw new NumberFormatException();
                    }
                    if(s.charAt(i+1) != '0'){
                        throw new NumberFormatException();
                    }
                }
            }
        }
        return true;
    }

    public int getResult(String s){
        int firstNumber, secondNumber = 1;
        char sign = getSignOfExpression(s);
        if(verifyForDigit(s.charAt(1))) {
            firstNumber = 10;
            for (int i = 2; i < s.length() - 1 ; i++) {
                if(verifyForDigit(s.charAt(i)) && verifyForDigit(s.charAt(i+1))) {
                    secondNumber = 10;
                    break;
                }
                if(i == s.length() - 2){
                    secondNumber = Integer.parseInt(String.valueOf(s.charAt(i+1)));
                    break;
                }
            }
        }
        else {
            firstNumber = Integer.parseInt(String.valueOf(s.charAt(0)));
            for (int i = 1; i < s.length() - 1 ; i++) {
                if(verifyForDigit(s.charAt(i)) && verifyForDigit(s.charAt(i+1))) {
                    secondNumber = 10;
                    break;
                }
                if(i == s.length() - 2){
                    secondNumber = Integer.parseInt(String.valueOf(s.charAt(i+1)));
                }
            }
        }
        if(sign == '+') return firstNumber + secondNumber;
        else if(sign == '-') return firstNumber - secondNumber;
        else if(sign == '*') return firstNumber * secondNumber;
        else if(sign == '/') return firstNumber / secondNumber;
        else return  0;
    }

    public char getSignOfExpression(String s){
        int c = 0;
        char temp = 'Z';
        for (int i = 0; i < s.length(); i++){
            if(s.charAt(i) == temp){
                throw new NumberFormatException();
            }
            if(s.charAt(i) == '+') {
                if(c >= 1){
                    throw new NumberFormatException();
                }
                if(c == 0){
                    temp = '+';
                    c++;
                }
            }
            if(s.charAt(i) == '-') {
                if(c >= 1){
                    throw new NumberFormatException();
                }
                if(c == 0){
                    temp = '-';
                    c++;
                }
            }
            if(s.charAt(i) == '*') {
                if(c >= 1){
                    throw new NumberFormatException();
                }
                if(c == 0){
                    temp = '*';
                    c++;
                }
            }
            if(s.charAt(i) == '/') {
                if(c >= 1){
                    throw new NumberFormatException();
                }
                if(c == 0){
                    temp = '/';
                    c++;
                }
            }
        }
        if(temp == 'Z'){
            throw new NumberFormatException();
        }
        return temp;
    }
}
