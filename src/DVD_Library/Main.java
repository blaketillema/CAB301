package DVD_Library;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        Scanner scanner = new Scanner(System.in);
        String in = "";
        boolean movie = false;
        boolean user = false;

        while(!in.equals("0")){

            if(in.equals("1")){
                user = true;
                movie = false;
            }
            else if(in.equals("2")){
                movie = true;
                user = false;
            }
            else if(in.equals("3")){
                movie = false;
                user = false;
                in = "";
            }
            else{
                System.out.println("Select an option [1 - 2]\n 1. user menu\n 2. movie menu\n\n 0. exit");
                System.out.print("Select: ");
                in = scanner.nextLine();
            }
            if(user){
                System.out.println("-----User Menu-----");
                in = scanner.nextLine();
            }
        }
    }
}