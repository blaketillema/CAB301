public class MovieCollection {

    private Movie root;

    public void addMovie(String title, String information, int copiesAvailable){
        Movie mv = new Movie(title, information, copiesAvailable);
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
        root = removeMovieRecursion(root, title); // call the recursive function
    }

    private Movie removeMovieRecursion(Movie root, String title){
        if(root == null){
            return root; // empty tree
        }
        if(title.compareTo(root.title) < 0){
            root.left = removeMovieRecursion(root.left, title); // look for the title down the left branch
        }
        else if(title.compareTo(root.title) > 0){
            root.right = removeMovieRecursion(root.right, title); // look for the title down the right branch
        }
        else{ // if the title is found
            if(root.left == null){
                return root.right; // if the movie has no left child, replace it with the right child
            }
            else if(root.right == null){ // if the movie has no right child, replace it with the left child
                return root.left;
            }
            root = min(root.right); // if the movie has two children, replace it with the minimum right child

            root.right = removeMovieRecursion(root.right, root.title); // delete the minimum right child
        }
        return root;
    }

    private Movie min(Movie root){ // searches for the minimum leaf given a root
        Movie min = root;
        if(min.left != null) min = min(min.left);
        return min;
    }

    public void inAlphabeticalOrder(){      // calls the recursive function bellow using the root
        inOrderMovie(root);
    }

    private void inOrderMovie(Movie movie){  // recursive function to print movie titles from
        if(movie != null){                  // leftmost to rightmost node (alphabetical)
            inOrderMovie(movie.left);
            System.out.println(movie.title);
            inOrderMovie(movie.right);
        }
    }
}
