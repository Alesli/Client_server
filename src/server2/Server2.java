package server2;

import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {

    public static void main(String[] args) {
        int port = 9091;
        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Ждем клиента...");
            while (true) {
                Socket socket = ss.accept();
                System.out.println(socket.getInetAddress().toString());
                new Writer2(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
