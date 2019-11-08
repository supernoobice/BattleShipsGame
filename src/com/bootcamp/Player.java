package com.bootcamp;

public class Player {
    public int ships;
    public String playerID;

    public Player(int ships, String playerID) {
        this.ships = ships;
        this.playerID = playerID;
    }

    public void fire(int x, int y, Player enemy, OceanMap ocean) {
        GameObject obj = ocean.map[x][y];

        String id = obj.getId();

        String player_name = null;
        if(this.playerID.equals("1")) {
            player_name = "You";
        }
        else if(this.playerID.equals("2")) {
            player_name = "Computer";
        }

        if(id.equals(this.playerID)) {
            System.out.println("Oh no! " + player_name + " hit your own ship.");
            this.ships = this.ships - 1;
            ocean.map[x][y] = new GameObject(x, y, "0", "@");
        }
        else if (id.equals("0") &&
                (obj.getDisplay().equals("-") || obj.getDisplay().equals(" ")) ) {
            System.out.println(player_name + " missed.");
            ocean.map[x][y].setDisplay("-");
        }
        else if(id.equals("0") && !obj.getDisplay().equals("-")) {
            System.out.println(player_name + " missed.");
        }
        else if(id.equals(enemy.playerID)){
            String display = "!";
            if(enemy.playerID.equals("1")) {
                display = "x";
            }
            System.out.println("Boom! "+ player_name +" sunk the enemy ship!");
            ocean.map[x][y] = new GameObject(x, y, "0", display);
            enemy.ships = enemy.ships - 1;
        }
    }

    public GameObject checkCoordinate(int x, int y, OceanMap map) {
        return map.map[x][y];
    }
}
