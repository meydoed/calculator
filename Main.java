import java.io.IOException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter an expression: ");
        String input = sc.nextLine();
        System.out.println("result: " + calc(input));
    }


    public static String calc(String input) {
        String strVar1, strAct, strVar2;
        String[] inpArr = input.split(" ");
        strVar1 = inpArr[0];
        strAct = inpArr[1];
        strVar2 = inpArr[2];
        String[] allActsArr = {"+", "*", "-", "/"};
        int iAct = -1;
        for (int i = 0; i < allActsArr.length; i++) {
            if (strAct.equals(allActsArr[i])) {
                iAct = i;
            }
        }
        if (iAct == -1) {
            try {
                throw new UnsupportedOperationException();
            } catch (UnsupportedOperationException e) {
                System.out.println("unsupported operation!");
                System.exit(0);
            }
        }
        int var1 = 0, var2 = 0;
        boolean isRoman = false;
        if (isInt(strVar1) && isInt(strVar2)) {
            var1 = Integer.parseInt(strVar1);
            var2 = Integer.parseInt(strVar2);
        } else if (isRoman(strVar1) && isRoman(strVar2)) {
            isRoman = true;
            var1 = romanToInt(strVar1);
            var2 = romanToInt(strVar2);
        } else {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("only roman or only arabic!");
                System.exit(0);
            }
        }
        if (var1 > 10 || var2 > 10 || var1 < 1 || var2 < 1) {
            try {
                throw new IOException();
            } catch (IOException e) {
                System.out.println("incorrect input: less than 1 or more than 10!");
                System.exit(0);
            }
        }
        int out = 0;
        switch (iAct) {
            case (0):
                out = var1 + var2;
                break;
            case (1):
                out = var1 * var2;
                break;
            case (2):
                out = var1 - var2;
                break;
            case (3):
                out = var1 / var2;
                break;
        }
        if (isRoman) {
            if (out < 1) {
                try {
                    throw new IOException();
                } catch (IOException e) {
                    System.out.println("output less than 1 for roman!");
                    System.exit(0);
                }
            }
            return intToRoman(out);
        } else return Integer.toString(out);
    }


    public static int romanToInt(String roman) {
        String[] romanArr = roman.split("");
        int sum = 0;
        for (int i = 0; i < romanArr.length; i++) {
            switch (romanArr[i]) {
                case ("I"):
                    if (i < (romanArr.length - 1) &&
                            !romanArr[i + 1].equals("I")) {
                        sum -= 1;
                        break;
                    }
                    sum += 1;
                    break;
                case ("V"):
                    sum += 5;
                    break;
                case ("X"):
                    sum += 10;
                    break;
                default:
                    return -1;
            }
        }
        return sum;
    }


    public static String intToRoman(int arabic) {
        String result  = "";
        while (arabic > 0) {
            if (arabic < 4) {
                for (int i = 0; i < arabic; i++) {
                    result += "I";
                    arabic -= 1;
                    continue;
                }
            } else if (arabic == 4) {
                result += "IV";
                arabic -= 4;
                continue;
            } else if (arabic < 9) {
                result += "V";
                arabic -= 5;
                continue;
            } else if (arabic == 9) {
                result += "IX";
                arabic -= 9;
                continue;
            }else if (arabic < 40) {
                for (int i = 0; i < (arabic / 10); i++) {
                    result += "X";
                    arabic -= 10;
                    continue;
                }
            } else if (arabic == 40) {
                result += "XL";
                arabic -= 40;
                continue;
            } else if (arabic < 90) {
                result += "L";
                arabic -= 50;
                continue;
            } else if (arabic == 90) {
                result += "XC";
                arabic -= 90;
                continue;
            } else if (arabic == 100) {
                result += "C";
                arabic -= 100;
                continue;
            }
        }
        return result;
    }


    public static boolean isInt(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isRoman(String s) throws NumberFormatException {
        char[] charArr = s.toCharArray();
        if (charArr[0] == 'I' ||
                charArr[0] == 'V' ||
                    charArr[0] == 'X' ||
                        charArr[0] == 'L' ||
                            charArr[0] == 'C') {
            return true;
        } else return false;
    }
}