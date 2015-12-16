public class encrypt {
  public static void main(String[] args) throws Exception {
  // File to be encrypted
  FileInputStream inFile = new FileInputStream("/u/users/kumaran/secret.txt");
  
  // Output File that will be in encrypted format
  FileOutputStream outFile = new FileOutputStream("/u/users/kumaran/encrypted.des");
  
  //key to encrypt the file
  String password = "ChicagoBears";
  
  byte[] salt = new byte[8];
  
  SecureRandom secureRandom = new SecureRandom();
  secureRandom.nextBytes(salt);
  
  FileOutputStream saltOutFile = new FileOutputStream("/u/users/kumaran/salt.enc");
  saltOutFile.write(salt);
  saltOutFile.close();
  
  SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
  
  KeySpec keySpec = new PBEKeySpec(password.toCharArray(),salt, 65536, 128);
  
  Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
  cipher.init(Cipher.ENCRYPT_MODE,secret);
  AlgorithmParameters params = cipher.getParameters();
  
  FileOutputStream ivOutFile = new FileOutputStream("/u/users/kumaran/iv.enc");
  byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
  ivOutFile.write(iv);
  ivOutFile.close();
  
  byte[] input = new byte[64];
  int bytesRead;
  
  while((bytesRead = inFile.read(input)) != -1) {
   byte[] output = cipher.update(input, 0 , bytesRead);
   if (output !- null)
           outFile.write(output);
  }
  
  byte[] output = cipher.doFinal();
   if (output !- null)
           outFile.write(output);
    inFile.close();
    outputFile.flush();
    outputFile.close();

  }
}