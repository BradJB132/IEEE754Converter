import java.lang.StringBuilder;
public class Baudin_Convertor {

    private static String convToBinary(int number){
        StringBuilder numStr = new StringBuilder();
        int test = number;
        while(test != 1){
            numStr.insert(0,test % 2);
            test /= 2;
        }
        numStr.insert(0,1);
        return numStr.toString();
    }
    private static String bitRepresentation(double input, int exponent_size, int mantissa_size) {
        StringBuilder mantissa = new StringBuilder();
        StringBuilder exponent = new StringBuilder();
        if(input == 0) {
            for(int i = 0; i < exponent_size; i++)
                exponent.append(0);
            for(int i = 0; i < mantissa_size; i++)
                mantissa.append(0);
            return (Math.signum(input) == Math.signum(-0.0) ? "1 " : "0 ") + exponent.toString() + " " + mantissa.toString();
        }
        int front = (int)Math.floor(Math.abs(input));
        double back = Math.abs(input) % 1;
        int exp = 0;

        if(front != 0){
            mantissa.append(convToBinary(front));
            mantissa.deleteCharAt(0);
            exp = mantissa.length();
        }
        else{
            while(1 > back){
                back *= 2;
                exp -= 1;
            }
            back = back % 1;
        }

        for(int i = mantissa.length(); i < mantissa_size; i++){
            back *= 2;
            if(back >= 1){
                mantissa.append(1);
                back = back % 1;
            }
            else
                mantissa.append(0);
        }
        exp += Math.pow(2, (exponent_size-1))-1;
        exponent.append(convToBinary(exp));
        while(exponent.length() < exponent_size)
            exponent.insert(0, 0);


        return (input >= 0 ? "0" : "1") + " " + exponent.toString() + " " + mantissa.toString();
    }
    public static void IEEE754(double input){
        System.out.println("Input: " + input);
        String bit32 = bitRepresentation(input, 8, 23);
        String bit64 = bitRepresentation(input, 11, 52);
        System.out.println(bit32);
        System.out.println(bit64);
    }

}
