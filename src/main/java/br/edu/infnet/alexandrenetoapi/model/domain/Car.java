package br.edu.infnet.alexandrenetoapi.model.domain;

public class Car {
    private Integer id;
    private String name;
    private String color;
    private String plate;
    private String size;

    public Integer getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getPlate() {
        return plate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return ""
            + this.id.toString() + ","
            + "name=" + this.name + ","
            + "color=" + this.color + ","
            + "plate=" + this.plate + ","
            + "size=" + this.size
        ;
    }
}
