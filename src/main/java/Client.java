import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String args[]) {
        String host = "localhost";
        int port = 8989;
        try (Socket clientSocket = new Socket(host, port)) {
            PrintWriter out = new
                    PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new
                    InputStreamReader(clientSocket.getInputStream()));
            Scanner scanner = new Scanner(System.in);
            System.out.println(in.readLine());
            out.println(scanner.nextLine());

            while (true) {
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
