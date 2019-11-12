package com.bootcamp;

import com.sun.xml.internal.ws.api.client.WSPortInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static OceanMap map = new OceanMap();
    public static Player player = new Player(5, "1");
    public static Player computer = new Player(5, "2");
    public static boolean gameOver = false;

    public static void main(String[] args) {
	// write your code here
        System.out.println("*** Welcome to Battle Ships Game ***");
        System.out.println("Right now, the sea is empty.");

        // Initialize
        init();

        // Battle
        while ( !gameOver ) {

            System.out.println("- BATTLE -");
            System.out.print("Player: " + player.ships + " ");
            System.out.println("Computer: " + computer.ships);

            playerTurn();
            if(isGameOver()) { continue; }

            System.out.println("COMPUTER'S TURN");
            computerTurn();
            if(isGameOver()) { continue; }

            map.printMap();
        }
    }

    private static void init() {

        // Prepare the map
        map = new OceanMap();
        player.ships = 5;
        computer.ships = 5;

        // show map
        map.printMap();

        // Player placing ships
        placeShips();
        // show map with player ship
        map.printMap();

        // Computer places ships
        computerPlaceShips();
        map.printMap();

    }

    private static void reset() {
        init();

        player = new Player(5, "1");;
        computer = new Player(5, "2");;
    }

    private static boolean isGameOver() {
        // Check if the game condition is over
        if(player.ships <= 0 || computer.ships <= 0) {
            boolean again = false;

            System.out.print("Player: " + player.ships + " ");
            System.out.println("Computer: " + computer.ships);

            map.printMap();

            if(player.ships == 0) {
                System.out.println("You lose! Play again?");
            }
            if(computer.ships == 0) {
                System.out.println("You win! Play again?");
            }

            Scanner againscan = new Scanner(System.in);
            String decide = againscan.next();
            if(decide.equals("y")) {
                gameOver = false;
                reset();
            }

            else if(decide.equals("n")) {
                gameOver = true;
            }

        }
        return gameOver;
    }

    private static void computerTurn() {
        String seen[] = {"-", "@", "x", "!"};
        List<String> list = Arrays.asList(seen);

        int xcoor = new Random().nextInt(9 + 1);
        int ycoor = new Random().nextInt(9 + 1);


        String item = computer.checkCoordinate(xcoor, ycoor, map).getDisplay();

        if(list.contains(item)) {
            computerTurn();
        }
        else {
            System.out.println("Computer fired at " + xcoor + ", " + ycoor);
            computer.fire(xcoor, ycoor, player, map);
        }
    }

    private static void playerTurn() {
        System.out.println("YOUR TURN");
        System.out.print("Enter X coordinate: ");
        Scanner xplayer = new Scanner(System.in);
        int xcoor = xplayer.nextInt();

        System.out.print("Enter Y coordinate: ");
        Scanner yplayer = new Scanner(System.in);
        int ycoor = yplayer.nextInt();

        // Check if the coordinates are out of bounds.
        // must be within range
        if(xcoor <= 9 && xcoor >= 0) {
            if (ycoor <= 9 && ycoor >= 0) {
                // fire at coordinates of the map
                player.fire(xcoor, ycoor, computer, map);
            }
            else {
                System.out.println("You are out of range.");
                playerTurn();
            }
        }
        else {
            System.out.println("You are out of range.");
            playerTurn();
        }
    }

    private static void computerPlaceShips() {
        System.out.println("Computer is placing its ships.");
        int shipsPlaced = 0;

        while (shipsPlaced != computer.ships) {
            int xcoor = new Random().nextInt(9 + 1);
            int ycoor = new Random().nextInt(9 + 1);

            if( !map.map[xcoor][ycoor].getId().equals("0") ) {
                System.out.println("Computer placed a ship that is already there. Repeating.");
            }
            else {
                GameObject ship = new GameObject(xcoor, ycoor, "2", " ", "COMP");
                map.place(xcoor, ycoor, ship);
                shipsPlaced = shipsPlaced + 1;
                System.out.println(shipsPlaced + " ship deployed.");
            }
        }

    }

    public static void placeShips() {
        System.out.println("PLAYER 1 - Place your ships.");
        int shipsPlaced = player.ships;

        while(shipsPlaced != 0) {
            Scanner xplayerInput = new Scanner(System.in);
            System.out.print("Enter X coordinate of your ship: ");
            int xcoor = xplayerInput.nextInt();

            Scanner yplayerInput = new Scanner(System.in);
            System.out.print("Enter Y coordinate of your ship: ");
            int ycoor = yplayerInput.nextInt();

            // Must be within range
            if(xcoor <= 9 && xcoor >= 0) {
                if(ycoor <= 9 && ycoor >= 0) {
                    GameObject ship = new GameObject(xcoor, ycoor, "1", "1", "PLAYER");

                    // Check if the map is already taken
                    if (!map.map[xcoor][ycoor].getId().equals("0")) {
                        System.out.println("You can not place a ship there. Try again.");
                    } else {
                        // You can place a ship there because its empty
                        map.place(xcoor, ycoor, ship);
                        shipsPlaced = shipsPlaced - 1;
                    }
                    map.printMap();
                }
                else {
                    System.out.println("You are out of range.");
                }
            }
            else {
                System.out.println("You are out of range.");
            }
        }
    }
}
