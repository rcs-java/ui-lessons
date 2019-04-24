import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


// Copy of one of our lessons, but changed main to authenticate
// authenticate now returns the username
public class Authenticator {
    private static String fileName = "userData.txt";

    /**
     * Tries to log in a user.
     * @return The username that was logged in or null.
     */
    public static String login(String username, String password) throws FileNotFoundException {
        File f = new File(fileName);

        if (!f.exists()) {
            return null;
        }

        Scanner fileScan = new Scanner(f);

        while (fileScan.hasNext()) {
            String userPassPair = fileScan.nextLine();
            String[] result = userPassPair.split(",");
            if (username.equals(result[0])
                && password.equals(result[1])) {
                return result[0];
            }
        }

        return null;
    }

    /**
     * Tries to register a new user.
     * @return The username that was registered.
     * @throws IOException on file system errors
     * @throws RuntimeException on user errors (check message for more details)
     */
    public static String registerUser(
        String username,
        String password
    ) throws IOException, RuntimeException {
        if (userExists(username)) {
            throw new RuntimeException("The user exists already!");
        }

        File f = new File(fileName);
        FileWriter fw = new FileWriter(f, true);
        fw.write(username + "," + password + System.lineSeparator());
        fw.flush();

        return username;
    }

    private static boolean userExists(String username) {
        File f = new File(fileName);
        Scanner fileScan;

        try {
            fileScan = new Scanner(f);
        } catch (FileNotFoundException ex) {
            return false;
        }

        while (fileScan.hasNext()) {
            String userPassPair = fileScan.nextLine();
            String[] result = userPassPair.split(",");
            if (username.equals(result[0])) {
                return true;
            }
        }

        return false;
    }
}
