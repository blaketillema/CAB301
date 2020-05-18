package DVD_Library;

public class Movie {

    public Movie left, right;
    public String title;
    public String info;
    public int timesBorrowed;
    public int copiesAvailable;

    public Movie(String title, String info, int copiesAvailable){
        this.left = this.right = null;
        this.title = title;
        this.info = info;
        this.copiesAvailable = copiesAvailable;
        timesBorrowed = 0;
    }

}
