import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.TreeVisitor;

public class MovieCollection {

    private Movie root;

    public void addMovie(String title, String information, int copiesAvailable){
        if(root == null){
            root = new Movie(title, information, copiesAvailable);
        }
        Movie nodeLocation = root;
        while(true){
            if(title.compareTo(nodeLocation.title) < 0){ // compare left node if exists
                if(nodeLocation.left != null) nodeLocation = nodeLocation.left;
                else { nodeLocation.left = new Movie(title, information, copiesAvailable); }
            }
            else if(title.compareTo(nodeLocation.title) > 0){ // compare right node if exists
                if(nodeLocation.right != null) nodeLocation = nodeLocation.right;
                else { nodeLocation.right = new Movie(title, information, copiesAvailable); }
            }
            else break; // node has same name
        }
    }
    public void inAlphabeticalOrder(){
        inOrderMovie(root);
    }
    public void inOrderMovie(Movie movie){
        if(movie != null){
            inOrderMovie(movie.left);
            System.out.println(movie.title);
            inOrderMovie(movie.right);
        }
    }
}
