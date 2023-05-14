import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        MyClient myClient = new MyClient();

        try {
            myClient.startConnection("192.168.20.46", 5000);
            System.out.println(myClient.sendMessage("Rame sxva"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                myClient.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}