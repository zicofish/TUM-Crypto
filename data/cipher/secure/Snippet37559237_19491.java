import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import com.google.common.primitives.Longs;
class Encryptor {
    private String initialVector;
    private static final String TRANFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";
    String encrypt(SecretKeySpec key, long timestamp) throws Exception {
        byte[] encryptedBytes =
            getEncryptingCipher(key).doFinal(Longs.toByteArray(timestamp));
        return Base64.encodeBase64String(encryptedBytes);
    }
    private Cipher getEncryptingCipher(SecretKeySpec key) throws
    NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException,
    InvalidKeyException,
    InvalidAlgorithmParameterException {
        Cipher encryptingCipher = Cipher.getInstance(TRANFORMATION);
        encryptingCipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(initialVector.getBytes()));
        return encryptingCipher;
    }
    private SecretKeySpec getSecretKeySpec(String key) throws DecoderException {
        byte[] keyBytes = Hex.decodeHex(key.toCharArray());
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }
    void setInitialVector(String initialVector) {
        this.initialVector = initialVector;
    }
}
