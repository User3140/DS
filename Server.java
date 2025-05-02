// Server.java
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of clients: ");
        int numClients = sc.nextInt();

        List<Socket> clientSockets = new ArrayList<>();
        List<Integer> clientTimes = new ArrayList<>();

        System.out.print("Enter server (master) clock time (in seconds): ");
        int masterTime = sc.nextInt();

        System.out.println("Waiting for " + numClients + " clients to connect...");

        // Accept connections
        for (int i = 0; i < numClients; i++) {
            Socket socket = serverSocket.accept();
            clientSockets.add(socket);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            int clientTime = dis.readInt();
            clientTimes.add(clientTime);
            System.out.println("Received time from client " + (i + 1) + ": " + clientTime);
        }

        // Calculate average offset
        int sum = 0;
        for (int time : clientTimes) {
            sum += (time - masterTime);
        }

        int averageOffset = sum / (numClients + 1); // +1 for server
        int newMasterTime = masterTime + averageOffset;

        System.out.println("Average offset: " + averageOffset);
        System.out.println("New master time: " + newMasterTime);

        // Send adjusted time to clients
        for (int i = 0; i < numClients; i++) {
            DataOutputStream dos = new DataOutputStream(clientSockets.get(i).getOutputStream());
            dos.writeInt(newMasterTime);
            clientSockets.get(i).close();
        }

        serverSocket.close();
    }
}
