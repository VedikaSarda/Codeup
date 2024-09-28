/*
 * Task : Number System Converter and operation
 *
 * Here is a basic Number System Converter that converts octal numbers between different systems such as  Octal ,Decimal,
 * and Hexadecimal. The code also handles  numbers both positive and negative, as well as large values .
 *
 * Owner Name: Vedika Sarda
 * Date of Creation: September 27, 2024
 */
package NumberSystemConversion;

public class OctalConversion {
    // Validate the octal string
    public static boolean isValidOctal(String octal) {
        if (octal == null || octal.isEmpty()) {
            return false; // Invalid if empty or null
        }
        for (char ch : octal.toCharArray()) {
            if (ch < '0' || ch > '7') {
                return false; // Contains invalid character
            }
        }
        return true; // Valid octal string
    }

    // Convert octal string to decimal
    public static String octalToDecimal(String octal) {
        // Validate input
        if (!isValidOctal(octal)) {
            return Constant.INVALID_OCTAL_INPUT;
        }

        long decimal = 0;
        int length = octal.length();
        long base = 1; // This will represent 8^0, 8^1, 8^2, etc.

        // Convert octal to decimal
        for (int i = length - 1; i >= 0; i--) {
            int currentDigit = octal.charAt(i) - '0';
            // Check for overflow before adding
            if (decimal > (Long.MAX_VALUE - currentDigit) / base) {
                return Constant.OVERFLOW_CONDITION;
            }
            decimal += currentDigit * base;
            base *= 8; // Update base for the next position (8^n)
        }

        return String.valueOf(decimal); // Return as string
    }

    // octal to binary
    // Method to convert a single octal digit to a 3-bit binary string
    public static String octalDigitToBinary(char octalDigit) {
        switch (octalDigit) {
            case '0': return "000";
            case '1': return "001";
            case '2': return "010";
            case '3': return "011";
            case '4': return "100";
            case '5': return "101";
            case '6': return "110";
            case '7': return "111";
            default: System.out.println(Constant.INVALID_OCTAL_INPUT + octalDigit);
        }
        return " ";
    }
    public static String octalToBinary(String octal) {
        boolean isNegative = false;
        int startIdx = 0;

        // Step 1: Handle negative numbers
        if (octal.charAt(0) == '-') {
            isNegative = true;
            startIdx = 1;  // Start processing after the '-' sign
        }

        // Step 2: Convert each octal digit to its corresponding binary
        String binary = "";
        for (int i = startIdx; i < octal.length(); i++) {
            char octalDigit = octal.charAt(i);

            // Validate if the character is a valid octal digit (0-7)
            if (octalDigit < '0' || octalDigit > '7') {
                System.out.println(Constant.INVALID_OCTAL_INPUT + octalDigit);
            }

            // Convert the current octal digit to its 3-bit binary equivalent
            binary += octalDigitToBinary(octalDigit);
        }
        // Step 4: Return the binary result, adding '-' for negative values
        if (isNegative) {
            return "-" + binary;
        } else {
            return binary;
        }
    }


    // octal to hexadecimal
    public static String octalToHexadecimal(String octal) {
        boolean isNegative = false;
        int startIdx = 0;

        // Step 1: Handle negative numbers
        if (octal.charAt(0) == '-') {
            isNegative = true;
            startIdx = 1;  // Skip the negative sign
        }

        // Step 2: Convert octal to binary
        String binary = octalToBinary(octal.substring(startIdx));

        // Step 3: Pad binary to make its length a multiple of 4
        binary = BinaryConversion.padBinary(binary);

        // Step 4: Convert binary to hexadecimal
        String hex = (BinaryConversion.binaryToHex(binary));

        // Step 5: Return the hexadecimal value, with '-' sign if it was negative
        if (isNegative) {
            return "-" + hex;
        } else {
            return hex;
        }
    }
}
