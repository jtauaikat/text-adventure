
/**
 * Write a description of class MainLoop here.
 *
 * @author Joshua Toumu'a
 * @version 16/02/2022
 */

import java.util.Scanner;//keyboard import
import java.util.Random;//random import
public class MainLoop
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class MainLoop
     */

    public static void main (String[] args){
        // initialise instance variables
        Scanner keyboard = new Scanner(System.in); // keyboard input setup
        Random rand = new Random(); //random setup

        String[] flavourText = {"You are standing at the mouth of the cave, ready to explore. enter 'down' to descend.", 
            "you are standing in an empty cavern, lit dimly by the hole you fell through. There is an opening to the south and the east."
            , "you enter a mineral deposit, filled with glimmering purple crystals. There is an entrance to the south, north and a staircase up to what looks like a mineshaft. The stairs seem busted, but if you had a rope. you could reach it.", 
              "you enter a dark room, lined with thick cobwebs. The spider sits in the centre of the room. There is an entrance to the west, east and north.", 
                "You climb the rope into the mineshaft. There is a long corridor with some chests lining the walls and an entrance to the north and a rope down.", 
                "you are in what was an old exit for the local miners. A landslide is blocking the exit, but could be easily removed with some sort of pickaxe. There are two entrances, one path up and one path east.", 
                "you are on a rocky shore beside a deep pool in the centre of the room. You see something glimmering at the bottom of the pool. There is an entrance to the west or you can swim down to the bottom of the pool.", 
                "You swim to the bottom of the pool, to find a sort of handle sticking out of the bottom of the pool. You can search, or you can head back up.", 
                "The spider slashed you across the chest, landing the killing blow. You have died.", 
                "you are standing at the bottom of an old, dried out well, now crumbled and empty. There is no way you can reach the well entrance, but there seems to be something sitting in the middle of the room. ", 
                "clawing the rubble away, you clamber out of the tunnel exit, finally free. You win!"}; //all text that is shown relating to rooms

        String[] inventory = {"","",""}; //inventory array, empty when no items

        int[] goNorth = {-1,-1, 1, 2, 5,-1, 5,-1,-1,-1,-1,}; // all possibble paths for this direction. if direction = -1, you cannot go that direction.
        int[] goEast =  {-1, 6,-1,-1,-1,-1,-1,-1,-1, 3,-1,}; // all possibble paths for this direction. if direction = -1, you cannot go that direction.
        int[] goSouth = {-1, 2, 3,-1,-1, 6,-1,-1,-1,-1,-1,}; // all possibble paths for this direction. if direction = -1, you cannot go that direction.
        int[] goWest =  {-1,-1,-1, 9,-1,-1, 1,-1,-1,-1,-1,}; // all possibble paths for this direction. if direction = -1, you cannot go that direction.
        int[] goUp =    {-1,-1,-2,-1,-1,-2,-1, 6,-1,-1,-1,}; // all possibble paths for this direction. if direction = -1, you cannot go that direction.
        int[] goDown =  { 1,-1,-1,-1, 2,-1, 7,-1,-1,-1,-1,}; // all possibble paths for this direction. if direction = -1, you cannot go that direction.

        int spiderHealth = 80; //initial health of spider
        int playerHealth = 100; //player health during combat
        int spiderDamage; //defines the damage dealt by the spider
        int playerDamage = 0; //defines the damage of the player
        int playerDamageValue = 6; //damage modifier. the "dice type". currently can do d6 of damage.

        boolean running = true; //the main movement game loop
        boolean encounter = false; //the main combat loop
        boolean mainGame = true; //whole game loop
        boolean spiderDeath = false; //allows player to navigate spider room is spider is dead,
        
        boolean ropeToggle = false; // adds rope to inventory and removes it when used
        boolean pickToggle = false; // adds pick to inventory and removes it when used

        int roomNumber = 0; // tells code which room player is in
        int destination = 0; // temp variable that holds location. unused if value is -1

        System.out.println('\u000c'+flavourText[roomNumber]); // prints initial text

        GAME:
        while(mainGame){
            while(running){
                String keyInput = keyboard.nextLine().toLowerCase(); //takes key input and turns input o all lowercase
                System.out.print('\u000c'); //clears canvas
                switch(keyInput){
                    case "north":
                        
                        destination = goNorth[roomNumber]; // sets destination to the value in the nth position of that direction's array
                        if(destination<0){
                            System.out.println("you cannot go that direction"); //if invalid room, prints txt
                        }else{
                            roomNumber = destination; //if valid room, sets room to destination
                        }
                        System.out.println(flavourText[roomNumber]); //prints flavour text
                        break;
                    
                    case "south":
                        destination = goSouth[roomNumber]; //same as previous command
                        if(destination<0){
                            System.out.println("you cannot go that direction");
                        }else{
                            roomNumber = destination;
                        }
                        System.out.println(flavourText[roomNumber]);
                        break;
                    
                    case "east":
                        destination = goEast[roomNumber];//same as previous command
                        if(destination<0){
                            System.out.println("you cannot go that direction");
                        }else{
                            roomNumber = destination;
                        }
                        System.out.println(flavourText[roomNumber]);
                        break;
                        
                    case "west":
                        destination = goWest[roomNumber];//same as previous command
                        if(destination<0){
                            System.out.println("you cannot go that direction");
                        }else{
                            roomNumber = destination;
                        }
                        System.out.println(flavourText[roomNumber]);
                        break;
                        
                    case "up":
                        destination = goUp[roomNumber];//same as previous command
                        if(destination<0){
                            System.out.println("you cannot go that direction");
                        }else{
                            roomNumber = destination;
                        }
                        System.out.println(flavourText[roomNumber]);
                        break;
                        
                    case "down":
                        destination = goDown[roomNumber];//same as previous command
                        if(roomNumber == 0 && destination == 1){
                            System.out.println("Your foot slips, and you descend into the cavern. Your gear gets snagged on a rock by the entrance and is torn off of you, losing it all.");
                            System.out.println("type 'commands' to see all available commands.");
                        }
                        if(destination<0){
                            System.out.println("you cannot go that direction");
                        }else{
                            roomNumber = destination;
                        }
                        System.out.println(flavourText[roomNumber]);
                        break;
                        
                    case "search":
                        switch(roomNumber){
                            case 7:
                                if(inventory[0]=="sword"){
                                    System.out.println("you have already found the sword.");//if already holding sword, prints text
                                    break; //ends loop
                                }
                                inventory[0] = "sword";//sets position 0 in array to sword if in room 7
                                playerDamageValue = 20; //increases damage from d6 to d20
                                System.out.println("you have found a sword!"); //prints flavour text
                                break;
                                
                            case 9:
                                if(inventory[1]=="rope"){
                                    System.out.println("you have already found the rope.");//if already holding rope, prints text
                                    break; //ends loop
                                }
                                inventory[1] = "rope";//sets position 1 in array to rope if in room 9
                                System.out.println("you have found a rope! it looks like you can use this to climb up to somewhere with this!"); //prints flavour text
                                ropeToggle = true; //tells boolean rope is in inventory
                                break;
                                
                            case 4:
                                if(inventory[2]=="pick"){
                                    System.out.println("you have already found the pick.");//if already holding pick, prints text
                                    break; //ends loop
                                }
                                inventory[2] = "pick"; //sets position 2 in array to pick if in room 4
                                System.out.println("you have found a pickaxe! it seems sturdy enough to break thin walls!"); //prints flavour text
                                pickToggle = true;//tells boolean pick is in inventory
                                break;
                                
                            default:
                                System.out.println("you didn't find anything useful..."); //prints defualt text
                                break;
                        }
                        System.out.println(flavourText[roomNumber]); //prints room flavour text
                        break;
                        
                    case "inventory":
                        if(inventory[0]=="sword"||inventory[1]=="rope"||inventory[2]=="pick"){ //if you have any items in inventory
                            System.out.println("You have: "); 
                            if(inventory[0]=="sword"){
                                System.out.println("A sword, found at the bottom of a reservoir. There is minimal rusting.");//prints sword description if holding sword
                            }
                            if(inventory[1]=="rope" && ropeToggle){
                                System.out.println("A length of rope, attached to the old handle of a bucket for the old well. It seems that you could use this to climb somewhere.");//prints rope description if holding rope
                            }
                            if(inventory[2]=="pick"){
                                System.out.println("A sturdy pickaxe, left by the old miners. It looks strong enough to break down small obstructions.");//prints pick description if holding pick
                            }
                        }else{
                            System.out.println("You do not have anything in your inventory."); //if no items, prints line.
                        }
                        System.out.println(flavourText[roomNumber]); //prints room flavour text
                        break;
                        
                    case "use":
                        if(inventory[1] == "rope" && roomNumber == 2 && ropeToggle){//if rope is in inventory, in room 2 and unused:
                            goUp[2] = 4; //opens new path up to room 4
                            ropeToggle = false; //stops rope from being used
                            System.out.println("you used the rope"); //prints text
                        }else if(inventory[2] == "pick" && roomNumber == 5 && pickToggle){//if rope is in inventory, in room 2 and unused:
                            goUp[5] = 10; //opens new path up to room 10
                            pickToggle = false; //stops pick from being used
                            System.out.println("you used the pickaxe"); //prints text
                        }else{
                            System.out.println("You cannot use anything here."); //if unable to use anything, prints text
                        }
                     
                        System.out.println(flavourText[roomNumber]); //prints room flavour text
                        break;
                        
                    case "end game":
                        System.out.println("Game closed."); //prints text
                        return; //ends loop.
                        
                    case "commands":
                        System.out.println("You can go:"); //tells you if you what valid commands are available
                        if(goUp[roomNumber]>=0){
                            System.out.print(" Up,");
                        }
                        if(goDown[roomNumber]>=0){
                            System.out.print(" Down,");
                        }
                        if(goNorth[roomNumber]>=0){
                            System.out.print(" North,");
                        }
                        if(goEast[roomNumber]>=0){
                            System.out.print(" East,");
                        }
                        if(goSouth[roomNumber]>=0){
                            System.out.print(" South,");
                        }
                        if(goWest[roomNumber]>=0){
                            System.out.print(" West,");
                        }
                        if(roomNumber == 2 || roomNumber == 5){
                            System.out.print(" Use,");
                        }
                        if(roomNumber == 7 && inventory[0]==""|| roomNumber == 9 && inventory[1]=="" || roomNumber == 4 && inventory[2]==""){
                            System.out.print(" Search,");
                        }
                        System.out.print(" Inventory,");
                        System.out.print(" End Game.");
                        System.out.println(flavourText[roomNumber]);
                        break;
                        
                    default:
                        System.out.println("I don't understand this command.");
                        System.out.println(flavourText[roomNumber]);
                }
                if(roomNumber==3 && !spiderDeath){
                    //System.out.println("           ____                      ,");
                    //System.out.println("          /---.'.__             ____//");
                    //System.out.println("               '--.\\           /.---'");
                    //System.out.println("          _______  \\         //");
                    //System.out.println("        /.------.\\  \\|      .'/  ______");
                    //System.out.println("       //  ___  \\ \\ ||/|\\  //  _/_----.\\__");
                    //System.out.println("      |/  /.-.\\  \\ \\:|< >|// _/.'..\\   '--'");
                    //System.out.println("         //   \'. | \'.|.'/ /_/ /  \\");
                    //System.out.println("       //       '-._| :H: |\'-.__     \\");
                    //System.out.println("      //           (/'==='\\)\'-._\\     ||");
                    //System.out.println("      ||                        \\    \\|");
                    //System.out.println("      ||                         \\    '");
                    //System.out.println("      |/                          \\");
                    //System.out.println("                                   ||");
                    //System.out.println("                                   ||");
                    //System.out.println("                                   \\");
                    //System.out.println("                                    '");
                    System.out.println("Enemy Health = "+ spiderHealth);
                    System.out.println("Player Health = "+ playerHealth);
                    encounter = true;
                    running = false;
                    //System.out.println("encounter");
                }
                if(roomNumber==10 || roomNumber==8){
                    mainGame = false;
                    running = false;
                }
                
            //if(running){
                //System.out.println("roomNumber = "+roomNumber);
                //System.out.println("East = "+goEast[roomNumber]);
                //System.out.println("West = "+goWest[roomNumber]);
                //System.out.println("North = "+goNorth[roomNumber]);
                //System.out.println("South = "+goSouth[roomNumber]);
                //System.out.println("Up = "+goUp[roomNumber]);
                //System.out.println("Down = "+goDown[roomNumber]);
            //}

        }
        while(encounter){
            String keyInput = keyboard.nextLine();
            switch(keyInput){
                case "attack":
                    playerDamage = rand.nextInt(20);
                    playerDamage++;
                    if(playerDamage > 7){
                        playerDamage = rand.nextInt(playerDamageValue);
                        playerDamage ++;
                        spiderHealth -= playerDamage;
                        System.out.println("you hit for "+playerDamage+" damage.");
                        playerDamage = 0;
                    }else{
                        System.out.println("you missed!");
                    }
                    spiderDamage = rand.nextInt(20);
                    if(spiderDamage > 7){
                        spiderDamage = rand.nextInt(playerDamageValue);
                        playerDamage += 3;
                        spiderHealth -= playerDamage;
                        System.out.println("spider hit for "+playerDamage+" damage.");
                        playerDamage = 0;
                    }else{
                        System.out.println("spider missed!");
                    }
                    
                    spiderDamage ++;
                    playerHealth -= spiderDamage;
                    break;
                    
                case "block":
                    playerHealth += 3;
                    spiderHealth += 2;
                    break;
                    
                case "end game":
                    System.out.println("Game closed.");
                    return;
                    
                default:
                    System.out.println("You can't do that.");
                    
                
            }
            if(playerHealth <= 0){
                roomNumber = 8;
                encounter = false;
                running = true;
            }
            if(spiderHealth <= 0){
                System.out.println("it died");
                running = true;
                encounter = false;
                spiderDeath = true;
                System.out.println(flavourText[roomNumber]);

            }
            if(encounter){
                System.out.println("Enemy Health = "+ spiderHealth);
                System.out.println("Player Health = "+ playerHealth);
            }  
        }
    }
}
}

