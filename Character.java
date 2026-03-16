import java.util.Random;

public class Character {	//a class for all the character methods
	//attributes
	private String name;	//private variables for the character stats
	private int maxHp;
	private int currHp;   
	private int attack;
	private int defense;

	// Default Constructor
	public Character() {};

	// Constructor that should take in name, maxHp, attack, and defense, and set all attribute value
	public Character(String name, int maxHp, int attack, int defense) {
		this.name = name;
		this.maxHp = maxHp;
		this.currHp = maxHp;	//start at full health
		this.attack = attack;
		this.defense = defense;
	}

	//all of the getters for all attributes
	public String getName() {
		return this.name;
	}

	public int getMaxHp() {
		return this.maxHp;
	}

	public int getCurrHp() {
		return this.currHp;
	}

	public int getAttack() {
		return this.attack;
	}

	public int getDefense() {
		return this.defense;
	}

	//all of the setters for all attributes
	public void setName(String name) {
		this.name = name;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public void setCurrHp(int currHp) {
		this.currHp = currHp;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	//the method to call for a character to heal
	public void heal(int numCharacters) {
		Random randomNum = new Random();
		int randomNumber = randomNum.nextInt();

        if (randomNumber < 0) {
            randomNumber = -randomNumber; //if number ends up negative we can make it positive
        }
        int healAmount = 4 + (randomNumber % 3);  //given heal amount formula
        if (this.currHp + healAmount > this.maxHp) {   //character cannot heal more than their max hp
            healAmount = this.maxHp - this.currHp;
        }
        this.currHp += healAmount;

        //printing out what is happening during the heal
        System.out.println(this.name+" has healed by " +healAmount+ "!");
        System.out.println(this.name+" now has an HP of " + this.currHp + "!");
        System.out.println(" ");    //space for formating
	}

	//the method to call for a character to attack
	public void attack(Character opponent) {
        int damage = this.attack - opponent.defense;      //formula for how much the attack
        if (damage < 0) {
            damage = 0;     //will not allow negative damage
        }
        int newHp = opponent.currHp - damage;   //formula for how much damage character will take
        if (newHp < 0) {
            newHp = 0;  //no negative hp for formating purposes
        }
        opponent.currHp = newHp;    //update the array with the new hp
        //printing out what is happening during the attack
        System.out.println("Oh no! " + this.name + " has attacked " + opponent.name + " with an attack strength of " + this.attack + "!");
        System.out.println(" ");    //space for formating
        System.out.println(opponent.name + " has defended by " + opponent.defense + "!");
        System.out.println(opponent.name + " now has " + newHp + " HP!");
        System.out.println(" ");    //space for formating
	}

	//the method to call for the smart computer
	public int calculateBestMove(Character opponent) {
		int compHp = this.currHp;		//creating variables from the attributes so it is easier to use them
        int compMaxHp = this.maxHp;
        int compAttack = this.attack;
        int compDefense = this.defense;
        int oppHp = opponent.currHp;
        int oppAttack = opponent.attack;
        int oppDefense = opponent.defense;

        if (compAttack - oppDefense >= oppHp) {   //computer will attack if the player is one shot
            return 1;
        }

        if (compHp <= (oppAttack - compDefense)) {   //computer will heal if the players attack is higher than its hp
            return 2;
        }

        if (compHp <= compMaxHp - 5) {   //computer will attack if its current hp is less than the max hp by 5
            return 1;
        }

        if (compHp >= oppHp) {   //computer will attack if its current hp is greater or equal to players hp
            return 1;
        }

        int hpDifference = compHp - oppHp;   //figuring out the hp difference is
        if (hpDifference < 0) {
            hpDifference = -hpDifference;   //keep the positive value of the difference
        }

        if (hpDifference <= 5) {    //if the difference is within 5
            Random rand = new Random();
            double probability = rand.nextDouble();  //generate a probability from 0.0 to 1.0
            if (probability < 0.6) {
                return 1;   //computer will attack if probability is less than 0.6
            } else {
                return 2;   //computer will heal if probability is more than 0.6
            }
        }
        return 2;   //computer will heal
	}

	//toString() method to return a nicely formatted 
	@Override
    public String toString() {
        return this.name + ", HP=" + this.currHp + ", Attack=" + this.attack + ", Defense=" + this.defense;
    }
}