import java.io.IOException;

public class Main {
    public static void main(String[] args) {


        MyServer myServer = new MyServer();
        try {
            myServer.start(8181);
        } catch (IOException e) {

            System.out.println(e.getMessage());
        } finally {
            try {
                myServer.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }


    }
}