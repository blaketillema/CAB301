public class Movie {

    public Movie left, right;
    public String title;
    public String information;
    public int timesBorrowed;
    public int copiesAvailable;

    public Movie(String title, String information, int copiesAvailable){
        this.left = this.right = null;
        this.title = title;
        this.information = information;
        timesBorrowed = 0;
        this.copiesAvailable = copiesAvailable;
    }

}
