import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        int serverPort = 9090;
        String address = "localhost";

        try {
            InetAddress ipAddress = InetAddress.getByName(address);
            Socket socket = new Socket(ipAddress, serverPort);

            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String line;
            System.out.println("Все что вы введете будет отправлено на сервер");

            while (true) {
                line = br.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                System.out.println("Отправляем данные на сервер");
                out.writeUTF(line); // отсылаем введенную строку текста серверу.
                out.flush();
                if (line.equalsIgnoreCase("END")) {
                    line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                    System.out.println("Ответ сервера -> " + line);
                    break;
                }
                line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                System.out.println("Пришел ответ от сервера : " + line);
                System.out.println();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}
