/*
 * Task : Number System Converter and operation
 *
 * Here is a basic Number System Converter that converts hexadecimal numbers between different systems such as  Octal ,Decimal,
 * and Hexadecimal. The code also handles  numbers both positive and negative, as well as large values .
 *
 * Owner Name: Vedika Sarda
 * Date of Creation: September 27, 2024
 */
package NumberSystemConversion;

import java.util.function.LongToIntFunction;

public class HexadecimalConversion {
    // Convert a hexadecimal character to its decimal value
    public static int hexCharToDecimal(char hexChar) {
        if (hexChar >= '0' && hexChar <= '9') {
            return hexChar - '0';
        } else if (hexChar >= 'A' && hexChar <= 'F') {
            return hexChar - 'A' + 10;
        } else if (hexChar >= 'a' && hexChar <= 'f') {
            return hexChar - 'a' + 10;
        }
        return -1; // Invalid hex character
    }

    // Validate the hexadecimal input (returns true if valid)
    public static boolean isValidHex(String hex) {
        if (hex.isEmpty() || hex.equals("-")) {
            return false; // Empty or just a negative sign is invalid
        }
        int startIndex = hex.charAt(0) == '-' ? 1 : 0; // Check if it's negative
        for (int i = startIndex; i < hex.length(); i++) {
            if (hexCharToDecimal(hex.charAt(i)) == -1) {
                return false; // Invalid character found
            }
        }
        return true;
    }

    // Convert hexadecimal to decimal, handling negative numbers and large inputs
    public static String hexToDecimal(String hex) {
        if (hex.isEmpty()) {
            return "0";
        }
        boolean isNegative = hex.charAt(0) == '-';
        int startIndex = isNegative ? 1 : 0;

        long decimal = 0;
        long base = 1; // 16^0 = 1

        // Convert hexadecimal to decimal
        for (int i = hex.length() - 1; i >= startIndex; i--) {
            int value = hexCharToDecimal(hex.charAt(i));
            if (value == -1) return Constant.INVALID_HEXADECIMAL_INPUT; // Handle invalid characters
            decimal += value * base;
            base *= 16;
        }

        return isNegative ? "-" + decimal : Long.toString(decimal);
    }

    // Convert a hexadecimal character to binary
    public static String hexCharToBinary(char hexChar) {
        switch (hexChar) {
            case '0': return "0000";
            case '1': return "0001";
            case '2': return "0010";
            case '3': return "0011";
            case '4': return "0100";
            case '5': return "0101";
            case '6': return "0110";
            case '7': return "0111";
            case '8': return "1000";
            case '9': return "1001";
            case 'A': case 'a': return "1010";
            case 'B': case 'b': return "1011";
            case 'C': case 'c': return "1100";
            case 'D': case 'd': return "1101";
            case 'E': case 'e': return "1110";
            case 'F': case 'f': return "1111";
            default: return ""; // Invalid character
        }
    }

    // Convert hexadecimal to binary, handling negative numbers
    public static String hexToBinary(String hex) {
        if (hex.isEmpty()) {
            return "0";
        }

        boolean isNegative = hex.charAt(0) == '-';
        int startIndex = isNegative ? 1 : 0;

        StringBuilder binaryResult = new StringBuilder();

        for (int i = startIndex; i < hex.length(); i++) {
            String binaryValue = hexCharToBinary(hex.charAt(i));
            if (binaryValue.isEmpty()) {
                return "Invalid Hex"; // Handle invalid characters
            }
            binaryResult.append(binaryValue);
        }

        // Remove leading zeros
        while (binaryResult.length() > 1 && binaryResult.charAt(0) == '0') {
            binaryResult.deleteCharAt(0);
        }

        return isNegative ? "-" + binaryResult.toString() : binaryResult.toString();
    }

    // Convert binary to octal
    public static String binaryToOctal(String binary) {
        if (binary.isEmpty()) return "0";
        StringBuilder octal = new StringBuilder();
        boolean isNegative = binary.charAt(0) == '-';
        int startIndex = isNegative ? 1 : 0;

        int len = binary.length();
        int padLength = (3 - (len - startIndex) % 3) % 3;

        // Pad with leading zeros to make the length a multiple of 3
        String paddedBinary = "0".repeat(padLength) + binary.substring(startIndex);

        for (int i = 0; i < paddedBinary.length(); i += 3) {
            String binaryChunk = paddedBinary.substring(i, i + 3);
            int octalDigit = Integer.parseInt(binaryChunk, 2);
            octal.append(octalDigit);
        }

        return isNegative ? "-" + octal.toString() : octal.toString();
    }

    // Convert hexadecimal to octal, handling negative numbers
    public static String hexToOctal(String hex) {
        String binary = hexToBinary(hex);
        if (binary.equals(Constant.INVALID_HEXADECIMAL_INPUT)) {
            return Constant.INVALID_HEXADECIMAL_INPUT;
        }
        return binaryToOctal(binary);
    }
}
