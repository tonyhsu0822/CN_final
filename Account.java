package sample;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by co on 2016/1/18.
 * Account = 0
 * Password = 1
 * in a String array
 */
public class Account {
    protected Map<String, String> account_map ;

    public Account(){
       account_map = new HashMap<>();
    }

    public int add(String id, String password){
        System.out.print("New add, id =" + id + " password = " + password+ " in account\n") ;
        String previous = account_map.put(id, password);
        return 1;
    }

    public int remove(String id){
        System.out.print("Remove:" + id + "in account\n") ;
        account_map.remove(id) ;
        return 1 ;
    }

    public String query(String id){
        // input a id and return the password
        System.out.print("Query:" + id + "in account\n") ;
        if(!account_map.containsKey(id)){
            System.out.print("No such id in map in account\n") ;
            return null ;
        }
        String result = account_map.get(id) ;
        System.out.println("Get:" + result+ "in account") ;
        return result ;

    }


}