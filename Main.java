package sample;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws Exception
    {
        Scanner scan = new Scanner(System.in);

        System.out.println("請傳入參數server或client");
        String string = scan.next();
        if(string.equals("server")){
           is_server();
        }
        else{
           is_client();

        }

    }

    public static void is_server() throws Exception{
        //System.out.print("In is_server\n") ;
        Server server = new Server() ;
        if(-1 == server.init()){
            System.out.print("Server init error\n") ;
            System.exit(-1);
        }
        if(-1 == server.run()){
            System.out.print("Server run error\n") ;
            System.exit(-1);
        }

        System.exit(0) ;

    }

    public static void is_client() throws Exception{
       // System.out.print("is client\n") ;
        new Client() ;
    }
}
