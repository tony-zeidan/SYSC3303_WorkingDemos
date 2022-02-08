package udpmodel;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides an implementation of the Client in the UDP model.
 * The Client continuously passes packets to the server and awaits a response.
 *
 * @author Tony Abou Zeidan
 * @version 2/8/2022
 */
public class Client {

    /**
     * Send and receive packets
     */
    DatagramPacket sendPacket, receivePacket;

    /**
     * A socket used for both sending and retrieving of Server-bound packets
     */
    DatagramSocket sendReceiveSocket;

    /**
     * The ports for the Client and Server
     */
    int port, server_port;

    /**
     * Default constructor for instances of Client.
     * Initializes a new Client ready to communicate with the Server-port.
     *
     * @param port The port of the Client
     * @param server_port The port of the Server
     */
    public Client(int port, int server_port) {
        try {
            this.port = port;
            this.server_port = server_port;
            sendReceiveSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * This method sends a packet to the Server and awaits a reply.
     */
    public void sendReceive() {

        // Initialize the message to send as bytes
        List<Byte> message = new ArrayList<>();
        int[] student_number = new int[] {1, 0, 1, 1, 0, 2, 0, 2, 4};
        for (int i: student_number)
            message.add((byte) i);

        byte[] first_name = "Rowan".getBytes(StandardCharsets.UTF_8);
        byte[] last_name = "James".getBytes(StandardCharsets.UTF_8);
        for (byte b: first_name)
            message.add(b);
        for (byte b: last_name)
            message.add(b);

        byte[] messageArr = new byte[message.size()];
        for (int i = 0; i < message.size(); i ++)
            messageArr[i] = message.get(i);

        // Attempt to send a packet to the server
        try {
            sendPacket = new DatagramPacket(messageArr, messageArr.length, InetAddress.getLocalHost(), this.server_port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Client - packet created.");

        System.out.println(String.format(
                "Client - sending packet to host %s at port %s.\n%s\n%s\n",
                sendPacket.getAddress(),
                sendPacket.getPort(),
                new String(messageArr, 0, messageArr.length),
                Arrays.toString(messageArr)
        ));

        try {
            sendReceiveSocket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Packet sent.");

        // Attempt to receive the packet from the server
        byte[] receiveData = new byte[100];
        receivePacket = new DatagramPacket(receiveData, receiveData.length);

        try {
            sendReceiveSocket.receive(receivePacket);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Print the received packet
        System.out.println(String.format(
                "Client - received packet from host %s at port %s.\n%s\n%s\n",
                receivePacket.getAddress(),
                receivePacket.getPort(),
                new String(receiveData, 0, receivePacket.getLength()),
                Arrays.toString(Arrays.copyOf(receiveData, receivePacket.getLength()))
        ));
    }

    /**
     * Executable, should be run after Server is running.
     *
     * @param args Unused
     */
    public static void main(String[] args) {
        Client client = new Client(23, 62);
        while (true)
            client.sendReceive();
    }
}
