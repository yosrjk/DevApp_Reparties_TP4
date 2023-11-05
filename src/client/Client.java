package client;
import java.io.*;
import java.net.*;
public class Client {
    public static void main(String[] args) {
        try {
            // Define the message to be sent
            String ch = "Yosr Joulek ";
            byte buffer[] = ch.getBytes();

            // Create a DatagramSocket for sending and receiving datagrams
            DatagramSocket socket = new DatagramSocket();

            // Create a DatagramPacket for sending data to the server
            DatagramPacket donneesEmises = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 1234);
            socket.send(donneesEmises); // Send the packet

            // Create a buffer for receiving data
            byte[] bufferRecu = new byte[1024];
            // Create a DatagramPacket for receiving data from the server
            DatagramPacket donneesRecues = new DatagramPacket(bufferRecu, bufferRecu.length);
            socket.receive(donneesRecues); // Receive the packet

            // Convert the received data to a string
            String message = new String(donneesRecues.getData(), 0, donneesRecues.getLength());
            System.out.println("Message : " + message); // Print the received message
            System.out.println("de : " + donneesRecues.getAddress() + ":" + donneesRecues.getPort()); // Print the server's address and port

            socket.close(); // Close the socket
        } catch (IOException e) {
            e.printStackTrace(); // Print any exceptions that occur
        }
    }
}
