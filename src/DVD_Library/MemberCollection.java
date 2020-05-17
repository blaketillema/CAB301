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
        }
        return mobile;
    }

}
