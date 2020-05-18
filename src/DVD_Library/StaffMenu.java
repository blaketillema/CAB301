package DVD_Library;

import java.util.Scanner;

public class StaffMenu {

    private int option = -1;

    public void staffMenu(MovieCollection mvc, MemberCollection mbc){

        while(option != 0){

            System.out.println("1. Add a movie");
            System.out.println("2. Remove a movie");
            System.out.println("3. Register a member");
            System.out.println("4. Find a member's mobile number");
            System.out.print("Enter a number [1-4], or enter 0 to exit: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();

            switch(option){
                case 1:
                    addMovie(mvc);
                    System.out.println();
                    break;
                case 2:
                    removeMovie(mvc);
                    System.out.println();
                    break;
                case 3:
                    addMember(mbc);
                    System.out.println();
                    break;
                case 4:
                    findMemberMobi(mbc);
                    System.out.println();
                    break;
            }
        }

        System.out.println("Goodbye.");
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
            System.out.print("Please enter a title from above: ");
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
