package DVD_Library;

public class Movie {

    public Movie left, right;
    public String title;
    public int timesBorrowed;

    public Movie(String title){
        this.left = this.right = null;
        this.title = title;
        timesBorrowed = 0;
    }

}
