private static byte[] Key = new byte[] {
        0x42, 0x45, 0x49, 0x30, 0x12, 0x22, 0x35, 0x48, 0x33, 0x24, 0x28, 0x51,
        0x48, 0x24, 0x30, 0x21, 0x44, 0x31, 0x14, 0x19, 0x45, 0x34, 0x47, 0x25 };

Cipher c;

public EncryptionHelper() throws Exception {
    // byte[] key_hash = (Key).toString().getBytes("UTF-8");
    // key_hash = Arrays.copyOf(key_hash, 32);
    SecretKey key = new SecretKeySpec(Key, 0, Key.length, "DESede");
    c = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    c.init(Cipher.ENCRYPT_MODE, key);
}

public String Encrypt(String S) throws Exception {
    byte[] base64EncryptedText = S.getBytes("UTF-8");
    byte EncryptedText[] = c.doFinal(base64EncryptedText, 0, base64EncryptedText.length);
    return new String(EncryptedText);
}

public String Decrypt(String S) throws Exception {
    Cipher c2 = null;
    // byte[] key_hash = (Key).toString().getBytes("UTF-8");
    // key_hash = Arrays.copyOf(key_hash, 24);
    SecretKey key = new SecretKeySpec(Key,0, Key.length, "DESede");
    c2 = Cipher.getInstance("DESede/ECB/PKCS5Padding");
    c2.init(Cipher.DECRYPT_MODE, key);
    byte[] base64EncryptedText = Base64.getEncoder().encode(S.getBytes());
    byte[] textDecrypted = c2.doFinal(base64EncryptedText, 0, base64EncryptedText.length);
    return new String(textDecrypted, "UTF-8");
}
