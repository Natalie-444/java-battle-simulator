import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Menu {
  //implemented main() method to start a game so user can play
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    //call universe method to get the filename
        Game game = new Game();
        game.setupGame();

        //initalize starting variable
        boolean gamePlay = true; //keeps game running until turned false

    //the main menu screen
        while (gamePlay) {
        System.out.println("｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳");   //aesthetic main menu for formating
        System.out.println("╔══════════════════════════════╗");
        System.out.println("    Ultimate Battle Simulator    ");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳");
        System.out.println("Hello! Welcome to the Ultimate Battle Simulator!");
        System.out.println("｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳");
        System.out.println("Please choose what you wish to do.");
        System.out.println("1. Play a One Player Game!");
        System.out.println("2. Play a Two Player Game!");
        System.out.println("3. Watch a Recursive Battle!");
        System.out.println("4. Play in Multiple Universes!");
        System.out.println("5. Change your Universe!");
        System.out.println("6. View Game Credits!");
        System.out.println("7. Exit!");
        String input = scanner.nextLine();  //getting user input
        System.out.println("｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳");

            if (input.equals("1")) {    
        //first option from main menu
              //a one player game
            game.playOptionOne();   //call the game.java file to play option one

            } else if (input.equals("2")) {     
        //second option from main menu
              //a two player game
              game.playOptionTwo();  //call the game.java file to play option two

            } else if (input.equals("3")) {     
        //third option from main menu
              //a recursive battle game
              game.playOptionThree();  //call the game.java file to play option three

            } else if (input.equals("4")) {     
        //fourth option from main menu
              //a one player battle between all universes
              game.playOptionFour();  //call the game.java file to play option

            } else if (input.equals("5")) {     
        //fifth option from main menu
                //change universes
                System.out.println("Loading...");
                game.setupGame();     //call universe method to get the new filename
                System.out.println("Your universe has been changed!");
                System.out.println("Loading Main Menu...");   //returns to main menu

            } else if (input.equals("6")) {     
        //sixth option from main menu
                System.out.println("╔═══════════════════════════╗");    //output for game credits
                System.out.println("         Game Credits        ");
                System.out.println("╚═══════════════════════════╝");
                System.out.println("Code developed by Natalie Rodriguez.");
                System.out.println("December 5, 2025.");
                System.out.println("Sources: CS1301 Unit 1-3 Material, Assisted by geeksforgeeks.");  //geeksforgeeks helped my further understanding in file scanner, random and recursive methods

            } else if (input.equals("7")) {     
        //seventh option from main menu
                System.out.println("Thank you for playing! Until next time!");     //end game message
                System.out.println("｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡");
                System.out.println("╔═══════════════════════════╗");    //output for exiting game
                System.out.println("           GoodBye!          ");
                System.out.println("╚═══════════════════════════╝");
                gamePlay = false;       //stop the while loop from running

            } else {
                System.out.println("That is an invalid input. Please try again!");      //to handle any other message player inputs
            }

        }
    }
  }
