public class Main {

    public static void main(String args[]){

        String[] movies = {"b", "q", "a", "s", "p", "g"};
        MovieCollection mc = new MovieCollection();
        for(int i = 0; i < movies.length; i++){
            mc.addMovie(movies[i], "info", 10);
        }
        mc.inAlphabeticalOrder();

    }

}
