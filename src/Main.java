public class Main {

    public static void main(String args[]){

        String[] movies = {"b", "q", "a", "s", "p", "g", "f"};
        MovieCollection mvc = new MovieCollection();
        for(int i = 0; i < movies.length; i++){
            mvc.addMovie(movies[i], "info", 10);
        }

        MemberCollection mbc = new MemberCollection();
        mbc.addMember("jef");
        mbc.addMember("manem");
        mbc.getMembers();

    }

}
