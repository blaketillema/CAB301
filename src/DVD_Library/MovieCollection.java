package DVD_Library;

public class MovieCollection {

    public Movie root;

    public void addMovie(String title, String info, int copies){
        Movie mv = new Movie(title, info, copies);
        if(root == null){
            root = mv;
        }
        else{
            root = addMovieRecursion(root, mv);
        }
    }

    private Movie addMovieRecursion(Movie root, Movie mv){
        if(root == null){
            return mv; // add movie
        }
        if(mv.title.compareTo(root.title) < 0){
            root.left = addMovieRecursion(root.left, mv);
        }
        else if(mv.title.compareTo(root.title) > 0){
            root.right = addMovieRecursion(root.right, mv);
        }
        return root;
    }

    public Movie search(Movie root, String title){
        if(root == null){
            return null;
        }
        else if(root.title.equals(title)){
            return root;
        }
        else{
            if(root.title.compareTo(title) < 0){
                search(root.left, title);
            }
            else{
                search(root.right, title);
            }
        }
        return root;
    }

    public void removeMovie(String title){
        root = removeMovieRecur(root, title);
    }

    private Movie removeMovieRecur(Movie root, String title){

        if(title == null){
            return root;
        }

        Movie parent = null;
        Movie current = root;

        while(current != null && title.compareTo(current.title) != 0){ //search for the title
            parent = current;
            if(title.compareTo(current.title) < 0){
                current = current.left;
            }
            else{
                current = current.right;
            }
        }

        if(current == null){ //if the search couldn't find anything
            return root;
        }
        // if the search could find something
        if(current.left == null && current.right == null){
            if(current != root){ //if the search has no branches
                if(parent.left == current){
                    parent.left = null;
                }
                else{
                    parent.right = null;
                }
            }
            else{ //if the search is the root
                root = null;
            }
        }
        else if(current.left != null && current.right != null){
            //if the search has two branches, find minimum leaf of right branch
            Movie replacement = min(current.right);
            removeMovieRecur(root, replacement.title);
            current.title = replacement.title;
        }
        else{
            //if the search has one branch
            Movie child = (current.left != null)? current.left: current.right;
            if(current != root){
                if(current == parent.left){
                    parent.left = child;
                }
                else{
                    parent.right = child;
                }
            }
            else{
                root = child;
            }
        }
        return root;
    }

    private Movie min(Movie m){ //used for finding the minimum leaf in a branch
        while(m.left != null){
            m = m.left;
        }
        return m;
    }

    public void borrowMovie(Movie root, Movie m){
        borrowMovieRecur(root, m);
    }

    private void borrowMovieRecur(Movie root, Movie m){
        if(root.title.equals(m.title)){
            root.timesBorrowed++;
            root.copiesAvailable--;
        }
        else{
            if(root.title.compareTo(m.title) < 0){
                borrowMovieRecur(root.left, m);
            }
            else{
                borrowMovieRecur(root.right, m);
            }
        }
    }

    public void returnMovie(Movie root, Movie m){
        returnMovieRecur(root, m);
    }

    private void returnMovieRecur(Movie root, Movie m){
        if(root.title.equals(m.title)){
            root.copiesAvailable++;
        }
        else{
            if(root.title.compareTo(m.title) < 0){
                returnMovieRecur(root.left, m);
            }
            else{
                returnMovieRecur(root.right, m);
            }
        }
    }

    public void inAlphabeticalOrder(){      // calls the recursive function bellow using the root
        inOrderTraversal(root);
    }

    public int length(Movie mv){
        if(mv == null){
            return 0;
        }
        else{
            return(length(mv.left) + 1 + length(mv.right));
        }
    }

    public void topTen(){
        int len = length(root);
        Movie[] movies = new Movie[len];
        movies = toArray(root, movies);
        for(int i = 0; i < len; i++){
            System.out.println(movies[i].title);
        }
    }

    private Movie[] toArray(Movie movie, Movie[] movies){
        if(movie != null){
            toArray(movie.left, movies);
            int i = 0;
            while(i < movies.length && movies[i] != null){
                i++;
            }
            movies[i] = movie;
            toArray(movie.right, movies);
        }
        return movies;
    }

    private void inOrderTraversal(Movie movie){  // recursive function to print movie titles from
        if(movie != null){                  // leftmost to rightmost node (alphabetical)
            inOrderTraversal(movie.left);
            System.out.println("Title: " + movie.title);
            System.out.println("Description: " + movie.info);
            System.out.println("Copies available: " + movie.copiesAvailable);
            System.out.println();
            inOrderTraversal(movie.right);
        }
    }

    private void inReverseOrderTraversal(Movie movie){
        if(movie != null){
            inReverseOrderTraversal(movie.right);
            System.out.println("Title: " + movie.title);
            System.out.println("Times borrowed: " + movie.timesBorrowed);
            inReverseOrderTraversal(movie.left);
        }
    }
}
