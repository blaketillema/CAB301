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
            System.out.println("\n1. Staff Menu\n2. Member Menu\n0. Exit");
            System.out.print("\nPlease make a selection [1-2, or 0 to exit]: ");
            menuOp = in.nextInt();

            switch(menuOp){
                case 1:
                    staffMenu.staffMenu(mvc, mbc);
                    break;
                case 2:
                    memberMenu.memberMenu(mvc, mbc);
                    break;
            }
        }
        System.out.println("Goodbye. ");
    }
}