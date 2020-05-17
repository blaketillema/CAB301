package DVD_Library;

public class Member {

    public String fn;
    public String ln;
    public String mobile;
    public String un = fn + ln;
    public MovieCollection mc = new MovieCollection();

    public Member(String fn, String ln, String mobile){
        this.fn = fn;
        this.ln = ln;
        this.mobile = mobile;
    }

}
