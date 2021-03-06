import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class MainClass {

    public static void main ( String args[]) throws NoSuchAlgorithmException{

        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(1024 / 2, 100, r);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        byte[] result =  sha.digest(p.toByteArray());
        //now I have a random byte array :)


        System.out.println(result);
        System.out.println(hexEncode(result));
        String temp1 = new String(result);
        System.out.println(temp1);

        byte[] after = hexStringToByteArray(hexEncode(result));
        System.out.println(after );
        System.out.println(hexEncode(after));
        String temp2 = new String(after);
        System.out.println(temp2);

        if ( Arrays.equals(result, after) ){
            System.out.println("OK");           
        }
        else{
            System.out.println("Problem");
        }



    }
    private static  String hexEncode(byte[] aInput){
        StringBuilder result = new StringBuilder();
        char[] digits = {'0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e','f'};
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[ (b&0xf0) >> 4 ]);
            result.append(digits[ b&0x0f]);
        }
        return result.toString();
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
