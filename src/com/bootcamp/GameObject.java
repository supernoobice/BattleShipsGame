package com.bootcamp;

public class GameObject {
    // coordinates
    private int x;
    private int y;
    private String id; // 1, 2,
    private String display; // !, @, X, L
    private String owner; // P-player , C-computer

    // constructor setting of GameObject
    public GameObject(int x, int y, String id, String display) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.display = display;
    }

    // constructor setting of GameObject
    public GameObject(int x, int y, String id, String display, String owner) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.display = display;
        this.owner = owner;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
