import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 * @author Delphine Scherler & Alois Christen
 */
public class Client {

    static final Logger LOG = Logger.getLogger(Client.class.getName());

    final static int BUFFER_SIZE = 1024;

    /**
     * This method does the whole processing
     */
    public void sendOperation() {
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            clientSocket = new Socket("localhost", 2021);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());

            while (true){
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }

                Scanner scan = new Scanner(System.in);
                String operation = scan.nextLine();
                out.println(operation);
            }

        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (out != null) {
                out.close();
            }
            try {
                clientSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        Client client = new Client();
        client.sendOperation();

    }

}
