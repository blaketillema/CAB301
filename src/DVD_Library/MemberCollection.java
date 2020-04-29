package DVD_Library;

import java.util.ArrayList;
import java.util.List;

public class MemberCollection {

    public Member[] members = new Member[10];

    public void addMember(String name){
        int i = 0;
        while(members[i] != null){
            i++;
        }
        members[i] = new Member(name);
    }

    public void getMembers(){
        int i = 0;
        while(members[i] != null){
            System.out.println(members[i].name);
            i++;
        }
    }

}
