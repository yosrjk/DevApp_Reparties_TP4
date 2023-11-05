package Act4_3;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) { // Crée un socket datagramme pour le client
            Scanner scanner = new Scanner(System.in); // Crée un scanner pour obtenir l'entrée de l'utilisateur

            while (true) { // Boucle infinie pour permettre au client d'envoyer plusieurs messages
                System.out.print("Enter your message: "); // Invite l'utilisateur à saisir un message
                String ch = scanner.nextLine(); // Lit la prochaine ligne saisie par l'utilisateur
                byte buffer[] = ch.getBytes(); // Convertit la chaîne en tableau de bytes pour l'envoi

                // Crée un paquet de datagramme pour envoyer des données à l'adresse locale sur le port 1234
                DatagramPacket donneesEmises = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), 1234);
                socket.send(donneesEmises); // Envoie le paquet

                byte[] bufferRecu = new byte[1024]; // Crée un tampon pour recevoir les données
                DatagramPacket donneesRecues = new DatagramPacket(bufferRecu, bufferRecu.length); // Crée un paquet de datagramme pour recevoir les données
                socket.receive(donneesRecues); // Reçoit le paquet du serveur

                // Convertit les données reçues en une chaîne et affiche le message et l'adresse du serveur
                String message = new String(donneesRecues.getData(), 0, donneesRecues.getLength());
                System.out.println("Message Received : " + message);
                System.out.println("from : " + donneesRecues.getAddress() + ":" + donneesRecues.getPort());
            }
        } catch (IOException e) { // Gère toute exception d'entrée/sortie qui peut survenir
            e.printStackTrace(); // Affiche la trace de la pile en cas d'erreur
        }
    }
}
