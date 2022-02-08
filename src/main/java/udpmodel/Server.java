package udpmodel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * This class provides an implementation of the Server in the UDP model.
 * The Server continuously receives packets from the Client and responds.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class Server {

    /**
     * Send and receive packets
     */
    DatagramPacket receivePacket, sendPacket;

    /**
     * Sockets for sending and retrieving
     */
    DatagramSocket receiveSocket, sendSocket;

    /**
     * Default constructor for instances of Client.
     * Initializes a new Server ready to await packets from the Client.
     *
     * @param receive_port The port that the Server will receive packets from
     * @param send_port The port that the Server will send packets from
     */
    public Server(int receive_port, int send_port) {

        try {
            sendSocket = new DatagramSocket(send_port);
            receiveSocket = new DatagramSocket(receive_port);
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * This function awaits a packet from the Client and attempts to reply
     * to the Client.
     */
    public void sendReceive() {

        byte[] receiveData = new byte[100];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);

        try {
            receiveSocket.receive(receivePacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println(String.format(
                "Server - received packet from host %s at port %s.\n%s\n%s\n",
                receivePacket.getAddress(),
                receivePacket.getPort(),
                new String(receiveData, 0, receivePacket.getLength()),
                Arrays.toString(Arrays.copyOf(receiveData, receivePacket.getLength()))
        ));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }

        sendPacket = new DatagramPacket(new byte[] {1, 1, 1, 1}, 4, receivePacket.getAddress(), receivePacket.getPort());

        System.out.println(String.format(
                "Server - sending packet to host %s at port %s.\n%s\n%s\n",
                receivePacket.getAddress(),
                receivePacket.getPort(),
                new String(receiveData, 0, receivePacket.getLength()),
                Arrays.toString(Arrays.copyOf(receiveData, receivePacket.getLength()))
        ));

        try {
            sendSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Packet sent.");
    }

    /**
     * Executable, should be running before Client is run.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        Server server = new Server(62, 69);

        while (true)
            server.sendReceive();
    }

}
