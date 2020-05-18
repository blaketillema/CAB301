package DVD_Library;

import java.util.ArrayList;
import java.util.List;

public class MemberCollection {

    public Member[] members = new Member[10];

    public void addMember(String fn, String ln, String mobile){
        int i = 0;
        while(members[i] != null){
            i++;
        }
        members[i] = new Member(fn, ln, mobile);
    }

    public String findMobile(String fn, String ln){
        int i = 0;
        String mobile = "not found";
        while(members[i] != null){
            if(members[i].fn.equals(fn) && members[i].ln.equals(ln)){
                mobile = members[i].mobile;
                return mobile;
            }
            else{
                i++;
            }
        }
        return mobile;
    }

    public boolean login(String un, int pw){
        int i = 0;
        while(members[i] != null){
            if(members[i].un.equals(un) && members[i].pw == pw){
                return true;
            }
            else{
                i++;
            }
        }
        return false;
    }

    public Member searchUn(String un){
        int i = 0;
        while(members[i] != null){
            if(members[i].un.equals(un)){
                return members[i];
            }
            else{
                i++;
            }
        }
        return null;
    }
}
