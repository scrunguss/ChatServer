import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConnection extends Thread {

    private Socket socket;

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            StringBuilder messageBuilder = new StringBuilder();
            String message;
            while((message = reader.readLine()) != null){
                messageBuilder.append(message);
            }
            input.close();
            System.out.println(messageBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}