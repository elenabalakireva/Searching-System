import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

public class Main {
    static final String PATH = "pdfs";
    static final int port = 8989;

    public static void main(String[] args) throws Exception {

        BooleanSearchEngine booleanSearchEngine = new BooleanSearchEngine(new HashMap<>());
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            out.println("Write a word");
            final String word = in.readLine();

            List<PageEntry> sortedList = booleanSearchEngine.search(word);
            out.println(stringToJson(sortedList));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String stringToJson(List<PageEntry> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);
        return json;

    }
}
