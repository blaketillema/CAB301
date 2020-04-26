import java.util.ArrayList;
import java.util.List;

public class MemberCollection {

    public Member[] members;
    public List<Member> temp = new ArrayList<Member>();

    public void addMember(String name){
        temp.add(new Member(name));
        members = temp.toArray(new Member[temp.size()]);
    }

    public void getMembers(){
        for(int i = 0; i < members.length; i++){
            System.out.println(members[i].name);
        }
    }

}
