import java.util.*;

public class AdventureGame {
    static Scanner scanner = new Scanner(System.in);
    static String currentRoom = "Entrance";
    static List<String> inventory = new ArrayList<>();
    static int playerHealth = 100;
    static boolean gameWon = false;

    public static void main(String[] args) {
        while (playerHealth > 0 && !gameWon) {
            System.out.println("\nYou are in the " + currentRoom + ".");
            displayRoomDescription();
            System.out.print("> ");
            String command = scanner.nextLine().trim().toLowerCase();
            handleCommand(command);
        }
    }

    static void displayRoomDescription() {
        switch (currentRoom) {
            case "Entrance":
                System.out.println("A dimly lit entrance with paths leading north and east.");
                System.out.println("Exits: north, east");
                break;
            case "Forest":
                System.out.println("A dense forest with towering trees. A key is glinting on the ground.");
                System.out.println("Exits: south");
                break;
            case "Dungeon":
                System.out.println("A dark and damp dungeon. A goblin blocks the path ahead.");
                System.out.println("Exits: west");
                break;
            case "Treasure Room":
                System.out.println("The Treasure Room! A chest of gold lies before you.");
                System.out.println("Exits: none");
                break;
        }
    }

    static void handleCommand(String command) {
        switch (command) {
            case "go north":
                if (currentRoom.equals("Entrance")) {
                    currentRoom = "Forest";
                } else {
                    System.out.println("You can't go that way.");
                }
                break;
            case "go south":
                if (currentRoom.equals("Forest")) {
                    currentRoom = "Entrance";
                } else {
                    System.out.println("You can't go that way.");
                }
                break;
            case "go east":
                if (currentRoom.equals("Entrance")) {
                    currentRoom = "Dungeon";
                } else {
                    System.out.println("You can't go that way.");
                }
                break;
            case "go west":
                if (currentRoom.equals("Dungeon")) {
                    currentRoom = "Entrance";
                } else {
                    System.out.println("You can't go that way.");
                }
                break;
            case "check inventory":
                System.out.println("Inventory: " + (inventory.isEmpty() ? "Empty" : String.join(", ", inventory)));
                break;
            case "take key":
                if (currentRoom.equals("Forest") && !inventory.contains("Key")) {
                    inventory.add("Key");
                    System.out.println("You picked up the key.");
                } else {
                    System.out.println("There is nothing to take here.");
                }
                break;
            case "talk":
                if (currentRoom.equals("Dungeon")) {
                    System.out.println("The goblin growls at you, preparing to attack.");
                } else {
                    System.out.println("There is no one to talk to.");
                }
                break;
            case "attack":
                if (currentRoom.equals("Dungeon")) {
                    combat("Goblin", 50);
                } else {
                    System.out.println("There is nothing to attack.");
                }
                break;
            case "run":
                if (currentRoom.equals("Dungeon")) {
                    System.out.println("You flee back to the Entrance.");
                    currentRoom = "Entrance";
                } else {
                    System.out.println("You can't run from here.");
                }
                break;
            case "open chest":
                if (currentRoom.equals("Treasure Room") && inventory.contains("Key")) {
                    System.out.println("You open the chest and claim the treasure. You win!");
                    gameWon = true;
                } else if (currentRoom.equals("Treasure Room")) {
                    System.out.println("The chest is locked. You need a key.");
                } else {
                    System.out.println("There is no chest here.");
                }
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    static void combat(String enemy, int enemyHealth) {
        while (enemyHealth > 0 && playerHealth > 0) {
            System.out.println("Your health: " + playerHealth + " | " + enemy + "'s health: " + enemyHealth);
            System.out.print("Attack or Run? > ");
            String action = scanner.nextLine().trim().toLowerCase();
            if (action.equals("attack")) {
                int damage = (int) (Math.random() * 20) + 10;
                enemyHealth -= damage;
                System.out.println("You hit the " + enemy + " for " + damage + " damage.");
                if (enemyHealth > 0) {
                    int enemyDamage = (int) (Math.random() * 15) + 5;
                    playerHealth -= enemyDamage;
                    System.out.println("The " + enemy + " hits you for " + enemyDamage + " damage.");
                }
            } else if (action.equals("run")) {
                System.out.println("You flee back to the Entrance.");
                currentRoom = "Entrance";
                return;
            } else {
                System.out.println("Invalid action.");
            }
        }
        if (playerHealth <= 0) {
            System.out.println("You have been defeated. Game Over.");
        } else {
            System.out.println("You defeated the " + enemy + "!");
        }
    }
}
