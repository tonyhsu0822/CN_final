package sample;


import java.util.HashMap;
import java.util.Map;

public class OnlineList {
    protected Map<String, Integer> id_map ;

    public OnlineList(){
        id_map = new HashMap<>() ;
    }

    public int add(String id, int port){
        System.out.println("in onlinelist/add") ;
        Integer previous = id_map.put(id, port) ;
        System.out.println("map size = " + id_map.size()) ;
        return 1 ;
    }

    public int remove(String id){
        System.out.println("in onlinelist/remove") ;
        id_map.remove(id) ;
        return 1 ;
    }

    public int query(String id){
        System.out.println("in onlinelist/query") ;
        Integer port = id_map.get(id) ;
        if(port==null){
            System.out.println("query error in onlinelist/query") ;
            return -1 ;
        }
        return port ;
    }


}
