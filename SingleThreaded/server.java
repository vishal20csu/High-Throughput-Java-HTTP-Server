package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public void run() throws IOException {
        int port= 8010;
        ServerSocket server = new ServerSocket(port);
        while (true) {
            try {

                Socket connection = server.accept();
                server.setSoTimeout(10000);
                System.out.println("SingleThreaded.client connected from " + connection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(connection.getOutputStream()); // convert texts to bytes from SingleThreaded.server to SingleThreaded.client
                BufferedReader fromclient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                toClient.println("Hello from the SingleThreaded.server");
                toClient.close();
                fromclient.close();
                connection.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {

        server server= new server();
        try{
            server.run();
        }catch(Exception e){
            e.printStackTrace();
        }

        }
    }

