package sample;

import java.io.*;
import java.net.Socket;

/*
* A run class for Server
*
* TODO: disconnection, message, group message, file
*
*
* */



public class ClientHandler extends Thread{

    protected Socket socket ;
    protected DataInputStream input ;
    protected DataOutputStream output ;

    public ClientHandler(Socket in_socket) throws Exception{
        this.socket = in_socket ;  //make the object's socket assigned the socket in argument
        input = new DataInputStream(new BufferedInputStream(socket.getInputStream())) ;
        output = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())) ;

    }
    public void run(){
        try{
            while(true){
                byte[] b = new byte[1024] ;
                String data ;
                int length ;


                while( (length = input.read(b)) > 0 ){
                    data = new String(b, 0 ,length) ;
                    System.out.println("Read: " + data);

                    String[] token = data.split("[<>]+") ;
                    for(int i = 0 ; i< token.length ; i++){
                        System.out.println(i + ":" + token[i]);
                    }

                    switch (token[1]) {
                        case "R" :
                            System.out.println("Register!") ;
                            if(-1==register(token[3],token[5])){
                                System.out.println("register fail!");
                            }else{
                                System.out.println("register success") ;

                            }
                            break ;
                        case "L" :
                            System.out.println("Login!") ;
                            int value1 = login(token[3], token[5]) ;
                            if(-1 == value1){
                                System.out.println("register fail!");
                            }else if(0 == value1){
                                System.out.println("login fail") ;
                            }else{
                                System.out.println("login successfully!!!!!!!!!!!!!") ;
                            }
                            break ;
                        case "K" :
                            System.out.print("Knock!") ;
                            int value2 = knock(token[3]) ;
                            if(value2 == -1){
                                System.out.println("The id not register: " + token[3]) ;
                            }else if(value2 == 0){
                                System.out.println("The id not online: "+ token[3]) ;
                            }else if(value2 == 1){
                                System.out.println("The id is online!!!!!!!!!!!!!!!! "+ token[3]) ;
                            }
                            break ;
                        case "M" :
                            System.out.println("Message!!!!!!!!") ;

                            String source = token[3] ;
                            String dest = token[5] ;
                            String text = token[7] ;



                        default :
                            System.out.println("unknown tag: "+ token[1]) ;
                            break ;
                        }



                }

                System.out.print("Client close\n") ;
                break ;


            }


        }catch(IOException ie){
            ie.printStackTrace();
        }
    }

    public int register(String id, String password){
        System.out.println("In register") ;
        if(1 != Server.account.add(id, password)){
            System.out.println("Add account error") ;
            return -1 ;
        }
        return 1 ;
    }

    public int login(String id, String password){
        System.out.println("In login") ;
        if (!password.equals(Server.account.query(id))) {
            if(null == Server.account.query(id)){
                System.out.println("No this id") ;
            }else System.out.println("Id not match password");
            return 0 ;
        } else {
            Server.onlinelist.add(id, socket.getLocalPort()) ;
            System.out.println("login successfully") ;
            return 1 ;
        }

    }

    public int knock(String id){
        //1.Check if registered 2.Check if online
        System.out.println("In knock") ;
        if(null == Server.account.query(id)){
            System.out.println("No this id in knock") ;
            return -1 ;
        }else if(-1 == Server.onlinelist.query(id)){
            System.out.println("Offline in knock") ;
            return 0 ;
        }else{
            System.out.println("id online with port:" + Server.onlinelist.query(id)) ;
            return 1 ;
        }

    }

}
