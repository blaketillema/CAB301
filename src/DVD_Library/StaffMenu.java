package DVD_Library;

import java.util.Scanner;

public class StaffMenu {


    public void staffMenu(MovieCollection mvc, MemberCollection mbc){

        int option = -1;

        while(option != 0){

            System.out.println("1. Add a movie\n2. Remove a movie\n3. Register a member\n4. Find a member's phone number\n");
            System.out.print("Please make a selection [1-4, or 0 to exit]: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();

            switch(option){
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
                    testSetup(mbc, mvc);
            }
        }

        System.out.println();
    }

    private void testSetup(MemberCollection mbc, MovieCollection mvc){
        mbc.addMember("grant", "macdonald", "18");
        String[] movies = {"eighteen", "naked", "cowboys", "in", "the", "showers", "at", "ram", "ranch", "big", "chickens", "wanting", "to", "cluck"};
        for(int i = 0; i < movies.length; i++){
            mvc.addMovie(movies[i], "info", 50);
        }
        int idk = 13;
        while(idk > 0){
            for(int i = 0; i < idk; i++){
                Movie mv = mvc.search(mvc.root, movies[i]);
                mvc.borrowMovie(mvc.root, mv);
                mvc.returnMovie(mvc.root, mv);
            }
            idk--;
        }
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
        System.out.println();
    }

    private void removeMovie(MovieCollection mvc){
        String remMvOp = "y";
        while(remMvOp.equals("y")){
            Scanner in = new Scanner(System.in);
            mvc.inAlphabeticalOrder();
            System.out.print("Please enter a title from above: ");
            String title = in.nextLine();
            mvc.removeMovie(title);
            System.out.print("Remove another movie? (y/n): ");
            remMvOp = in.nextLine();
        }
        System.out.println();
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
        System.out.println();
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
        System.out.println();
    }
}
