package sample ;

import java.net.ServerSocket;
import java.net.Socket;

/*
        A Server with no thread now, only handle one connection, then try to read in on
    System.out.print.

        Reference:
        http://blog.yslifes.com/archives/652

        Data: 1/18 15:24

 */


public class Server{

    public ServerSocket listen_socket ;
    public static int listen_port = 12345 ;
    public static Account account = new Account() ;
    public static OnlineList onlinelist = new OnlineList() ;



    public int init() throws  Exception{
        //System.out.print("Server init\n");
        listen_socket = new ServerSocket(listen_port) ;
        //System.out.print("Server socket buffer = ") ;
        //System.out.print(listen_socket.getReceiveBufferSize() + "\n") ;
        //System.out.print("Server socket address = " + listen_socket.getInetAddress() + "\n");

        return 0 ;
    }

    public int run() throws  Exception{
        Socket socket ;
        java.io.BufferedInputStream input_stream ;
        //System.out.print("Server run\n") ;

        while(true){
            //System.out.print("In while loop\n");

            try{
                //System.out.print("In try\n") ;
                while(true){
                    synchronized (listen_socket) {
                        //System.out.print("In synchronize\n");
                        //synchronize, a kind of lock in thread of java
                        socket = listen_socket.accept();
                        System.out.print("Server connected, InetAddress =  " + socket.getInetAddress() + "\n") ;
                        ClientHandler clienthandler = new ClientHandler(socket) ;
                        clienthandler.start();
                    }
                }

               /*
                socket.setSoTimeout(100) ;     //set socket timeout time
                //read the input
                //read in byte format, cast to string, which just imitate from Internet
                //b for those byte, data for the string, and length for input length
                input_stream = new java.io.BufferedInputStream(socket.getInputStream()) ;
                byte[] b = new byte[1024] ;
                String data = "" ;
                int length ;

                while( (length = input_stream.read(b)) > 0 ){
                    data = data + new String(b, 0 ,length) ;

                }
                System.out.print("Read: " + data) ;

                input_stream.close() ;
                socket.close() ;
                break ;
                */

            } catch(java.io.IOException e){
                System.out.print("Socket connect failed\n") ;
                System.out.print("IOExecption" +  e.toString()) ;

            }

        }



    }





}
