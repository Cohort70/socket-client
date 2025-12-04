package ait.socket.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientSocketAppl {
    public static void main(String[] args) {
        String serverIp = "127.0.0.1"; // localhost
        int serverPort = 9000;
        try (Socket socket = new Socket(serverIp, serverPort);) {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(outputStream);
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(inputStream));
            Scanner consoleScanner = new Scanner(System.in);
            System.out.println("Enter your message, or 'exit' to quit: ");
            String message = consoleScanner.nextLine();
            while (!"exit".equalsIgnoreCase(message)) {
                socketWriter.println(message);
                socketWriter.flush();
                String response = socketReader.readLine();
                System.out.println(response);
                System.out.println("Enter your message, or 'exit' to quit: ");
                message = consoleScanner.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
