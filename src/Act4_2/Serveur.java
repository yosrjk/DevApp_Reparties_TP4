package Act4_2;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Serveur {
    // Create a byte array buffer for incoming data
    static byte buffer[] = new byte[1024];
    public static void main(String argv[]) throws IOException {
        // Create a DatagramSocket and bind it to port 1234
        try (DatagramSocket socket = new DatagramSocket(1234)) {
            String donnees = "";
            String message = "";
            int taille = 0;
            System.out.println("lancement du serveur"); // Print a message indicating the server has started
            while (true) {
                // Create a DatagramPacket for receiving data
                DatagramPacket paquet = new DatagramPacket(buffer, buffer.length);
                DatagramPacket envoi = null;
                // Receive the packet
                socket.receive(paquet);
                System.out.println("\n" + paquet.getAddress()); // Print the address of the sender
                taille = paquet.getLength();
                donnees = new String(paquet.getData(), 0, taille);
                System.out.println("Donnees reçues = " + donnees); // Print the received data
                // Get the current date and time in the specified format
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                // Create the response message with the current date and time
                message = timeStamp;
                System.out.println("Donnees envoyees = " + message); // Print the message to be sent
                // Create a DatagramPacket for sending data back to the client
                envoi = new DatagramPacket(message.getBytes(), message.length(), paquet.getAddress(), paquet.getPort());
                socket.send(envoi); // Send the response to the client
            }
        }
    }
}
