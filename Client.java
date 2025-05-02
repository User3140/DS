// Client.java
import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 5000);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        // Simulate client time with random offset
        Random rand = new Random();
        int localTime = 100 + rand.nextInt(50); // Example: 100-149 seconds
        System.out.println("Client time: " + localTime + " seconds");

        // Send local time to server
        dos.writeInt(localTime);

        // Receive time adjustment from server
        int adjustedTime = dis.readInt();
        System.out.println("Adjusted time from server: " + adjustedTime + " seconds");

        socket.close();
    }
}
