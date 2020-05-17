package DVD_Library;

public class MovieCollection {

    public Movie root;

    public void addMovie(String title){
        Movie mv = new Movie(title);
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

    public void inAlphabeticalOrder(){      // calls the recursive function bellow using the root
        alphabeticalOrder(root);
    }

    public void inReverseAlphabeticalOrder(){
        reverseAlphabeticalOrder(root);
    }

    private void alphabeticalOrder(Movie movie){  // recursive function to print movie titles from
        if(movie != null){                  // leftmost to rightmost node (alphabetical)
            alphabeticalOrder(movie.left);
            System.out.println(movie.title);
            alphabeticalOrder(movie.right);
        }
    }

    private void reverseAlphabeticalOrder(Movie movie){
        if(movie != null){
            reverseAlphabeticalOrder(movie.right);
            System.out.println(movie.title);
            reverseAlphabeticalOrder(movie.left);
        }
    }
}
