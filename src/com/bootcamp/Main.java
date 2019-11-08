package com.bootcamp;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static OceanMap map = new OceanMap();
    public static Player player = new Player(5, "1");
    public static Player computer = new Player(5, "2");

    public static void main(String[] args) {
	// write your code here
        System.out.println("*** Welcome to Battle Ships Game ***");
        System.out.println("Right now, the sea is empty.");

        // Initialize
        init();
        boolean gameOver = false;
        Scanner scanner = new Scanner(System.in);

        // Battle
        while ( !gameOver ) {
            //map.printMap();
            if(player.ships <= 0 || computer.ships <= 0) {
                boolean again = false;

                System.out.print("Player: " + player.ships + " ");
                System.out.println("Computer: " + computer.ships);

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
            System.out.println("- BATTLE -");
            System.out.print("Player: " + player.ships + " ");
            System.out.println("Computer: " + computer.ships);

            playerTurn();
            computerTurn();


            scanner.nextLine();

            map.printMap();
        }
    }

    private static void init() {
        map = new OceanMap();
        player.ships = 5;
        computer.ships = 5;

        map.printMap();

        // Player placing ships
        placeShips();
        map.printMap();

        // Computer places ships
        computerPlaceShips();
        map.printMap();

        // battle
    }

    private static void reset() {
        init();

        player = new Player(5, "1");;
        computer = new Player(5, "2");;
    }

    private static void computerTurn() {
        int xcoor = new Random().nextInt(9 + 1);
        int ycoor = new Random().nextInt(9 + 1);
        System.out.println("Computer fired at " + xcoor + ", " + ycoor);
        computer.fire(xcoor, ycoor, player, map);
    }

    private static void playerTurn() {
        System.out.println("YOUR TURN");
        System.out.print("Enter X coordinate: ");
        Scanner xplayer = new Scanner(System.in);
        int xcoor = xplayer.nextInt();

        System.out.print("Enter Y coordinate: ");
        Scanner yplayer = new Scanner(System.in);
        int ycoor = yplayer.nextInt();

        // fire at coordinates of the map
        player.fire(xcoor, ycoor, computer, map);
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

            GameObject ship = new GameObject(xcoor, ycoor, "1", "1", "PLAYER");

            if (!map.map[xcoor][ycoor].getId().equals("0")) {
                System.out.println("You can not place a ship there. Try again.");
            } else {
                map.place(xcoor, ycoor, ship);
                shipsPlaced = shipsPlaced - 1;
            }
            map.printMap();
        }
    }
}
