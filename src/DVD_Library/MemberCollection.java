package DVD_Library;

import java.util.ArrayList;
import java.util.List;

public class MemberCollection {

    public Member[] members = new Member[10];

    public void addMember(String fn, String ln, String mobile){
        // iterates through the member array until it finds a null index, then inserts
        int i = 0;
        while(members[i] != null){
            i++;
        }
        members[i] = new Member(fn, ln, mobile);
    }

    public String findMobile(String fn, String ln){
        // iterates through the member array until it finds a member with matching first and last names
        // then returns the associated mobile number
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
        // iterates through the member array until a matching username and password are found
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
        // iterates through the member array until a matching username is found
        // used for checking if a username exists before asking for a password
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
