package sample;

import java.io.BufferedOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/*
        A Server with no thread now, only handle one connection, then try to read in on
    System.out.print.

        Reference:
        http://blog.yslifes.com/archives/652

        Data: 1/18 15:24

 */
public class Client {
    private String address = "127.0.0.1";// 連線的ip
    private int port = 12345;// 連線的port

    public Client() {

        System.out.print("In client class\n");

        Socket client = new Socket();
        InetSocketAddress isa = new InetSocketAddress(this.address, this.port);
        try {
            client.connect(isa, 10000);
            BufferedOutputStream out = new BufferedOutputStream(client
                    .getOutputStream());
            // 送出字串
            out.write("<R><ID>Tony<PWD>123123".getBytes());
            out.flush();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            out.write("<L><ID>Tonyq<PWD>123123".getBytes());
            out.flush();

            out.close();
            out = null;
            client.close();
            client = null;

        } catch (java.io.IOException e) {
            System.out.println("Socket連線有問題 !");
            System.out.println("IOException :" + e.toString());
        }
    }

    public static void main(String args[]) {
        System.out.print("In client main\n");
        new Client();
        System.out.print("Finish client main\n");
    }
}
