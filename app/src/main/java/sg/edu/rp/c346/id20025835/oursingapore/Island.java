package sg.edu.rp.c346.id20025835.oursingapore;

import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String name;
    private String description;
    private int area;
    private int stars;

    public Island(String name, String description, int area, int stars) {
        this.name = name;
        this.description = description;
        this.area = area;
        this.stars = stars;
    }

    public Island(int id, String name, String description, int area, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public Island setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Island setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Island setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getArea() {
        return area;
    }

    public Island setArea(int yearReleased) {
        this.area = yearReleased;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Island setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @Override
    public String toString() {
        String starsString = "";
        /*if (stars == 5){
            starsString = "*****";
        } else if (stars == 4){
            starsString = "****";
        } else if (stars == 3) {
        starsString = "***";
        } else if (stars == 2) {
        starsString = "**";
        } else {
        starsString = "*";
        }
        */

        //or
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return name + "\n" + description + " - " + area + "\n" + starsString;

    }
}
