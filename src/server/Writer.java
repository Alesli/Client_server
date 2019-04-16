package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Writer implements Runnable {
    private String text;
    private int serverPort2 = 9091;
    private String address2 = "localhost";

    private Socket socket;

    public Writer(Socket socket) {
        this.socket = socket;
        new Thread(this, "Демонстрационный socket").start();
    }

    @Override
    public void run() {

        try {
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line;
            while (true) {
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("Клиент прислал : " + line);

                out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                out.flush();
                text = line;
                new DataOutputStream(clientServer(serverPort2, address2, text));
                System.out.println();
                if (line.equalsIgnoreCase("END")) {
                    out.writeUTF(line);
                    break;
                }
            }

        } catch (Exception r) {
            r.printStackTrace();
        }

    }

    public static DataOutputStream clientServer(int serverPort2, String address2, String text) {

        try {
            InetAddress ipAddress = InetAddress.getByName(address2);
            Socket socket = new Socket(ipAddress, serverPort2);

            OutputStream sout = socket.getOutputStream();

            DataOutputStream out = new DataOutputStream(sout);

            while (true) {
                System.out.println("Отправляем данные на сервер_2");
                if (text.equalsIgnoreCase("END")) {
                    System.out.println("Клиент ушел с сервера_1");
                    out.writeUTF(text);
                    break;
                }
                out.writeUTF(text); // отсылаем введенную строку текста серверу.
                out.flush();

                System.out.println();
                return out;
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }
}
