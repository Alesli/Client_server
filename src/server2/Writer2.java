package server2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Writer2 implements Runnable {

    private Socket socket;

    public Writer2(Socket socket) {
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

            String text;
            while (true) {
                text = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("Пришло на server2 : " + text);
                if (text.equalsIgnoreCase("END")){
                    System.out.println("Клиент ушел");
                    out.writeUTF(text);
                    break;
                }
                out.writeUTF(text); // отсылаем клиенту обратно ту самую строку текста.
                out.flush();
                System.out.println();
            }
        } catch (Exception r) {
            r.printStackTrace();
        }
    }


}
