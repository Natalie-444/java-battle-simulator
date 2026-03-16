import java.io.File;
import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Game {
	//attributes
	private File file;       //private variables for reading the file and reading the characters
    private String fileName;          
    private Character[] characters; 
    private int numCharacters;         
	

	// Default Constructor
	public Game() {};

	//setupGame() method to start the game
	public void setupGame() {
		Scanner scanner = new Scanner(System.in);

        while(true) {   //enter a loop to choose the characters universe
            System.out.println("｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳");   //prints out the universe menu for aesthetic formating
            System.out.println("Please choose what universe you wish to play in!"); 
            System.out.println("1. Pokémon");
            System.out.println("2. Star Wars");
            System.out.println("3. Marvel");
            System.out.println("4. DC");
            String input = scanner.nextLine();  //user input of players choice of universe
            System.out.println("｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳･･｡･ﾟ⁎⁺˳");

            if (input.equals("1")) {            //first universe will return the filename for pokemon
                fileName = "pokemon.txt";
                break;
            } else if (input.equals("2")) {     //second universe will return the filename for starwars
                fileName = "starwars.txt";
                break;
            } else if (input.equals("3")) {     //third universe will return the filename for marvel
                fileName = "marvel.txt";
                break;
            } else if (input.equals("4")) {     //fourth universe will return the filename for dc
                fileName = "dc.txt";
                break;
            } else {
                System.out.println("That is an invalid input. Please try again!");  //to handle any other message player inputs
            }
        }

		this.file = new File(fileName); 	//replace with the correct filename based on the user's selection
	    this.countCharactersInFile();		//counting the characters in that file
	    this.readCharacters();				//read the characters and have them in an array
	}

	//countCharactersInFile() method for each file
	private void countCharactersInFile() {
		numCharacters = 0;	//keep count of the characters
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {	//go through each line to represent the number of characters
                scan.nextLine();
                numCharacters++;	//add to all the characters
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");	//file error
        }
	}

	//readCharacters() method for a character array to store stats
	private void readCharacters() {
		characters = new Character[numCharacters];
        try (Scanner scan = new Scanner(file)) {
            for (int i = 0; i < numCharacters; i++) {	//loop to go through every character
                String characterName = scan.next();		//the first item is the character name
                int hp = scan.nextInt();				//the second item is the character hp
                int attack = scan.nextInt();			//the third item is the character attack
                int defense = scan.nextInt();			//the fourth item is the character defense
                characters[i] = new Character(characterName, hp, attack, defense);	//creating a new character with all the stats found
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");	//file error
        }
	}
	
	//playOptionOne() method to play a one player game
	    public void playOptionOne() {
	    Random random = new Random();	//create random object
	    Scanner scanner = new Scanner(System.in);	//create random scanner

	    System.out.println(" ");    //space to keep output clearer
        int playerIndex = selectCharacter();	//call method to select character to set an index for the character array
        Character player = characters[playerIndex];

	    // Select difficulty
	    String difficulty;
	    while (true) {
	        System.out.println("Please choose your difficulty level!");
	        System.out.println("1. Easy");
	        System.out.println("2. Medium");
	        difficulty = scanner.nextLine();
	        if (difficulty.equals("1") || difficulty.equals("2")) {	//making sure the users option is valid
	            break;
	        } else {
	            System.out.println("Invalid input! Please try again.");	//let user know its invalid and try again
	        }
	    }

	    // Computer randomly selects a character from the file
	    int opponentIndex = random.nextInt(characters.length);
	    while (opponentIndex == playerIndex) {	//make sure computers character is not the same as player character
	        opponentIndex = random.nextInt(characters.length);
	    }
	    Character opponent = characters[opponentIndex];

        System.out.println();	//space to keep output clearer
        System.out.println("You will be battling: " + opponent.toString());	//printing out the opponent and stats
        System.out.println();	//space to keep output clearer

	    // Battle loop
	    boolean roundActive = true;	 //set true to keep the while loop running
	    int roundNum = 1;	//keep count of round rumbers 

	    while (roundActive) {
	        System.out.println("╔═══════════════════════════╗");
	        System.out.println("           Round " + roundNum);		//formatted clearly shown round sign and counter
	        System.out.println("╚═══════════════════════════╝");

	        // Player's turn
	        System.out.println("It is your turn! Choose what to do:");
	        System.out.println("1. Attack");
	        System.out.println("2. Heal");
	        System.out.println("3. Forfeit Game");
	        String choice = scanner.nextLine();	  //get the users option

	        //player turn
	        if (choice.equals("1")) {
	            player.attack(opponent);	//if player wants to attack call method
	            if (opponent.getCurrHp() <= 0) {
	                System.out.println(" ");    //space to keep output clearer
	                System.out.println("Oh no! " + opponent.getName() + " has fainted!"); //let know player that the opponent has no more hp
	                System.out.println("Congratulations! You win!");
	                System.out.println(" ");    //space to keep output clearer
	                System.out.println("Exiting to main menu...");
	                break;
	            }
	        } else if (choice.equals("2")) {
	            player.heal(1);		//if player wants to heal call method
	        } else if (choice.equals("3")) {
	            System.out.println(" ");    //space to keep output clearer
	            System.out.println("You have forfeited the game! :(");	//if player wants to forfeit game
	            System.out.println(" ");    //space to keep output clearer
	            System.out.println("Exiting to main menu...");
	            break;
	        } else {
	            System.out.println("Invalid input! Try again.");
	            continue;
	        }

	        //computer's turn
	        System.out.println("Opponent's Turn...");
	        if (difficulty.equals("1")) { //easy mode
	            if (random.nextBoolean()) {	 //random move for true or false
	                opponent.attack(player);	//if true opponent will attack
	                if (player.getCurrHp() <= 0) {
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Oh no! " + player.getName() + " has fainted!");  //let know player that they have no more hp
	                    System.out.println("You lost! Try again next time.");
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Returning to main menu...");
	                    break;
	                }
	            } else {
	                opponent.heal(1);	//if false opponent will heal
	            }
	        } else { //meddium mode
	            int move = opponent.calculateBestMove(player);	//call the smart computer for the opponents turn
	            if (move == 1) {
	                opponent.attack(player);	//if the computer wants to attack call the attack method
	                if (player.getCurrHp() <= 0) {
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Oh no! " + player.getName() + " has fainted!");	//let know player that the opponent has no more hp
	                    System.out.println("You lost! Try again next time.");
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Returning to main menu...");
	                    break;
	                }
	            } else if (move == 2) {
	                opponent.heal(1);	//if the computer wants to heal call the heal method
	            }
	        }
	        roundNum++; //keep track of how many rounds by adding
	    }
    }

	//playOptionTwo() method to play a two player game
	public void playOptionTwo() {
		Scanner scanner = new Scanner(System.in);

        System.out.println("Enter player ones's name:");
        String player1Name = scanner.nextLine();	//get player ones name

        System.out.println("Enter player twos's name:");
        String player2Name = scanner.nextLine();	//get player twos name

        while(player1Name.equals(player2Name)) {
            System.out.println("Player one choose that name! Please choose a new name.");
            player2Name = scanner.nextLine();	//player two cannot have the same name as player one
        }

        System.out.println(" ");    //space for formating
        System.out.println(player1Name + " you are up!");
        int player1Index = selectCharacter();	//call method for player to select their character
        Character player1 = characters[player1Index];	//set their character

        System.out.println(player2Name + " you are up!");
        int player2Index = selectUniqueCharacter(player1Index);	//call method for player two to select their character
        Character player2 = characters[player2Index];	//set their character

        Character char1 = characters[player1Index];	//get a character for character array to make it easier to call
        Character char2 = characters[player2Index];

        int roundNum = 1;	//keep count of rounds
        boolean roundActive = true;	//set true for the loop to continue

        while(roundActive) {
        	System.out.println(" ");    //space to keep output clearer
            System.out.println("╔═══════════════════════════╗");
            System.out.println("           Round " + roundNum);	  //nice way to format what round it is for the player
            System.out.println("╚═══════════════════════════╝");

            //player one turn
            System.out.println("It is " + player1Name + "'s turn! Please choose what to do.");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Forfeit Game");
            String choice1 = scanner.nextLine();	//get player ones turn
            System.out.println(" ");    //space to keep output clearer

            if (choice1.equals("1")) {
                char1.attack(char2);	//call attack method if player one chooses one
                if (char2.getCurrHp() <= 0) { 
                	System.out.println(" ");    //space to keep output clearer
                    System.out.println("Oh no! " + char2.getName() + " has fainted!"); //let player know character two has no more hp
                    System.out.println(player1Name + " wins! Congratulations!");    //let player know who won
                    System.out.println(" ");    //space to keep output clearer
                    System.out.println("Exiting to main menu...");  
                    return; 
                }
            } else if (choice1.equals("2")) {
                char1.heal(1);	//call heal method if player one chooses two
            } else if (choice1.equals("3")) { 
            	System.out.println(" ");    //space to keep output clearer
                System.out.println(player1Name + " has forfeited! :( ");   //let player know who forfeited
                System.out.println(" ");    //space to keep output clearer
                System.out.println("Exiting to main menu...");  
                return;
            } else {
			    System.out.println("Invalid input! Try again.");
			    continue;   //allow player to try again
			}

            //player two turn
            System.out.println("It is " + player2Name + "'s turn! Please choose what to do.");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Forfeit Game");
            String choice2 = scanner.nextLine();
            System.out.println(" ");    //space to keep output clearer

            if (choice2.equals("1")) {
                char2.attack(char1);   //if player two wants to attack call method
                if (char1.getCurrHp() <= 0) { 
                	System.out.println(" ");    //space to keep output clearer 
                	System.out.println("Oh no! " + char1.getName() + " has fainted!"); //let player know character two has no more hp
                    System.out.println(player1Name + " wins! Congratulations!");    //let player know who won
                	System.out.println(" ");    //space to keep output clearer
                    System.out.println("Exiting to main menu...");  
                	return; 
            	}
            } else if (choice2.equals("2")) {
                char2.heal(1);	//if player two wants to heal call method
            } else if (choice2.equals("3")) { 
            	System.out.println(" ");    //space to keep output clearer
                System.out.println(player2Name + " has forfeited! :( "); 	//let player know who forfeited
                System.out.println(" ");    //space to keep output clearer
                System.out.println("Exiting to main menu...");  
                return; 
            } else {
			    System.out.println("Invalid input! Try again.");
			    continue;  //allow player to try again
			}

            roundNum++;	//keep track of how many rounds by adding
        }
	}

	//playOptionThree() method to watch a recursive battle between characters
	public int playOptionThree() {
		Random rand = new Random();	//create random object

        int playerOneIndex = rand.nextInt(characters.length);	//choose two random characters from the length of the character file
        int playerTwoIndex = rand.nextInt(characters.length);

        while (playerTwoIndex == playerOneIndex) {	//if characters are the same run through the while loop for a new character
            playerTwoIndex = rand.nextInt(characters.length);
        }

        //get both of the character stats in the character array
        Character character1 = new Character(characters[playerOneIndex].getName(), characters[playerOneIndex].getMaxHp(), characters[playerOneIndex].getAttack(), characters[playerOneIndex].getDefense());
        Character character2 = new Character(characters[playerTwoIndex].getName(), characters[playerTwoIndex].getMaxHp(), characters[playerTwoIndex].getAttack(), characters[playerTwoIndex].getDefense());

        //let player know what characters were choosen
        System.out.println("Recursive battle between " + character1.getName() + " and " + character2.getName() + "!");

        int winner = playRecursively(character1, character2, 50, 1);	//call the recursive method for characters to play

        if (winner == -1) {
            System.out.println("The battle is a tie!");	//print if the battle is a tie
        } else {
            Character winningCharacter;
            if (winner == 0) {
                winningCharacter = character1;	//set winner if character one won
            } else {
                winningCharacter = character2;	//set winner if character two won
            }
            System.out.println("The winner is " + winningCharacter.getName() + "!"); //print winning character
        }
        return winner;
	}

	//playOptionFour() method to play a battle player vs computer between universes
	public void playOptionFour() {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);	//create scanner and random objects
        Random rand = new Random();

        String difficulty;
	    while (true) {
	        System.out.println("Please choose your difficulty level!");
	        System.out.println("1. Easy");
	        System.out.println("2. Medium");
	        difficulty = scanner.nextLine();
	        if (difficulty.equals("1") || difficulty.equals("2")) {	//making sure the users option is valid
	            break;
	        } else {
	            System.out.println("Invalid input! Please try again.");	//let user know its invalid and try again
	        }
	    }

        loadAllUniverses();	//print out each universe

        System.out.println(" ");    //space to keep output clearer
        int playerIndex = selectCharacter();	//call method for player to choose character
        Character player = characters[playerIndex];	//set players character

        int oppIndex = rand.nextInt(characters.length);	//opponent chooses their character
        while (oppIndex == playerIndex) {	//if the characters are the same pick a new one
            oppIndex = rand.nextInt(characters.length);
        }
        Character opponent = characters[oppIndex];	//set opponents character

        System.out.println();
        System.out.println("You will be battling: " + opponent.toString());
        System.out.println();

        int roundNum = 1;
        while (true) {	//loop to run the rounds
            System.out.println("╔════════════════════════════╗");
            System.out.println("          Round " + roundNum);		   //keep track of what round it is
            System.out.println("╚════════════════════════════╝");

            System.out.println(" ");    //space to keep output clearer
            System.out.println("It is your turn! Choose what to do:");
            System.out.println("1. Attack");
            System.out.println("2. Heal");
            System.out.println("3. Forfeit");
            String choice = scanner.nextLine();	//players turn
            System.out.println(" ");    //space to keep output clearer

            //player turn
            if (choice.equals("1")) {
                player.attack(opponent);	//call method if player wants to attack
                if (opponent.getCurrHp() <= 0) {
                    System.out.println("Oh no! " + opponent.getName() + " has fainted!"); //let know player that the opponent has no more hp
	                System.out.println("Congratulations! You win!");
                    System.out.println(" ");    //space to keep output clearer
	                System.out.println("Exiting to main menu...");
                    return;
                }
            } else if (choice.equals("2")) {
                player.heal(1); 	 //call method if player wants to heal
            } else if (choice.equals("3")) {
                System.out.println("You have forfeited the game! :(");	//let player know game is forfeited
	            System.out.println(" ");    //space to keep output clearer
	            System.out.println("Exiting to main menu...");
                return;
            } else {
            	System.out.println("Invalid input please try again.");
                continue;
            }

            //computer's turn
            System.out.println("Opponent's Turn...");
            System.out.println(" ");    //space to keep output clearer
	        if (difficulty.equals("1")) { //easy mode
	            if (random.nextBoolean()) {	 //random move for true or false
	                opponent.attack(player);	//if true opponent will attack
	                if (player.getCurrHp() <= 0) {
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Oh no! " + player.getName() + " has fainted!");  //let know player that they have no more hp
	                    System.out.println("You lost! Try again next time.");
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Returning to main menu...");
	                    break;
	                }
	            } else {
	                opponent.heal(1);	//if false opponent will heal
	            }
	        } else { //meddium mode
	            int move = opponent.calculateBestMove(player);	//call the smart computer for the opponents turn
	            if (move == 1) {
	                opponent.attack(player);	//if the computer wants to attack call the attack method
	                if (player.getCurrHp() <= 0) {
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Oh no! " + player.getName() + " has fainted!");	//let know player that the opponent has no more hp
	                    System.out.println("You lost! Try again next time.");
	                    System.out.println(" ");    //space to keep output clearer
	                    System.out.println("Returning to main menu...");
	                    break;
	                }
	            } else if (move == 2) {
	                opponent.heal(1);	//if the computer wants to heal call the heal method
	            }
	        }

            roundNum++;	//keep track of how many rounds by adding
        }
        System.out.println("Please reload your selected universe...");
    	setupGame();
	}

	//playRecursively() method to use in option three to watch the recursive battle
	private int playRecursively(Character playerOne, Character playerTwo, int maxRounds, int roundNum) {
		Random rand = new Random();

        //listing all base cases
        if (playerOne.getCurrHp() <= 0) { 
        	return 1; 		//character two wins
    	}
	    if (playerTwo.getCurrHp() <= 0) {
	    	return 0; 		  //character one wins
	    }
	    if (maxRounds == 0) {
	    	return -1; 			// Tie
	    }
	    System.out.println("╔════════════════════════════╗");
        System.out.println("          Round " + roundNum);		   //keep track of what round it is
        System.out.println("╚════════════════════════════╝");
        System.out.println(" ");    //space to keep output clearer

        int moveOne = playerOne.calculateBestMove(playerTwo);   //use the smart computer for the first move
        if (moveOne == 1) {         //if move is 1 call the attack method
            playerOne.attack(playerTwo);
        } else {                    //if move is 2 call the heal method
            playerOne.heal(1);
        }
       if (playerTwo.getCurrHp() <= 0) {
       		return 0; 	//if player two has no hp player one wins
   		}

        boolean attackMove = rand.nextBoolean();    //computer will decide to attack or heal randomly
        if (attackMove) {       //if computer chooses to attack call the method
            playerTwo.attack(playerOne);
        } else {                //if computer chooses to heal then it calls the heal method
            playerTwo.heal(1);
        }

         if (playerOne.getCurrHp() <= 0) {
         	return 1;     //if player one dies player two wins
        }
        //recursive call
        return playRecursively(playerOne, playerTwo, maxRounds - 1, roundNum + 1);
	}

	//selectCharacter() method to use when a player needs to pick their character
	private int selectCharacter() {
	    Scanner input = new Scanner(System.in);
	    System.out.println("Please select the character you wish to play as!");	//print all the characters

	    for (int i = 0; i < characters.length; i++) {
            System.out.println((i + 1) + ". " + characters[i].toString());
        }

	   int choice;
	   while (true) {  
		    System.out.println("Input the number for the character you wish to play.");
		    if (input.hasNextInt()) {	//if players input is a number it will check if it is valid
		        choice = input.nextInt() - 1;
		        if (choice >= 0 && choice < characters.length) { 
		            break; //if valid input break
		        } else {
		            System.out.println("That has been choosen please try again.");	//if it is not valid player tries again
		        }
		    } else {
		        System.out.println("Invalid input! Try again.");	//if it is not valid player tries again
		        input.next();  //clear bad input
		    }
		}

		System.out.println(" ");    //space to keep output clearer
	    System.out.println("You selected: " + characters[choice].getName() + "!");	//let player know what character they selected
	    System.out.println(" ");    //space to keep output clearer
	    return choice;	//return players character
	}	

    //additional method to help choose a character different from player one for my option two
    private int selectUniqueCharacter(int takenIndex) {	//takes in players character choice
        Scanner input = new Scanner(System.in);

        System.out.println("Please select the character you wish to play as!");

        for (int i = 0; i < characters.length; i++) {  //prints out all the characters to choose from
            System.out.println((i + 1) + ". " + characters[i].toString());
        }	

        int choice = -1;
        while (true) {	//loop to ensure valid character choice
            System.out.println("Input the number for the character you wish to play.");
            if (input.hasNextInt()) {
                choice = input.nextInt() - 1;
                if (choice >= 0 && choice < characters.length && choice != takenIndex) {
                	break;	//if the player twos choice is valid then break
                } else {
                	System.out.println("That has been choosen please try again.");
                }
            } else {
            	input.next();
            	System.out.println("Invalid! Try again.");	//if it is not valid try again
        	}
        }
        System.out.println(" ");    //space to keep output clearer
	    System.out.println("You selected: " + characters[choice].getName() + "!");	//let player know what character they selected
	    System.out.println(" ");    //space to keep output clearer
        return choice;	//return player twos valid choice
    }

    //additional method to help load all the universes for my option four
	private void loadAllUniverses() {
        String[] universeFiles = getUniverseFiles(); // get all .txt files
        int total = 0;
        for (int i = 0; i < universeFiles.length; i++) {
		    total += countCharactersInFiles(universeFiles[i]);	//count how many characters there are
		}

        characters = new Character[total];	//create character array for all characters
        int index = 0;
        for (int i = 0; i < universeFiles.length; i++) {
    		String fileName = universeFiles[i];
            try (Scanner scanner = new Scanner(new File(fileName))) {	//read all the files and set the character array
                while (scanner.hasNext()) {
                    String name = scanner.next();			//the first item is the character name
                	int hp = scanner.nextInt();				//the second item is the character hp
                	int attack = scanner.nextInt();			//the third item is the character attack
               		int defense = scanner.nextInt();			//the fourth item is the character defense
                    characters[index++] = new Character(name, hp, attack, defense);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");	//if file cant be found
            }
        }
    }

    //additional method to help count characters in all the files
    private int countCharactersInFiles(String fileName) {	
        int count = 0;
        try (Scanner scan = new Scanner(new File(fileName))) {	//read a new file name
            while (scan.hasNextLine()) {
                scan.nextLine();	//count all characters based on the lines
                count++;
            }
        } catch (FileNotFoundException e) {
                System.out.println("File not found");	//if file cant be found
        }
        return count;
    }

    //additional method to get all the universe character.txt files in the current folder to use
    private String[] getUniverseFiles() {
	    File folder = new File(".");  // create a new folder in current directory
	    File[] files = folder.listFiles();  //get all files in current directory and put them in the folder

	    int count = 0;
	    //count how many .txt files there are
	    for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile() && files[i].getName().endsWith(".txt")) {	//make sure it is a .txt file to count
	            count++;
	        }
	    }

	    //create an array to store alll the .txt filenames
	    String[] txtFiles = new String[count];
	    int index = 0;
	    for (int i = 0; i < files.length; i++) {
	        if (files[i].isFile() && files[i].getName().endsWith(".txt")) {		//make sure it is a .txt file to add to array
	            txtFiles[index] = files[i].getName();
	            index++;
	        }
	    }
	    return txtFiles;	//return all the files array so they can be used
	}

}