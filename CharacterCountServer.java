import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CharacterCountServer {
    public static void main(String[] args) {
        int port = 12345; // Specify the port you want to use

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                    String clientMessage = (String) inputStream.readObject();

                    String response = countMaxCharacter(clientMessage);

                    ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    outputStream.writeObject(response);
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String countMaxCharacter(String input) {
        if (input == null || input.isEmpty()) {
            return "No input provided.";
        }

        int[] characterCount = new int[256]; // Assuming ASCII characters
        char[] inputChars = input.toCharArray();

        for (char c : inputChars) {
            characterCount[c]++;
        }

        int maxCount = 0;
        char maxChar = '\0';

        for (int i = 0; i < characterCount.length; i++) {
            if (characterCount[i] > maxCount) {
                maxCount = characterCount[i];
                maxChar = (char) i;
            }
        }

        if (maxChar != '\0') {
            return maxChar + ":" + maxCount;
        } else {
            return "No character found in the input.";
        }
    }
}
