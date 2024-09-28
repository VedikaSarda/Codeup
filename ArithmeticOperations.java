/*
 * Task : Number System Converter and operation
 *
 * Here is a basic Number System Converter and operation t.
 *  The code also handles all the arithmetic operation like(+,-,/,*) on numbers both positive and negative, as well as large values .
 *
 * Owner Name: Vedika Sarda
 * Date of Creation: September 27, 2024
 */
package NumberSystemConversion;
import java.util.Scanner;
// enum class of Constant number Systems
 enum NumberSystem {
    BINARY, DECIMAL, OCTAL, HEXADECIMAL
}
public class ArithmeticOperations {
    // convert the number1 ,number2 to Decimal
    public static String convertToDecimal(String number, NumberSystem system) {
        String decimalValue = "";
        switch (system) {
            case BINARY:
                decimalValue = (BinaryConversion.binaryToDecimal(number));
                break;
            case OCTAL:
                decimalValue = OctalConversion.octalToDecimal(number);
                break;
            case HEXADECIMAL:
                decimalValue = HexadecimalConversion.hexToDecimal(number);
                break;
            case DECIMAL:
                decimalValue = number;
                break;
        }
        return decimalValue;
    }
    //Convert the decimal result to any number system
    public static String convertFromDecimal(String decimal, NumberSystem system) {
        switch (system) {
            case BINARY:
                return DecimalConversion.decimalToBase(decimal,2);
            case OCTAL:
                return DecimalConversion.decimalToBase(decimal,8);
            case HEXADECIMAL:
                return DecimalConversion.decimalToBase(decimal,16);
            case DECIMAL:
            default:
                return String.valueOf(decimal);
        }
    }
    //perform Arithmetic operations
    public static String performArithmetic(String num1, String num2, String operation) {
        boolean isNegativeResult = false;

        // Handle negative numbers and signs
        if (num1.charAt(0) == '-' && num2.charAt(0) == '-') {
            num1 = num1.substring(1);
            num2 = num2.substring(1);
            isNegativeResult = operation.equals("+") || operation.equals("*");
        } else if (num1.charAt(0) == '-') {
            num1 = num1.substring(1);
            if (operation.equals("+")) {
                return subtractLargeNumbers(num2, num1); // Subtract as num2 - num1
            } else if (operation.equals("-")) {
                return "-" + addLargeNumbers(num1, num2); // Add and negate result
            }
            isNegativeResult = operation.equals("*");
        } else if (num2.charAt(0) == '-') {
            num2 = num2.substring(1);
            if (operation.equals("+")) {
                return subtractLargeNumbers(num1, num2); // Subtract as num1 - num2
            } else if (operation.equals("-")) {
                return addLargeNumbers(num1, num2); // Add num1 + num2
            }
            isNegativeResult = operation.equals("*");
        }

        String result = "";
        switch (operation) {
            case "+":
                result = addLargeNumbers(num1, num2);
                break;
            case "-":
                result = subtractLargeNumbers(num1, num2);
                break;
            case "*":
                result = multiplyLargeNumbers(num1, num2);
                break;
            case "/":
                result = divideLargeNumbers(num1, num2);
                break;
            default:
                return "Invalid operation";
        }

        return isNegativeResult ? "-" + result : result;
    }

    // Helper method to add large numbers
    public static String addLargeNumbers(String num1, String num2) {
        int maxLength = Math.max(num1.length(), num2.length());
        int[] resultArray = new int[maxLength + 1];
        int carry = 0, idx = maxLength;

        int i1 = num1.length() - 1, i2 = num2.length() - 1;
        while (i1 >= 0 || i2 >= 0 || carry > 0) {
            int digit1 = (i1 >= 0) ? num1.charAt(i1--) - '0' : 0;
            int digit2 = (i2 >= 0) ? num2.charAt(i2--) - '0' : 0;

            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            resultArray[idx--] = sum % 10;
        }

        return arrayToString(resultArray);
    }

    // Helper method to subtract large numbers
    public static String subtractLargeNumbers(String num1, String num2) {
        if (isSmaller(num1, num2)) {
            return "-" + subtractLargeNumbers(num2, num1);
        }

        int[] resultArray = new int[num1.length()];
        int borrow = 0, idx = resultArray.length - 1;

        int i1 = num1.length() - 1, i2 = num2.length() - 1;
        while (i1 >= 0) {
            int digit1 = num1.charAt(i1--) - '0';
            int digit2 = (i2 >= 0) ? num2.charAt(i2--) - '0' : 0;

            int diff = digit1 - digit2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            resultArray[idx--] = diff;
        }

        return arrayToString(resultArray);
    }

    // Helper method to multiply large numbers
    public static String multiplyLargeNumbers(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int[] product = new int[len1 + len2];

        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = mul + product[i + j + 1];
                product[i + j + 1] = sum % 10;
                product[i + j] += sum / 10;
            }
        }

        return arrayToString(product);
    }

    // Helper method to divide large numbers
    public static String divideLargeNumbers(String num1, String num2) {
        if (num2.equals("0")) {
            return "Cannot divide by zero";
        }

        StringBuilder result = new StringBuilder();
        StringBuilder dividend = new StringBuilder();

        for (char digit : num1.toCharArray()) {
            dividend.append(digit);
            int quotient = 0;
            while (greaterOrEqual(dividend.toString(), num2)) {
                dividend = new StringBuilder(subtractLargeNumbers(dividend.toString(), num2));
                quotient++;
            }
            result.append(quotient);
        }

        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        return result.toString();
    }

    // Helper function to check if one number is smaller than another
    private static boolean isSmaller(String num1, String num2) {
        if (num1.length() < num2.length()) return true;
        if (num1.length() > num2.length()) return false;
        return num1.compareTo(num2) < 0;
    }

    // Helper function to compare two large numbers
    private static boolean greaterOrEqual(String num1, String num2) {
        if (num1.length() > num2.length()) return true;
        if (num1.length() < num2.length()) return false;
        return num1.compareTo(num2) >= 0;
    }

    // Helper function to convert an array of digits to string
    private static String arrayToString(int[] arr) {
        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;

        for (int digit : arr) {
            if (digit == 0 && leadingZero) {
                continue;
            }
            leadingZero = false;
            result.append(digit);
        }

        return result.length() == 0 ? "0" : result.toString();
    }
}




