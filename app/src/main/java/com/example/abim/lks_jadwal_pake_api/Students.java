package com.example.abim.lks_jadwal_pake_api;

public class Students {
    private int id;
    private String name, address, date, hp, classname, username;

    public Students(int id, String name, String address, String date, String hp, String classname, String username) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.date = date;
        this.hp = hp;
        this.classname = classname;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public String getHp() {
        return hp;
    }

    public String getClassname() {
        return classname;
    }

    public String getUsername() {
        return username;
    }
}
