/*
 * Task : Number System Converter and operation
 *
 * Here is a basic Number System Converter that converts binary numbers between different systems such as  Octal ,Decimal,
 * and Hexadecimal. The code also handles  numbers both positive and negative, as well as large values .
 *
 * Owner Name: Vedika Sarda
 * Date of Creation: September 27, 2024
 */
package NumberSystemConversion;
// Converting Binary Number to decimal,octal,hexadecimal
public class BinaryConversion {
    public static boolean isValidBinary(String binary) {
        if (binary == null || binary.isEmpty()) {
            return false; // Invalid if empty or null
        }

        // Check for negative sign
        if (binary.charAt(0) == '-') {
            binary = binary.substring(1); // Remove negative sign for validation
        }

        // Validate if the remaining part is a valid binary number (contains only '0' or '1')
        for (char c : binary.toCharArray()) {
            if (c != '0' && c != '1') {
                return false; // Invalid character
            }
        }
        return true; // Valid binary string
    }

    // Convert binary to decimal with validation
    public static String binaryToDecimal(String binary) {
        // Validate input
        if (!isValidBinary(binary)) {
            return "Invalid binary number.";
        }

        // Check if the number is negative
        boolean isNegative = binary.charAt(0) == '-';
        if (isNegative) {
            binary = binary.substring(1);  // Remove the negative sign for processing
        }

        // Handle the case where the binary number is "0"
        if (binary.equals("0")) {
            return "0";
        }

        // Convert binary to decimal manually
        long decimalValue = 0;
        int length = binary.length();

        // Traverse the binary string from right to left
        for (int i = 0; i < length; i++) {
            if (binary.charAt(length - i - 1) == '1') {  // Only add if the bit is 1
                decimalValue += (1L << i);  // Equivalent to base *= 2 (faster)
            }
            // Overflow detection: If decimal value exceeds the maximum value of long
            if (decimalValue > Long.MAX_VALUE) {
                return "Overflow: Number is too large for conversion.";
            }
        }

        // Return the result as a string and include the negative sign if needed
        return isNegative ? "-" + decimalValue : String.valueOf(decimalValue);
    }


    // Method to add leading zeros to make the length a multiple of 3
    public static String padBinaryString(String binary) {
        int length = binary.length();
        int padding = (3 - (length % 3)) % 3;  // Calculate the number of zeros to pad
        String paddedBinary = "";

        // Append leading zeros if needed
        for (int i = 0; i < padding; i++) {
            paddedBinary += '0';
        }
        paddedBinary += binary;
        return paddedBinary;
    }
    public static int binaryToOctalGroup(String binaryGroup) {
        int octalValue = 0;
        int base = 1;
        // Convert the 3-bit binary group to octal by calculating its decimal value
        for (int i = binaryGroup.length() - 1; i >= 0; i--) {
            octalValue += (binaryGroup.charAt(i) - '0') * base;
            base *= 2;
        }
        return octalValue;
    }
    //binary to octal conversion
    public static String binaryToOctal(String binary) {
        // Step 1: Pad the binary string if needed
        binary = padBinaryString(binary);

        String octal = "";  // Initialize an empty string for the octal number
        // Step 2: Process each group of 3 binary digits
        for (int i = 0; i < binary.length(); i += 3) {
            String binaryGroup = binary.substring(i, i + 3);
            int octalDigit = binaryToOctalGroup(binaryGroup);
            octal += octalDigit;  // Append the octal digit to the result
        }
        return octal;
    }
    // convert binary to hexadecimal
    public static char binaryToHexDigit(String binaryGroup) {
        switch (binaryGroup) {
            case "0000": return '0';
            case "0001": return '1';
            case "0010": return '2';
            case "0011": return '3';
            case "0100": return '4';
            case "0101": return '5';
            case "0110": return '6';
            case "0111": return '7';
            case "1000": return '8';
            case "1001": return '9';
            case "1010": return 'A';
            case "1011": return 'B';
            case "1100": return 'C';
            case "1101": return 'D';
            case "1110": return 'E';
            case "1111": return 'F';
            default: System.out.println(Constant.INVALID_BINARYTOHEX + binaryGroup);
        }
        return ' ';
    }
    // Method to pad a binary string to make its length a multiple of 4
    public static String padBinary(String binary) {
        int paddingLength = (4 - (binary.length() % 4)) % 4;
        for (int i = 0; i < paddingLength; i++) {
            binary = "0" + binary;
        }
        return binary;
    }

    // Method to convert a binary string to a hexadecimal string
    public static String binaryToHex(String binary) {
        String hex = "";
        // Process each group of 4 bits
        for (int i = 0; i < binary.length(); i += 4) {
            String binaryGroup = binary.substring(i, i + 4);
            hex += binaryToHexDigit(binaryGroup);
        }
        return hex;
    }

}




