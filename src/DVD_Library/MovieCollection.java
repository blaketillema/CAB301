package DVD_Library;

public class MovieCollection {

    public Movie root;

    // adds a movie to the collection
    public void addMovie(String title, String info, int copies){
        Movie mv = new Movie(title, info, copies); // constructor
        if(root == null){
            root = mv; //if there isn't a root, the new movie becomes the root
        }
        else{
            // there's a root, decide if it should go left or right
            root = addMovieRecursion(root, mv);
        }
    }

    private Movie addMovieRecursion(Movie root, Movie mv){
        if(root == null){
            return mv; // add movie
        }
        if(mv.title.compareTo(root.title) < 0){
            // new title comes before the current node's title, go left
            root.left = addMovieRecursion(root.left, mv);
        }
        else if(mv.title.compareTo(root.title) > 0){
            // new title comes after the current node's title, go right
            root.right = addMovieRecursion(root.right, mv);
        }
        return root;
    }

    public Movie search(Movie root, String title){
        if(root == null || root.title.compareTo(title) == 0){
            // if the movie is or isn't found, return null or the movie
            return root;
        }
        if(root.title.compareTo(title) > 0){
            // the search title comes before the current node's title, go left
            return search(root.left, title);
        }
        // the search title comes after the current node's title, go right
        return search(root.right, title);
    }

    // calls the removal recursion
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

    // calls the borrowing recursion
    public void borrowMovie(Movie root, Movie m){
        borrowMovieRecur(root, m);
    }


    private void borrowMovieRecur(Movie root, Movie m){
        if(root.title.equals(m.title)){
            // when the movie is found, increment the times borrowed
            // and decrement the copies available
            root.timesBorrowed++;
            root.copiesAvailable--;
        }
        else{
            if(m.title.compareTo(root.title) < 0){
                // if the title to borrow comes before the root, go left
                borrowMovieRecur(root.left, m);
            }
            else{
                // if the title to borrow comes after the root, go right
                borrowMovieRecur(root.right, m);
            }
        }
    }

    //calls the return recursion
    public void returnMovie(Movie root, Movie m){
        returnMovieRecur(root, m);
    }

    private void returnMovieRecur(Movie root, Movie m){
        if(root.title.equals(m.title)){
            // when the movie is found, increment copies available
            root.copiesAvailable++;
        }
        else{
            if(m.title.compareTo(root.title) < 0){
                // if the title to return comes before the root, go left
                returnMovieRecur(root.left, m);
            }
            else{
                // if the title to return comes after the root, go right
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
            // returns length of bst by traversing each node and returning +1
            return(length(mv.left) + 1 + length(mv.right));
        }
    }

    public void topTen(){
        // get the length of the bst
        int len = length(root);
        // initialise an array of Movies the length of the bst
        Movie[] movies = new Movie[len];
        // fill the array with the nodes in the bst
        movies = toArray(root, movies);
        // sort the array low to high by times borrowed
        movies = heapsort(movies);
        int i = 0;
        int end = Math.min(len, 10);
        while(i < end){
            // print the last 10 entries of the array
            System.out.println(
                    movies[movies.length - 1 - i].title + " - " +
                    movies[movies.length - 1 - i].timesBorrowed
            );
            i++;
        }
    }

    private Movie[] heapsort(Movie[] movies){
        int n = movies.length;

        for(int i = n / 2 - 1; i >=0; i--){
            movies = heapify(movies, n, i);
        }

        for(int i = n - 1; i > 0; i--){
            Movie temp = movies[0];
            movies[0] = movies[i];
            movies[i] = temp;
            movies = heapify(movies, i, 0);
        }
        return movies;
    }

    private Movie[] heapify(Movie[] movies, int n, int i){
        int mostPopular = i;
        int l = 2*i;
        int r = 2*i + 1;

        if(l < n && movies[l].timesBorrowed > movies[mostPopular].timesBorrowed){
            mostPopular = l;
        }
        if(r < n && movies[r].timesBorrowed > movies[mostPopular].timesBorrowed){
            mostPopular = r;
        }
        if(mostPopular != i){
            Movie swap = movies[i];
            movies[i] = movies[mostPopular];
            movies[mostPopular] = swap;

            movies = heapify(movies, n, mostPopular);
        }
        return movies;
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
            System.out.println("\nTitle: " + movie.title);
            System.out.println("Description: " + movie.info);
            System.out.println("Copies available: " + movie.copiesAvailable);
            System.out.println("Times borrowed: " + movie.timesBorrowed);
            inOrderTraversal(movie.right);
        }
    }
}
