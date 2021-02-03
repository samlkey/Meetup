import com.google.gson.Gson;


import java.net.*;
import java.io.*;

public class TCPServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private static Database db = new Database();


    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                String s = inputLine.split("!")[0];


                //if json make user
                if("REGUSER".equals(s)) {
                    Gson g = new Gson();
                    UserColl curr = g.fromJson(inputLine.split("!")[1], UserColl.class);
                    db.RegisterUser(curr);
                    System.out.println("[REGUSER] " + curr.username + " REGISTERED");

                }


                if ("exit".equals(inputLine)) {
                    out.println("Closing connection");
                    break;
                }


                out.println(inputLine);
            }
        }catch(java.net.SocketException e){
            System.out.println("[DB SERVER] CONNECTION HAS BEEN CLOSED");
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {


        int port = 6666;
        TCPServer server = new TCPServer();
        System.out.println("[DB SERVER] LISTENING ON PORT: " + port);
        server.start(port);
        System.out.println("[DB SERVER] CLOSING SERVER");
        server.stop();
    }
}
