package Act4_3;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serveur {
    static byte buffer[] = new byte[1024]; // Crée un tableau de bytes pour les données entrantes

    public static void main(String argv[]) throws IOException {
        try (DatagramSocket socket = new DatagramSocket(1234)) { // Crée un socket datagramme lié au port 1234
            String donnees = "";
            String message = "";
            int taille = 0;
            System.out.println("lancement du serveur"); // Affiche un message indiquant le démarrage du serveur

            while (true) { // Boucle infinie pour permettre au serveur de recevoir des messages en continu
                DatagramPacket paquet = new DatagramPacket(buffer, buffer.length); // Crée un paquet de datagramme pour recevoir des données
                DatagramPacket envoi = null;
                socket.receive(paquet); // Reçoit le paquet entrant
                System.out.println("\n" + paquet.getAddress()); // Affiche l'adresse de l'expéditeur du paquet
                taille = paquet.getLength(); // Récupère la taille des données reçues
                donnees = new String(paquet.getData(), 0, taille); // Convertit les données reçues en une chaîne
                System.out.println("Donnees reçues = " + donnees); // Affiche les données reçues

                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); // Récupère la date et l'heure actuelles
                message = timeStamp; // Met à jour le message avec la date et l'heure actuelles

                System.out.println("Donnees envoyees = " + message); // Affiche les données à envoyer

                // Crée un paquet de datagramme pour envoyer des données de date et heure au client
                envoi = new DatagramPacket(message.getBytes(), message.length(), paquet.getAddress(), paquet.getPort());
                socket.send(envoi); // Envoie le paquet de date et heure au client
            }
        }
    }
}
