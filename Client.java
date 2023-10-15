import java.io.*;
import java.net.Socket;

public class CharacterCountClient {
    public static void main(String[] args) {
        String serverHost = "localhost"; // Change this to the server's hostname or IP
        int serverPort = 12345; // Change this to the server's port

        try (Socket socket = new Socket(serverHost, serverPort)) {
            String input = "I Love Pakistan"; // Change this to the input string
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(input);

            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            String response = (String) inputStream.readObject();

            System.out.println("Server Response: " + response);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
