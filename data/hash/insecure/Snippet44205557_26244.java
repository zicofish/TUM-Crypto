byte[] key = (SALT2 + username + my_custom_secret_key).getBytes("UTF-8");
MessageDigest sha = MessageDigest.getInstance("SHA-1");
key = sha.digest(key);
key = Arrays.copyOf(key, 16); // use only first 128 bit

SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
