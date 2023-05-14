import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GroupChat {

    public  static  String name;
    static public volatile boolean finished = false;

    private static final String TERMINATE = "exit";

    public static void main(String []args){

        try {
            InetAddress group = InetAddress.getByName("224.0.0.0");
            int port = 3001;
            Scanner scanner = new Scanner(System.in);
            name = scanner.nextLine();
            MulticastSocket socket = new MulticastSocket(port);
            socket.setTimeToLive(0);
            socket.joinGroup(group);

            ReadThread readThread = new ReadThread(socket, group, port);
            Thread thread = new Thread(readThread);
            thread.start();

            while(true){
                String message = scanner.nextLine();
                if(message.equals(TERMINATE)){
                    finished = true;
                    socket.leaveGroup(group);
                    socket.close();
                    break;
                }
                message = name + " : " + message;
                byte[] buffer = message.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, group, port);
                socket.send(datagramPacket);
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
