package DVD_Library;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        int menuOp = -1;
        StaffMenu staffMenu = new StaffMenu();
        MemberMenu memberMenu = new MemberMenu();
        MovieCollection mvc = new MovieCollection();
        MemberCollection mbc = new MemberCollection();

        while(menuOp != 0){
            Scanner in = new Scanner(System.in);
            System.out.println("1. Staff Menu");
            System.out.println("2. Member Menu");
            System.out.println("0. Exit");
            System.out.println();
            System.out.print("Please make a selection [1-2, or 0 to exit]: ");
            menuOp = in.nextInt();
            
            switch(menuOp){
                case 1:
                    staffMenu.staffMenu(mvc, mbc);
                    System.out.println();
                    break;
                case 2:
                    memberMenu.memberMenu(mvc, mbc);
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Goodbye.");
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + menuOp + '\n');
            }
        }
    }
}