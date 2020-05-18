package DVD_Library;

public class Member {

    public String fn;
    public String ln;
    public String mobile;
    public String un;
    public int pw = -1;
    public MovieCollection mc = new MovieCollection();

    public Member(String fn, String ln, String mobile){
        this.fn = fn;
        this.ln = ln;
        this.un = fn + ln;
        this.mobile = mobile;
    }

    public void setPassword(int newPw){
        this.pw = newPw;
    }

}
