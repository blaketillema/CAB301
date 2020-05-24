package DVD_Library;

import java.util.Scanner;

public class MemberMenu {

    public void memberMenu(MovieCollection mvc, MemberCollection mbc){
        // main menu
        // menu will only run if a valid member is found and logged in
        Member mb = login(mbc);

        if(mb != null){
            Scanner in = new Scanner(System.in);
            int menuOp = -1;
            while(menuOp != 0){
                System.out.print("\n   Member Menu");
                System.out.println("\n1. Display all movies\n2. Borrow a movie\n3. Return a movie\n4. List current borrowed movies\n5. Top 10 most popular\n0. Exit");
                System.out.print("\nPlease make a selection [1-5, or 0 to exit]: ");
                menuOp = in.nextInt();
                switch(menuOp){
                    case 1:
                        allMovies(mvc);
                        break;
                    case 2:
                        borrow(mb, mvc);
                        break;
                    case 3:
                        returnMv(mb, mvc);
                        break;
                    case 4:
                        currentBorrowedMv(mb);
                        break;
                    case 5:
                        mvc.topTen();
                        break;
                }
            }
        }
    }

    public Member login(MemberCollection mbc){
        boolean loggedIn = false;
        while(!loggedIn){
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter username (or 0 to exit): ");
            String un = in.nextLine();
            if(un.equals("0")){
                break;
            }
            //searches for the username entered
            Member mb = mbc.searchUn(un);
            if(mb != null && mb.pw != -1){
                //if the member exists and already has a passcode
                System.out.print("Please enter passcode: ");
                int pw = in.nextInt();
                if(mbc.login(un, pw)){
                    return mb;
                }
                else{
                    //matching username and password weren't found
                    System.out.println("Username or passcode is incorrect. Try again.");
                }
            }
            else if(mb != null && mb.pw == -1){
                // member exists, but has no associated password
                int pw = -1;
                while(String.valueOf(pw).length() != 4){
                    System.out.print("Please enter a new 4-digit passcode: ");
                    pw = in.nextInt();
                }
                mb.setPassword(pw);
                System.out.println("Password set! Please log in.");
            }
            else{
                // no matching username was found
                System.out.println("User not found.");
            }
        }
        return null;
    }

    // prints all movies in order given a moviecollection (in this case root)
    private void allMovies(MovieCollection mvc){
        mvc.inAlphabeticalOrder();
    }

    private void borrow(Member mb, MovieCollection mvc){
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a movie to borrow: ");
        String title = in.nextLine();
        // stores a copy of the movie the member wants to borrow to minimise re-searching
        Movie toBorrow = mvc.search(mvc.root, title);
        // checks if the user already has a copy out
        Movie memberCopy = mvc.search(mb.mc.root, title);
        if(toBorrow == null){
            // movie couldn't be found in the collection
            System.out.println("Movie not found");
        }
        else{
            if(memberCopy == null){
                // user doesn't have a copy out
                mvc.borrowMovie(mvc.root, toBorrow);
                mb.mc.addMovie(toBorrow.title, toBorrow.info, 1);
            }
            else{
                // user does have a copy out
                System.out.println("You already have a copy");
            }
        }
    }

    private void returnMv(Member mb, MovieCollection mvc){
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a movie to return: ");
        // searches the users own member collection to see if they have the movie
        Movie toReturn = mb.mc.search(mb.mc.root, in.nextLine());
        if(toReturn == null){
            // user doesn't have a copy of the movie out
            System.out.println("Movie not found");
        }
        else{
            // returns the movie to the library collection
            // and removes it from the users collection
            mvc.returnMovie(mvc.root, toReturn);
            mb.mc.removeMovie(toReturn.title);
        }
    }

    private void currentBorrowedMv(Member mb){
        mb.mc.inAlphabeticalOrder();
    }
}
