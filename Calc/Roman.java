package Calc;

public class Roman {
    private static char[] romanSymbols = {' ', 'V', 'I', 'X', '/', '+', '-', '*'};
    public void romanCount(String s) {
        if (!checkingForOnlyRomanSymbols(s)) {
            throw new NumberFormatException();
        }
        if (!methodForCorrectValueOfInputNumbers(s)) {
            throw new NumberFormatException();
        } else {
            System.out.println(getResult(s));
        }
    }

    public boolean checkingForOnlyRomanSymbols(String s) {
        try {
            int counter = 0;
            for (int i = 0; i < s.length(); ) {
                for (int j = 0; j < romanSymbols.length; j++) {
                    if (s.charAt(i) == romanSymbols[j]) {
                        i++;
                        j = 0;
                    }
                    if (s.charAt(i) != romanSymbols[j] && j == romanSymbols.length - 1) {
                        counter++;
                        if (counter > 2) {
                            return false;
                        }
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException e) {
        }
        return true;
    }

    public boolean methodForCorrectValueOfInputNumbers(String s) {
        int counter = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                counter++;
                if (s.charAt(i) != 'I') throw new NumberFormatException();
                if (counter > 3) throw new NullPointerException();
            }
            if (translateToArabic(s.charAt(i)) > translateToArabic(s.charAt(i + 1)) && translateToArabic(s.charAt(i + 1)) != 0) {
                if (s.charAt(i) == 'X') throw new IllegalArgumentException();
            }
            if (translateToArabic(s.charAt(i)) < translateToArabic(s.charAt(i + 1)) && translateToArabic(s.charAt(i)) != 0) {
                if (s.charAt(i) != 'I') throw new IllegalCallerException();
            }
        }
        return true;
    }

    public int translateToArabic(char c) {
        switch (c) {
            case ('X'):
                return 10;
            case ('I'):
                return 1;
            case ('V'):
                return 5;
        }
        return 0;
    }

    public String translateToRomanFirst(int i) {
        String result = "";
        if (i == 1) result = "X";
        if (i == 2) result = "XX";
        if (i == 3) result = "XXX";
        if (i == 4) result = "XL";
        if (i == 5) result = "L";
        if (i == 6) result = "LX";
        if (i == 7) result = "LXX";
        if (i == 8) result = "LXXX";
        if (i == 9) result = "XC";
        return result;
    }

    public String translateToRomanSecond(int i) {
        String result = "";
        if (i == 1) result = "I";
        if (i == 2) result = "II";
        if (i == 3) result = "III";
        if (i == 4) result = "IV";
        if (i == 5) result = "V";
        if (i == 6) result = "VI";
        if (i == 7) result = "VII";
        if (i == 8) result = "VIII";
        if (i == 9) result = "IX";
        return result;
    }

    public String getResult(String s) {
        int signPlace = 0, result = 0, first = 0, second = 0;
        char sign = getSignOfExpression(s);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == sign) {
                signPlace = i;
            }
        }
        for (int i = 0; i < signPlace; i++) {
            if (translateToArabic(s.charAt(i + 1)) == 10) {
                first = 9;
                break;
            }
            if (translateToArabic(s.charAt(i + 1)) == 5) {
                first = 4;
                break;
            }
            if (translateToArabic(s.charAt(i)) >= translateToArabic(s.charAt(i + 1))) {
                first += translateToArabic(s.charAt(i));
            }
        }
        int counter = 0;
        int place = 0;
        for (int j = signPlace + 1; j < s.length(); j++) {
            if (translateToArabic(s.charAt(j)) != 0) {
                counter++;
                place = j;
            }
        }
        if (counter == 1) {
            second = translateToArabic(s.charAt(place));
        } else {
            for (int i = signPlace + 1; i < s.length(); i++) {
                if (translateToArabic(s.charAt(i)) == 10 && translateToArabic(s.charAt(i - 1)) != 0) {
                    second = 9;
                    break;
                }
                if (translateToArabic(s.charAt(i)) == 5 && translateToArabic(s.charAt(i - 1)) != 0) {
                    second = 4;
                    break;
                }
                if (translateToArabic(s.charAt(i - 1)) >= translateToArabic(s.charAt(i))) {
                    second += translateToArabic(s.charAt(i - 1));
                    if (i == s.length() - 1) second += translateToArabic(s.charAt(i));
                }
            }
        }
        if (sign == '+') result = first + second;
        else if (sign == '-') {
            if (second >= first) {
                System.out.println("Roman numbers can't be lower than 1!");
            }
            result = first - second;
        } else if (sign == '*') result = first * second;
        else if (sign == '/') {
            if (second >= first) {
                System.out.println("Roman numbers can't be lower than 1!");
            }
            result = first / second;
        }
        if (result < 10) return translateToRomanSecond(result);
        else if (result >= 10 && result < 100) {
            return translateToRomanFirst(result / 10) + translateToRomanSecond(result%10);
        } else return "C";
    }

    public char getSignOfExpression(String s) {
        int c = 0;
        char temp = 'Z';
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == temp) {
                throw new NumberFormatException();
            }
            if (s.charAt(i) == '+') {
                if (c >= 1) {
                    throw new NumberFormatException();
                }
                if (c == 0) {
                    temp = '+';
                    c++;
                }
            }
            if (s.charAt(i) == '-') {
                if (c >= 1) {
                    throw new NumberFormatException();
                }
                if (c == 0) {
                    temp = '-';
                    c++;
                }
            }
            if (s.charAt(i) == '*') {
                if (c >= 1) {
                    throw new NumberFormatException();
                }
                if (c == 0) {
                    temp = '*';
                    c++;
                }
            }
            if (s.charAt(i) == '/') {
                if (c >= 1) {
                    throw new NumberFormatException();
                }
                if (c == 0) {
                    temp = '/';
                    c++;
                }
            }
        }
        if (temp == 'Z') {
            throw new NumberFormatException();
        }
        return temp;
    }
}
