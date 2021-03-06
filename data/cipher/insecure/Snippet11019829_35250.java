package encryptionpackage;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher; 
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class encryption 
{
    // Note: The full CF default is "AES/ECB/PKCS5Padding"
    private static final String ALGORITHM = "AES";
    // The 24 character key from my CF app (base64 encoded)
    // typically generated with:  generateSecretKey("AES") 
    private static final String passKey = "***********************"; 

     public static String encrypt(String valueToEnc) throws Exception 
     {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = bytesToHex(encValue);
        return encryptedValue;
    }

    public static String decrypt(String encryptedValue) throws Exception 
    {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = hexStringToByteArray(encryptedValue);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() throws Exception 
    {
          byte[] keyValue;
          keyValue = new BASE64Decoder().decodeBuffer(passKey);
        Key key = new SecretKeySpec(keyValue, ALGORITHM);

        return key;
    }

    public static String bytesToHex(byte[] bytes) 
    { 
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'}; 
        char[] hexChars = new char[bytes.length * 2]; 
        int v; 
        for ( int j = 0; j < bytes.length; j++ ) 
        { 
            v = bytes[j] & 0xFF; 
            hexChars[j * 2] = hexArray[v >>> 4]; 
            hexChars[j * 2 + 1] = hexArray[v & 0x0F]; 
        } 
        return new String(hexChars); 
    } 

    public static byte[] hexStringToByteArray(String s) 
    { 
        int len = s.length(); 
        byte[] data = new byte[len / 2]; 
        for (int i = 0; i < len; i += 2) 
        { 
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) 
                                 + Character.digit(s.charAt(i+1), 16)); 
        } 
        return data; 
    } 

}
