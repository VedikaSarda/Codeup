/*
 * Task : Number System Converter and operation
 *
 * Here is a basic Number System Converter that converts decimal numbers between different systems such as  Octal ,Decimal,
 * and Hexadecimal. The code also handles  numbers both positive and negative, as well as large values .
 *
 * Owner Name: Vedika Sarda
 * Date of Creation: September 27, 2024
 */
package NumberSystemConversion;

public class DecimalConversion {
    // Method to check if Decimal String is valid
    public static boolean isValidDecimal(String decimal) {
        if (decimal.isEmpty()) {
            return false;
        }

        int startIndex = 0;

        // Allow leading '-' for negative numbers
        if (decimal.charAt(0) == '-') {
            startIndex = 1; // Skip the first character if it's '-'

            // Ensure the string isn't just "-"
            if (decimal.length() == 1) {
                return false;
            }
        }

        // Check if all remaining characters are digits
        for (int i = startIndex; i < decimal.length(); i++) {
            char ch = decimal.charAt(i);
            if (ch < '0' || ch > '9') {
                return false;  // Invalid character found
            }
        }

        return true;
    }
    // Main conversion function: Convert decimal string to any base (binary, octal, hexadecimal)
    public static String decimalToBase(String decimal, int base) {
        // Check if the number is negative and remember it
        boolean isNegative = decimal.charAt(0) == '-';
        if (isNegative) {
            decimal = decimal.substring(1); // Remove the negative sign for further processing
        }

        // Character set for hexadecimal digits
        char[] baseChars = "0123456789ABCDEF".toCharArray();
        StringBuilder result = new StringBuilder();

        // Convert decimal string to an array of digits
        int[] decimalDigits = new int[decimal.length()];
        for (int i = 0; i < decimal.length(); i++) {
            decimalDigits[i] = decimal.charAt(i) - '0';  // Convert each char to its int value
        }

        // Perform repeated division by the base and store remainders
        while (!isZero(decimalDigits)) {
            int remainder = divideByBase(decimalDigits, base);
            result.insert(0, baseChars[remainder]);  // Insert remainder at the front of the result
        }

        // If the number is zero, return "0"
        if (result.length() == 0) {
            return "0";
        }

        // Append negative sign if the number was negative
        if (isNegative) {
            result.insert(0, '-');
        }

        return result.toString();
    }

    // Helper function: Divide the number (represented as an array of digits) by the base
    private static int divideByBase(int[] decimalDigits, int base) {
        int remainder = 0;

        // Perform manual long division
        for (int i = 0; i < decimalDigits.length; i++) {
            int current = remainder * 10 + decimalDigits[i];  // Carry over remainder
            decimalDigits[i] = current / base;  // Store quotient
            remainder = current % base;  // Update remainder
        }

        return remainder;  // Return remainder (this will be the next digit in the result)
    }

    // Helper function: Check if the number (as an array of digits) is zero
    private static boolean isZero(int[] decimalDigits) {
        for (int digit : decimalDigits) {
            if (digit != 0) {
                return false;
            }
        }
        return true;
    }

}
