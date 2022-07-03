import java.util.*;
public class numconverter {
    public static void main(String[] args) {
        while (true){
            //The Scanner syntax to take input
            Scanner sc = new Scanner(System.in);
            // Declaring all the variables
            String num, hexalpha = "abcdef0123456789";
            long base, lenofnum, sum_of_digits = 0;
            boolean isint = true, isHex = false;
            /*Interface to accept the number and its base 
            and store it's value*/
            System.out.print("Enter a number: ");
            num= sc.next();
            System.out.print("Enter base of number(2,8,10,16): ");
            base=sc.nextInt();
            System.out.println("Processing ...\n");
            //Finding the number of digits
            lenofnum = num.length();
            //Checking if base is valid
            if (base == 2 ||base == 8 ||base == 10 ||base == 16) {
                //Checking if the number is an Long
                for (int i = 0; i < num.length(); i++) {
                    try {
                        Long.parseLong(num.charAt(i)+"");
                    } catch (Exception NumberFormatException) {
                        isint = false;
                    }
                    //Finding the sum of the digits of the number
                    if (isint==true) {
                        sum_of_digits+=Long.parseLong(num.charAt(i)+"");
                    }
                    // Checking if number is a valid Hexadecimal number
                    if (hexalpha.indexOf((num.charAt(i)+"").toLowerCase())!=-1) {
                        isHex = true;
                    }else {
                        isHex = false;
                        break;
                    }
                }
            }
            //Checking if the number if a Binary number and converting it to
            if (base==2 && (sum_of_digits/lenofnum)<=1 && isint==true) {
                // An Octal Number
                System.out.println("Octal Version of Number is: ("+decimal_to_other(other_to_decimal(num,2),8)+") [base: 8]");
                // A Decimal number
                System.out.println("Decimal Version of Number is: ("+other_to_decimal(num,2)+") [base: 10]");
                // A Hexadecimal Number
                System.out.println("Hexadecimal Version of Number is: ("+decimal_to_other(other_to_decimal(num,2),16)+") [base: 16]");
            //Checking if the number if an Octal number and converting it to
            }else if (base==8 && (sum_of_digits/lenofnum)<=7 && isint==true) {
                // A Binary Number
                System.out.println("Binary Version of Number is: ("+decimal_to_other(other_to_decimal(num,8),2)+") [base: 2]");
                // A Decimal Number
                System.out.println("Decimal Version of Number is: ("+other_to_decimal(num,8)+") [base: 10]");
                // A Hexadecimal Number
                System.out.println("Hexadecimal Version of Number is: ("+decimal_to_other(other_to_decimal(num,8),16)+") [base: 16]");
            //Checking if the number if a Decimal number and converting it to
            }else if (base==10 && (sum_of_digits/lenofnum)<=9 && isint==true) {
                // A Binary Number
                System.out.println("Binary Version of Number is: ("+decimal_to_other(num,2)+") [base: 2]");
                // An Octal Number
                System.out.println("Octal Version of Number is: ("+decimal_to_other(num,8)+") [base: 8]");
                // A Hexadecimal Number
                System.out.println("Hexadecimal Version of Number is: ("+decimal_to_other(num,16)+") [base: 16]");
            //Checking if the number if a Hexadecimal number and converting it to
            }else if (base==16 && isHex==true) {
                // A Binary Number
                System.out.println("Binary Version of Number is: ("+decimal_to_other(other_to_decimal(num,16),2)+") [base: 2]");
                // An Octal Number
                System.out.println("Octal Version of Number is: ("+decimal_to_other(other_to_decimal(num,16),8)+") [base: 8]");
                // A Decimal Number
                System.out.println("Decimal Version of Number is: ("+other_to_decimal(num,16)+") [base: 10]");
            }else{
                System.out.println("Not a valid base or number or both.");
            }
            System.out.println();
        }
    }
    /* Function to convert a decimal number and convert it to any base like 
    Binary, Octal, Decimal and Hexadecimal
    */ 
    public static String decimal_to_other(String number, long base_to_convert) {
        //Declaring all the important variables
        String answer = "",hexalpha, actual_answer="";
        hexalpha = "ABCDEF";
        char ch;
        //The main loop that will do two things
        while (Long.parseLong(number)!=0) {
            //1. Find the modulus of number
            String moduluser="";
            moduluser = Long.toString(Long.parseLong(number)%base_to_convert);
            /* For Hexadecimal number:
             * Replaces the modulus with alphabets accordingly
             */
            if (base_to_convert==16) {
                for (int i=0;i<hexalpha.length();i++) {
                    if ((i+10)==Long.parseLong(moduluser)) {
                        moduluser = hexalpha.charAt(i)+"";
                        break;
                    }
                }
            }
            //2. Add the modulus to the answer that is in reverse
            answer+=moduluser;
            number = Long.toString(Long.parseLong(number)/base_to_convert);
        }
        // Re-reversing the number to give the result
        for (int i=0; i<answer.length(); i++)
        {
            ch= answer.charAt(i);
            actual_answer= ch+actual_answer;
        }
        // Returning the answer
        return actual_answer;
    }
    /* Function to convert a number from any base like
    Binary, Decimal, Octal and Hexadecimal
    and convert it to a decimal number
    */ 
    public static String other_to_decimal(String num, long converter) {
        //Declaring all the important variables
        long lenofnum,index_of_digit,answer;
        boolean isint=true;
        String hexalpha;
        hexalpha = "ABCDEF";
        index_of_digit=0;
        answer=0;
        //Finds the number of digits of the number
        lenofnum = num.length()-1;
        /* The main loop for this function that does
        all the conversions
         */
        while (lenofnum!=-1) {
            // Checking if number is a Hexadecimal number
            if (converter==16) {
                // Dividing the number into numbers and alphabets
                try {
                    Long.parseLong(num.charAt((int)index_of_digit)+"");
                } catch (Exception NumberFormatException) {
                    isint=false;
                }
                //Processing the alphabet part
                if (isint==false) {
                    if (hexalpha.indexOf((num.charAt((int)index_of_digit)+"").toUpperCase())!=-1) {
                        answer+=((Long.parseLong(hexalpha.indexOf((num.charAt((int)index_of_digit)+"").toUpperCase())+"")+10)*((long)(Math.pow(converter, lenofnum))));
                    } else {//Or displaying that the Hexadecimal number is invalid
                        System.out.println("Not a valid Hexadecimal number. ");
                        break;
                    }
                // Processing the Long part
                } else {
                    answer+=(Long.parseLong(num.charAt((int)index_of_digit)+"")*(long)(Math.pow(converter,lenofnum)));
                }
            // Processing the numbers of other bases
            }else {
                answer+=(Long.parseLong(num.charAt((int)index_of_digit)+"")*(long)(Math.pow(converter,lenofnum)));
            }
            // Incrementing and decrementing numbers
            index_of_digit++;
            lenofnum--;
        }
        // Returning the final answer
        return Long.toString(answer);
    }
}
