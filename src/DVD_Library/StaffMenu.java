package DVD_Library;

import java.util.Scanner;

public class StaffMenu {


    public void staffMenu(MovieCollection mvc, MemberCollection mbc){

        if(login()){
            int option = -1;

            while (option != 0) {

                System.out.println("\n1. Add a movie\n2. Remove a movie\n3. Register a member\n4. Find a member's phone number");
                System.out.print("\nPlease make a selection [1-4, or 0 to exit]: ");
                Scanner in = new Scanner(System.in);
                option = in.nextInt();

                switch (option) {
                    case 1:
                        addMovie(mvc);
                        break;
                    case 2:
                        removeMovie(mvc);
                        break;
                    case 3:
                        addMember(mbc);
                        break;
                    case 4:
                        findMemberMobi(mbc);
                        break;
                    case 9:
                        testSetup(mvc, mbc);
                        break;
                }
            }
        }
    }

    private boolean login(){
        boolean loggedIn = false;
        while(!loggedIn){
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter username (or 0 to exit): ");
            String un = in.nextLine();
            if(un.equals("0")){
                break;
            }
            System.out.print("Please enter password: ");
            String pw = in.nextLine();
            if(un.equals("staff") && pw.equals("today123")){
                loggedIn = true;
            }
            else{
                System.out.println("Username or password incorrect. Try again.");
            }
        }
        return loggedIn;
    }

    private void testSetup(MovieCollection mvc, MemberCollection mbc){
        String[] movies = {"the", "quick", "brown", "fox", "jumps", "over", "lazy", "dog", "incy", "wincy", "spider", "climbed", "up", "water", "spout"};
        for(String movie : movies){
            mvc.addMovie(movie, movie, 10);
        }
        for(int i = 0; i < movies.length; i++){
            Movie mv = mvc.search(mvc.root, movies[i]);
            int j = i;
            while(j < movies.length){
                mvc.borrowMovie(mvc.root, mv);
                mvc.returnMovie(mvc.root, mv);
                j++;
            }
        }
        mbc.addMember("qwe", "po", "123");
    }

    private void addMovie(MovieCollection mvc){
        String addMvOp = "y";
        while(addMvOp.equals("y")){
            Scanner in = new Scanner(System.in);
            System.out.print("Please enter a title: ");
            String title = in.nextLine();
            System.out.print("Please enter a description: ");
            String info = in.nextLine();
            System.out.print("Please enter how many copies: ");
            int copies = in.nextInt();
            mvc.addMovie(title, info, copies);
            System.out.print("Add another? (y/n): ");
            addMvOp = in.next();
        }
    }

    private void removeMovie(MovieCollection mvc){
        String remMvOp = "y";
        while(remMvOp.equals("y")){
            Scanner in = new Scanner(System.in);
            mvc.inAlphabeticalOrder();
            System.out.print("\nPlease enter a title from above: ");
            String title = in.nextLine();
            mvc.removeMovie(title);
            System.out.print("Remove another movie? (y/n): ");
            remMvOp = in.nextLine();
        }
    }

    private void addMember(MemberCollection mbc){
        String addMbOp = "y";
        while(addMbOp.equals("y")){
            Scanner in = new Scanner(System.in);
            System.out.print("Enter first name: ");
            String fn = in.nextLine();
            System.out.print("Enter last name: ");
            String ln = in.nextLine();
            System.out.print("Enter mobile number: ");
            String mobile = in.nextLine();
            mbc.addMember(fn, ln, mobile);
            System.out.print("Add another member? (y/n): ");
            addMbOp = in.nextLine();
        }
    }

    private void findMemberMobi(MemberCollection mbc){
        String findMobOp = "y";
        while(findMobOp.equals("y")) {
            Scanner in = new Scanner(System.in);
            System.out.print("Enter first name: ");
            String fn = in.nextLine();
            System.out.print("Enter last name: ");
            String ln = in.nextLine();
            System.out.println("Mobile number: " + mbc.findMobile(fn, ln));
            System.out.print("Search again? (y/n): ");
            findMobOp = in.nextLine();
        }
    }
}
