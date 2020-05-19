package DVD_Library;

import java.util.Scanner;

public class MemberMenu {

    public void memberMenu(MovieCollection mvc, MemberCollection mbc){

        Member mb = login(mbc);

        if(mb != null){
            Scanner in = new Scanner(System.in);
            int menuOp = -1;
            while(menuOp != 0){
                System.out.println("1. Display all movies\n2. Borrow a movie\n3. Return a movie\n4. List current borrowed movies\n5. Top 10 most popular\n0. Exit\n");
                System.out.print("Please make a selection [1-5, or 0 to exit]: ");
                menuOp = in.nextInt();
                System.out.println();
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
            Member mb = mbc.searchUn(un);
            if(mb != null && mb.pw != -1){
                System.out.print("Please enter passcode: ");
                int pw = in.nextInt();
                if(mbc.login(un, pw)){
                    System.out.println();
                    return mb;
                }
                else{
                    System.out.println("Username or passcode is incorrect. Try again.");
                }
            }
            else if(mb != null && mb.pw == -1){
                int pw = -1;
                while(String.valueOf(pw).length() != 4){
                    System.out.print("Please enter a new 4-digit passcode: ");
                    pw = in.nextInt();
                }
                mb.setPassword(pw);
                System.out.println("Password set! Please log in.");
            }
            else{
                System.out.println("User not found.");
            }
        }
        return null;
    }

    private void allMovies(MovieCollection mvc){
        mvc.inAlphabeticalOrder();
    }

    private void borrow(Member mb, MovieCollection mvc){
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a movie to borrow: ");
        String title = in.nextLine();
        Movie toBorrow = mvc.search(mvc.root, title);
        Movie memberCopy = mvc.search(mb.mc.root, title);
        if(toBorrow == null){
            System.out.println("Movie not found");
        }
        else{
            if(memberCopy == null){
                mvc.borrowMovie(mvc.root, toBorrow);
                mb.mc.addMovie(toBorrow.title, toBorrow.info, 1);
            }
            else{
                System.out.println("You already have a copy");
            }
        }
        System.out.println();
    }

    private void returnMv(Member mb, MovieCollection mvc){
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter a movie to return: ");
        Movie toReturn = mb.mc.search(mb.mc.root, in.nextLine());
        if(toReturn == null){
            System.out.println("Movie not found");
        }
        else{
            mvc.returnMovie(mvc.root, toReturn);
            if(toReturn.copiesAvailable == 1){
                mb.mc.removeMovie(toReturn.title);
            }
            else{
                toReturn.copiesAvailable--;
            }
        }
        System.out.println();
    }

    private void currentBorrowedMv(Member mb){
        mb.mc.inAlphabeticalOrder();
    }
}
