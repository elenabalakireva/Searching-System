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
        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        ) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(in.readLine());
            out.println(scanner.nextLine());
            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
            }

        } catch (IOException ex) {
            System.out.println("Не могу стартовать сервер");
            ex.printStackTrace();
        }

    }
}
