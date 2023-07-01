
////// CLASS : IDandPasswords 
import java.util.*;
public class IDandPasswords {
    HashMap<String,String> logininfo = new HashMap<String,String>();
    IDandPasswords(){
       logininfo.put("","");
        logininfo.put("user1","100");
        logininfo.put("user2","200");
        logininfo.put("user3","300");
    }
    public HashMap getLoginInfo(){
        return logininfo;
    }
}
///////////////////////////////////////////////
