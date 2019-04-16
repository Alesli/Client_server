package server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        int port = 9090;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Ждем клиента...");
            while (true) {
                Socket socket = ss.accept();
                System.out.println(socket.getInetAddress().toString());
                new Writer(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
