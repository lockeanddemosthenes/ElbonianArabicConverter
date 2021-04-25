package converter;

import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;

/**
 * This class implements a converter that takes a string that represents a number in either the
 * Elbonian or Arabic numeral form. This class has methods that will return a value in the chosen form.
 *
 * @version 3/18/17
 */
public class ElbonianArabicConverter {

    // A string that holds the number (Elbonian or Arabic) you would like to convert
    private final String number;

    /**
     * Constructor for the ElbonianArabic class that takes a string. The string should contain a valid
     * Elbonian or Arabic numeral. The String can have leading or trailing spaces. But there should be no
     * spaces within the actual number (ie. "9 9" is not ok, but " 99 " is ok). If the String is an Arabic
     * number it should be checked to make sure it is within the Elbonian number systems bounds. If the
     * number is Elbonian, it must be a valid Elbonian representation of a number.
     *
     * @param number A string that represents either a Elbonian or Arabic number.
     * @throws MalformedNumberException Thrown if the value is an Elbonian number that does not conform
     * to the rules of the Elbonian number system. Leading and trailing spaces should not throw an error.
     * @throws ValueOutOfBoundsException Thrown if the value is an Arabic number that cannot be represented
     * in the Elbonian number system.
     */

    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // Rule 1
        if (number.contains("MMMM") || number.contains("CCCC") || number.contains("XXXX") || number.contains("IIII")) {
            throw new MalformedNumberException("Malformed Number Exception.\nRule 1: M, C, X, and I can be repeated up to three times in a row.");
        }

        // Rule 2
        int fCount = 0, nCount = 0, yCount = 0, dCount = 0, lCount = 0, vCount = 0;
        for (int i = 0; i < number.length(); i++) {
            switch (number.charAt(i)) {
                case 'F':
                    fCount++;
                    break;
                case 'N':
                    nCount++;
                    break;
                case 'Y':
                    yCount++;
                    break;
                case 'D':
                    dCount++;
                    break;
                case 'L':
                    lCount++;
                    break;
                case 'V':
                    vCount++;
                    break;
                default:
                    break;
            }

            if (fCount >= 2 || nCount >= 2 || yCount >= 2 || dCount >= 2 || lCount >= 2 || vCount >= 2) {
                throw new MalformedNumberException("Malformed Number Exception\nRule 2: F, N, Y, D, L, and V can each only be used once in a number.");
            }
        }

        // Rule 3-5
        if (number.contains("F") && number.contains("C")) {
            throw new MalformedNumberException("Malformed Number Exception\nRule 3: If F appears, you cannot use C in the number.");
        } else if (number.contains("N") && number.contains("X")) {
            throw new MalformedNumberException("Malformed Number Exception\nRule 4: If N appears, you cannot use X in the number.");
        } else if (number.contains("Y") && number.contains("I")) {
            throw new MalformedNumberException("Malformed Number Exception\nRule 5: If Y appears, you cannot use I in the number.");
        }

        // Rule 6
        int previousVal = 1000, nextVal = 0;
        for (int i = 0; i < number.length(); i++) {
            switch (number.charAt(i)) {
                case 'M':
                    nextVal = 1000;
                    break;
                case 'D':
                    nextVal = 500;
                    break;
                case 'F':
                    nextVal = 400;
                    break;
                case 'C':
                    nextVal = 100;
                    break;
                case 'L':
                    nextVal = 50;
                    break;
                case 'N':
                    nextVal = 40;
                    break;
                case 'X':
                    nextVal = 10;
                    break;
                case 'V':
                    nextVal = 5;
                    break;
                case 'Y':
                    nextVal = 4;
                    break;
                case 'I':
                    nextVal = 1;
                    break;
                default:
                    break;
            }

            if (nextVal > previousVal) {
                throw new MalformedNumberException("Malformed Number Exception\nRule 6: Numbers are represented by the letters from the greatest value down to the lowest value.");
            } else {
                previousVal = nextVal;
            }
        }
        this.number = number;
    }

    /**
     * Converts the number to an Arabic numeral or returns the current value as an int if it is already
     * in the Arabic form.
     *
     * @return An arabic value
     */
    public int toArabic() {
        int tempValue = 0;
        for (int i = 0; i < number.length(); i++) {
            switch (number.charAt(i)) {
                case 'M':
                    tempValue += 1000;
                    break;
                case 'D':
                    tempValue += 500;
                    break;
                case 'F':
                    tempValue += 400;
                    break;
                case 'C':
                    tempValue += 100;
                    break;
                case 'L':
                    tempValue += 50;
                    break;
                case 'N':
                    tempValue += 40;
                    break;
                case 'X':
                    tempValue += 10;
                    break;
                case 'V':
                    tempValue += 5;
                    break;
                case 'Y':
                    tempValue += 4;
                    break;
                case 'I':
                    tempValue += 1;
                    break;
                default:
                    break;
            }
        }
        return tempValue;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() throws ValueOutOfBoundsException{
        String tempString = "";
        int x = Integer.parseInt(number);
        while (x > 0) {
            if (x >= 1000) {
                tempString += "M";
                x -= 1000;
            } else if (x >= 500 && x < 1000) {
                tempString += "D";
                x -= 600;
            } else if (x >= 400 && x < 500) {
                tempString += "F";
                x -= 400;
            } else if (x >= 100 && x < 400) {
                tempString += "C";
                x -= 100;
            } else if (x >= 50 && x < 100) {
                tempString += "L";
                x -= 50;
            } else if (x >= 40 && x < 50) {
                tempString += "N";
                x -= 40;
            } else if (x >= 10 && x < 40) {
                tempString += "X";
                x -= 10;
            } else if (x >= 5 && x < 10) {
                tempString += "V";
                x -= 5;
            } else if (x >= 4 && x < 5) {
                tempString += "Y";
                x -= 4;
            } else if (x >= 1 && x < 4) {
                tempString += "I";
                x -= 1;
            }
        }
        return tempString;
    }
}



