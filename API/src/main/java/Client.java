
import java.net.*;
import java.io.*;



public class Client {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        try {
                clientSocket = new Socket(ip, port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            }catch(java.net.ConnectException e){
                System.out.println(API.code.get(101) + ": " + e.getLocalizedMessage());
            }
    }

    public String sendMessage(String msg) throws IOException {
        try {
            out.println(msg);
            String resp = in.readLine();
            return resp;
        }catch (java.lang.NullPointerException e){
                return "";
        }

    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
}
