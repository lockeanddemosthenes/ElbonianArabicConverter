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
    int M = 1000; int C = 100; int X = 10; int I = 1; int F = 400;
    int N = 40; int Y = 4; int D = 500; int L = 50; int V = 5;

    public ElbonianArabicConverter(String number) throws MalformedNumberException, ValueOutOfBoundsException {

        // Rule 1
        if(number.contains("MMMM") || number.contains("CCCC") || number.contains("XXXX") || number.contains("IIII")) {
            throw new MalformedNumberException("Malformed Number Exception.\nRule 1: M, C, X, and I can be repeated up to three times in a row.");
        }

        // Rule 2
        int fCount = 0, nCount = 0, yCount = 0, dCount = 0, lCount = 0, vCount = 0;
        for(int i=0; i < number.length(); i++) {
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
        if(number.contains("F") && number.contains("C")){
            throw new MalformedNumberException("Malformed Number Exception\nRule 3: If F appears, you cannot use C in the number.");
        } else if (number.contains("N") && number.contains("X")) {
            throw new MalformedNumberException("Malformed Number Exception\nRule 4: If N appears, you cannot use X in the number.");
        } else if (number.contains("Y") && number.contains("I")) {
            throw new MalformedNumberException("Malformed Number Exception\nRule 5: If Y appears, you cannot use I in the number.");
        }

        // Rule 6
        int previousVal = 1000, nextVal = 0;
        for(int i=0; i < number.length(); i++) {
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
        for (int i= 0; i< number.length(); i++){
            int currVal = Integer.parseInt(number.(i));
            if ()
        }
        // TODO Fill in the method's body
        return 1;
    }

    /**
     * Converts the number to an Elbonian numeral or returns the current value if it is already in the Elbonian form.
     *
     * @return An Elbonian value
     */
    public String toElbonian() {
        // TODO Fill in the method's body
        return "I";
    }

}
