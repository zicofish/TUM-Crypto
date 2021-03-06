import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;
import java.util.Scanner;


public class AuthenticationSystem {
    public static void main(String args[]) {
        try {
            Scanner scan;
            scan = new Scanner(new File("C:\\Users\\Zane Hewgley\\Desktop\\AuthSys\\.idea\\credentials.txt"));
            String credentials[][] = new String[100][4];
            int count = 0;
            while (scan.hasNextLine()) {
//read file
                credentials[count][0] = scan.next();
                credentials[count][1] = scan.next();
//obtain credentials from file
                String l[] = scan.nextLine().split("\"[ ]+");
                l[0] = l[0].trim();
                l[0] = l[0].replace("\"", "");

                credentials[count][2] = l[0];
                credentials[count][3] = l[0].trim();
                count++;
            }
//request user input
            Scanner input = new Scanner(System.in);
            boolean RUN = true;
            int tries = 0;

            while (RUN) {
                System.out.println("-Welcome-");
                System.out.println("1-Login");
                System.out.println("2-Exit");

                int ch = Integer.parseInt(input.nextLine().trim());

                if (ch == 1) {
//increment number of attempts
                    tries++;
//request username and password
                    System.out.print("Username: ");
                    String username = input.nextLine();
                    System.out.print("Password: ");
                    String password = input.nextLine();
//generate hash
                    MessageDigest md;
                    md = MessageDigest.getInstance("MD5");
                    md.update(password.getBytes());
                    byte[] digest = md.digest();
                    StringBuilder sb = new StringBuilder();
                    for (byte b : digest) {
                        sb.append(String.format("%02x", b & 0xff));
                    }
                    String hPassword = sb.toString();

                    boolean badUser = true;
                    for (int i = 0; i < count; i++) {
                        if (username.contentEquals(credentials[i][0])) {
                            if (hPassword.contentEquals(credentials[i][1])) {
//if verified, logged in
                                List<String> data = null;
//check type of user and print
                                switch (credentials[i][3]) {
                                    case "zookeeper":
                                        data = Files.readAllLines(Paths.get("zookeeper.txt"), Charset.defaultCharset());
                                        break;
                                    case "admin":
                                        data = Files.readAllLines(Paths.get("admin.txt"), Charset.defaultCharset());
                                        break;
                                    case "veterinarian":
                                        data = Files.readAllLines(Paths.get("veterinarian.txt"), Charset.defaultCharset());
                                        break;
                                    default:
                                        break;
                                }
                                if (data != null) {
                                    for (String s : data) {
                                        System.out.println(s);
                                    }
                                }
//reset
                                tries = 0;

                                System.out.println("\n1) Logout.");
                                System.out.println("2) Exit.");

                                ch = Integer.parseInt(input.nextLine().trim());
                                if (ch == 2) {
                                    RUN = false;
                                }
                                badUser = false;
                                break;
                            }
                        }
                    }
                    if (badUser) {
                        System.out.println("Invalid Username or password.");
                    }
                } else {
                    break;
                }
//limit attempts
                if (tries == 3) {
                    RUN = false;
                    System.out.println("Immoderate attempts!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
