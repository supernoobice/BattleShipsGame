package com.bootcamp;

public class OceanMap {
    GameObject map[][] = new GameObject[10][10];

    public OceanMap() {
        // Populate OceanMap with GameObjects

        for (int x = 0; x < map.length; x++ ) {
            for (int y = 0; y < map[x].length; y++) {
                this.map[x][y] = new GameObject(x, y, "0", " ");

            }
        }
    }

    public void printMap() {


        for (int x = 0; x < map.length; x++ ) {
            // top HUD
            if(x == 0 || x == map[x].length) {
                System.out.println("  0123456789  ");
            }

            for (int y = 0; y < map[x].length; y++) {
                // Left HUD
                if(y == 0) {
                    System.out.print(x + "|");
                }
                System.out.print(map[x][y].getDisplay());
                // Right HUD
                if(y + 1 == map.length) {
                    System.out.print("|" + x);
                }
            }
            System.out.println();

            // Bottom HUD
            if(x + 1 == map[x].length) {
                System.out.println("  0123456789  ");
            }
        }
    }

    // place to map
    public void place(int x, int y, GameObject ship) {
        map[x][y] = ship;
    }

    // check coordinates
    public GameObject checkCoordinates(int x, int y) {
        return map[x][y];
    }
}
