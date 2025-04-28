package MultiThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public Runnable getRunnable(){
        return ()->{

            int port= 8010;
            try {
                InetAddress address = InetAddress.getByName("localhost");
                Socket socket = new Socket(address, port);
                PrintWriter toserver = new PrintWriter(socket.getOutputStream(), true);
                toserver.println("Hello from client" + socket.getLocalSocketAddress() );
                BufferedReader fromserver = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Message from server:" + fromserver.readLine());
                toserver.close();
                fromserver.close();
                socket.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        };
    }
    public static void main(String[] args){
        Client client = new Client();
        for(int i=0;i<100;i++){
            Thread thread= new Thread(client.getRunnable());
            thread.start();
        }
    }
}
