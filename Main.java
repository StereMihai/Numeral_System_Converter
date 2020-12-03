package converter;

import java.util.Scanner;

import static java.lang.Character.getNumericValue;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int sourceRadix = 0;
        int targetRadix = 0;
        if(scanner.hasNextInt()) {
            sourceRadix = scanner.nextInt();
        } else {
            System.out.println("error");
            System.exit(1);
        }
        String number = scanner.next();
        if (scanner.hasNextInt()) {
            targetRadix = scanner.nextInt();
        } else {
            System.out.println("error");
            System.exit(1);
        }

        if(sourceRadix<1 || targetRadix>36) {
            System.out.println("error");
            System.exit(1);
        }
        if(targetRadix<1 || targetRadix>36) {
            System.out.println("error");
            System.exit(1);
        }
        converting(sourceRadix,number,targetRadix);


    }

    public static void converting(int sourceRadix, String number, int targetRadix){


        int indexOfDecimal = number.indexOf(".");


        if (indexOfDecimal == -1) {
            convertingIntegers(sourceRadix, number, targetRadix);
        } else {

            String integerP = number.substring(0, indexOfDecimal);

            String decimalP = number.substring(indexOfDecimal+1);

            String part1 = convertIntegerPart(sourceRadix, integerP, targetRadix);

            String part2 = convertFractionalPart(sourceRadix, decimalP, targetRadix);

            System.out.println(part1 + "." + part2);



        }
    }
    public static String convertFractionalPart(int sourceRadix, String decimalP, int targetRadix) {


        int integerFinalNumber;

        char[] chars = new char[decimalP.length()];

        for (int i = 0; i < decimalP.length(); i++) {
            chars[i] = decimalP.charAt(i);
        }
        int[] numberToConvert = new int[chars.length];
        double finalNumber = 0;

        for (int i = 0; i < chars.length; i++) {
            numberToConvert[i] = getNumericValue(chars[i]);

            finalNumber += numberToConvert[i] / Math.pow(sourceRadix, i + 1);

        }
        int[] ints = new int[5];
        for (int i = 0; i < 5; i++) {
            finalNumber *= targetRadix;
            integerFinalNumber = (int) finalNumber;
            ints[i] = integerFinalNumber;

            finalNumber = finalNumber - integerFinalNumber;

        }

        String xyz = new String();
        char[] chars1 = new char[5];

        for (int i = 0; i < ints.length; i++) {
            chars1[i] = Character.forDigit(ints[i], 36);

            }

        for(int i=0; i <chars1.length; i++) {
            xyz += chars1[i];
        }
        return xyz;
    }

    public static String convertIntegerPart(int sourceRadix, String integerP, int targetRadix) {
        int count = 0;
        int check = 0;
        String part1 = null;
        int integerPart =  convertFromStringToInteger(integerP);
        if (sourceRadix == 1) {
            while (integerPart>0) {

                integerPart = integerPart / 10;
                count++;
            }
            System.out.println(Integer.toString(count, targetRadix));
        } else if (targetRadix == 1) {
            for(int i = 0; i<integerPart; i++) {
                count = 10 * count + 1;
            }
            System.out.println(count);
        } else {
            check = parseInt(integerP, sourceRadix);
           String x = (Integer.toString(check, targetRadix));
            part1 = x;
          // return x;
        }
        return part1;
    }

    public static int convertFromStringToInteger(String number) {

        char[] chars = new char[number.length()];
        for (int i = 0 ; i<number.length(); i++) {
            chars[i] = number.charAt(i);
        }
        int[] numberToConvert = new int[chars.length];
        int finalNumber = 0;
        int x = chars.length;
        for (int i=0; i<chars.length; i++) {
            numberToConvert[i] = getNumericValue(chars[i]);
            finalNumber += numberToConvert[i] * Math.pow(10,x);
            x--;
        }
        finalNumber = finalNumber / 10;
        return finalNumber;
    }

    public static void convertingIntegers(int sourceRadix, String number, int targetRadix) {
        int count = 0;
        int check = 0;
        int numberToConvert = convertFromStringToInteger(number);


        if (sourceRadix == 1) {
            while (numberToConvert>0) {

                numberToConvert = numberToConvert / 10;
                count++;
            }
            System.out.println(Integer.toString(count, targetRadix));
        } else if (targetRadix == 1) {
            for(int i = 0; i<numberToConvert; i++) {
                count = 10 * count + 1;
            }
            System.out.println(count);
        } else {
            check = parseInt(number, sourceRadix);
            System.out.println(Integer.toString(check, targetRadix));
        }
    }
}
