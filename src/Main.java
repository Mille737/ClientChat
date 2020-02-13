import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    private static Socket link;
    private static final int port = 1234;
    private static InetAddress host;

    public static void main(String[] args) {

        try {
            host = InetAddress.getLocalHost();
        }
        catch (UnknownHostException uknownHost) {
            System.out.println("Unkown Host");
            System.exit(1);
        }
        acessServer();
    }
    private static void acessServer() {
        Socket link = null;
        try {
            link = new Socket(host, port);

            Scanner input = new Scanner(link.getInputStream());
            PrintWriter outPut = new PrintWriter(link.getOutputStream(), true);

            //set up stream for keyboard entry
            Scanner userEntry = new Scanner(System.in);

            String message, response;
            do {
                System.out.println("Enter Message: ");
                message = userEntry.nextLine();
                outPut.println(message);
                response = input.nextLine();
                System.out.println("\n SERVER> " + response);
            } while (!message.equals("***CLOSE***"));
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        finally {
            try {
                System.out.println("\n Disconnecting");
                link.close();
            }
            catch (IOException ioEx) {
                System.out.println("Couldn't disconnect");
                System.exit(1);
            }
        }
    }
}
