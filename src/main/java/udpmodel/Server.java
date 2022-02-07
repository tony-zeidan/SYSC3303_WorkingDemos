package udpmodel;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class Server {

    DatagramPacket receivePacket, sendPacket;
    DatagramSocket receiveSocket, sendSocket;

    public Server(int receive_port, int send_port) {

        try {
            sendSocket = new DatagramSocket(send_port);
            receiveSocket = new DatagramSocket(receive_port);
        } catch (SocketException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

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

    public static void main(String[] args) {
        Server server = new Server(62, 69);

        while (true)
            server.sendReceive();
    }

}
