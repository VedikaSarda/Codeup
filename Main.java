/*
 * Task : Number System Converter and operation
 *
 * Here is a basic Number System Converter that converts numbers between different systems such as Binary, Octal Decimal,
 * and Hexadecimal. The code also handles all the arithmetic operation like(+,-,/,*) on numbers both positive and negative
 * , as well as large values .
 *
 * Owner Name: Vedika Sarda
 * Date of Creation: September 27, 2024
 */
package NumberSystemConversion;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            //All the operation you can perform
            System.out.println(Constant.OPERATION);
            System.out.println(Constant.OPERATION_CHOICE_1);
            System.out.println(Constant.OPERATION_CHOICE_2);
            System.out.println(Constant.OPERATION_CHOICE_3);
            String choice = sc.nextLine(); //input your choice
            if (choice.equals("exit")) break;//type exit
            else {
                switch (choice) {
                    // Number System Conversion
                    case "1":
                        System.out.println(Constant.CONVERSION_EXAMPLE_1);
                        System.out.println(Constant.CONVERSION_EXAMPLE_2);
                        System.out.print(Constant.CONVERSION_COMMAND);
                        String command = sc.nextLine();
                        if (command.isEmpty()) {
                            System.out.println(Constant.COMMAND_EMPTY);
                            return;
                        } else if (command.equals("exit")) break;
                        else {
                            String[] input = command.split(" ");
                            if (input.length < 7) {
                                System.out.println(Constant.COMMAND_FORMAT);
                                continue;
                            }
                            String convertingFrom = input[1].toLowerCase();
                            String convertingTo = input[5].toLowerCase();
                            String digit = input[3];
                            switch (convertingFrom) {
                                // binary to others
                                case "binary":
                                    switch (convertingTo) {
                                        case "decimal":
                                            if (BinaryConversion.isValidBinary(digit)) {
                                                String decimalValue = BinaryConversion.binaryToDecimal(digit);
                                                System.out.println(Constant.DECIMAL_VALUE + decimalValue);
                                            } else {
                                                System.out.println(Constant.INVALID_BINARY_INPUT);
                                            }
                                            break;
                                            case "hexadecimal":
                                            if (BinaryConversion.isValidBinary(digit)) {
                                                String hexValue = BinaryConversion.binaryToHex(digit);
                                                System.out.println(Constant.HEXADECIMAL_VALUE + hexValue);
                                            } else {
                                                System.out.println(Constant.INVALID_BINARY_INPUT);
                                            }
                                            break;
                                            case "octal":
                                            if (BinaryConversion.isValidBinary(digit)) {
                                                String octalValue = BinaryConversion.binaryToOctal(digit);
                                                System.out.println(Constant.OCTAL_VALUE + octalValue);
                                            } else {
                                                System.out.println(Constant.INVALID_BINARY_INPUT);
                                            }
                                            break;
                                            default:
                                            System.out.println(Constant.INVALID_CONVERSION);
                                            break;
                                    }
                                    break;
                                    //decimal to other
                                case "decimal":
                                    switch (convertingTo) {
                                        case "binary":
                                            if (DecimalConversion.isValidDecimal(digit)) {
                                                System.out.println(Constant.BINARY_VALUE + DecimalConversion.decimalToBase(digit,2));
                                            } else {
                                                System.out.println(Constant.INVALID_DECIMAL_INPUT);
                                            }
                                            break;
                                        case "hexadecimal":
                                            if (DecimalConversion.isValidDecimal(digit)) {
                                                System.out.println(Constant.HEXADECIMAL_VALUE + DecimalConversion.decimalToBase(digit,16));
                                            } else {
                                                System.out.println(Constant.INVALID_DECIMAL_INPUT);
                                            }
                                            break;
                                        case "octal":
                                            if (DecimalConversion.isValidDecimal(digit)) {
                                                String octalValue = DecimalConversion.decimalToBase(digit,8);
                                                System.out.println(Constant.OCTAL_VALUE + octalValue);
                                            } else {
                                                System.out.println(Constant.INVALID_DECIMAL_INPUT);
                                            }
                                            break;
                                        default:
                                            System.out.println(Constant.INVALID_CONVERSION);
                                            break;
                                    }
                                    break;
                                    //octal to others
                                case "octal":
                                    switch (convertingTo) {
                                        case "binary":
                                            if (OctalConversion.isValidOctal(digit)) {
                                                String binaryValue = OctalConversion.octalToBinary(digit);
                                                System.out.println(Constant.BINARY_VALUE + binaryValue);
                                            } else {
                                                System.out.println(Constant.INVALID_OCTAL_INPUT);
                                            }
                                            break;
                                        case "decimal":
                                            if (OctalConversion.isValidOctal(digit)) {
                                                String decimalValue = OctalConversion.octalToDecimal(digit);
                                                System.out.println(Constant.DECIMAL_VALUE + decimalValue);
                                            } else {
                                                System.out.println(Constant.INVALID_OCTAL_INPUT);
                                            }
                                            break;
                                        case "hexadecimal":
                                            if (OctalConversion.isValidOctal(digit)) {
                                                String hexValue = OctalConversion.octalToHexadecimal(digit);
                                                System.out.println(Constant.HEXADECIMAL_VALUE + hexValue);
                                            } else {
                                                System.out.println(Constant.INVALID_OCTAL_INPUT);
                                            }
                                            break;
                                        default:
                                            System.out.println(Constant.INVALID_CONVERSION);
                                            break;
                                    }
                                    break;
                                    //hexadecimal to others
                                case "hexadecimal":
                                    switch (convertingTo) {
                                        case "binary":
                                            if (HexadecimalConversion.isValidHex(digit)) {
                                                String binaryValue = HexadecimalConversion.hexToBinary(digit);
                                                System.out.println(Constant.BINARY_VALUE + binaryValue);
                                            } else {
                                                System.out.println(Constant.INVALID_HEXADECIMAL_INPUT);
                                            }
                                            break;
                                            case "decimal":
                                            if (HexadecimalConversion.isValidHex(digit)) {
                                                String decimalValue = HexadecimalConversion.hexToDecimal(digit);
                                                System.out.println(Constant.DECIMAL_VALUE + decimalValue);
                                            } else {
                                                System.out.println(Constant.INVALID_HEXADECIMAL_INPUT);
                                            }
                                            break;
                                            case "octal":
                                            if (HexadecimalConversion.isValidHex(digit)) {
                                                String octalValue = HexadecimalConversion.hexToOctal(digit);
                                                System.out.println(Constant.OCTAL_VALUE + octalValue);
                                            } else {
                                                System.out.println(Constant.INVALID_HEXADECIMAL_INPUT);
                                            }
                                            break;
                                            default:
                                            System.out.println(Constant.INVALID_CONVERSION);
                                            break;
                                    }
                                    break;
                                    default:
                                    System.out.println(Constant.INVALID_INPUT);
                                    break;
                            }
                        }
                        break;
                        // arithmetic operations
                    case "2":
                        // Get the expression input in the form "1010+1010" or "10+4ad"
                        System.out.println(Constant.ARITHMETIC_EXAMPLE1);
                        String expression = sc.nextLine();
                        if (expression.isEmpty()) {
                            System.out.println(Constant.INVALID_EXPRESSION1);
                            return;
                        }
                        // CONVERT THE INPUT TO ARRAY AND SPLIT IT BY SPACE
                        String[] parts = expression.split(" ");

                        if (parts.length > 3) {
                            System.out.println(Constant.INVALID_EXPRESSION2);
                            return;
                        }
                        String num1 = parts[0].trim();
                        String num2 = parts[2].trim();
                        String operator = parts[1].trim();
                        System.out.println(Constant.INPUT1_NUMBERSYSTEM);
                        String system1 = sc.nextLine().toUpperCase();
                        System.out.println(Constant.INPUT2_NUMBERSYSTEM);
                        String system2 = sc.nextLine().toUpperCase();
                        // Convert to enum
                        NumberSystem ns1 = NumberSystem.valueOf(system1);
                        NumberSystem ns2 = NumberSystem.valueOf(system2);
                        // Convert both numbers to decimal
                        String decimalNum1 = (ArithmeticOperations.convertToDecimal(num1, ns1));
                        String decimalNum2 = (ArithmeticOperations.convertToDecimal(num2, ns2));
                        // Perform the arithmetic operation
                        String resultDecimal = ArithmeticOperations.performArithmetic(decimalNum1, decimalNum2, operator);
                        // Ask the user for the desired output system
                        System.out.println(Constant.RESULT_NUMBERSYSTEM);
                        String outputSystem = sc.nextLine().toUpperCase();
                        // Convert the result back to the desired number system
                        NumberSystem outputNs = NumberSystem.valueOf(outputSystem);
                        String result = ArithmeticOperations.convertFromDecimal(resultDecimal, outputNs);
                        System.out.println(Constant.RESULT + result);
                        break;
                        default:
                        System.out.println(Constant.INVALID_CHOICE);
                        break;
                }
            }
        }
    }
}
