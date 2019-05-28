package vn.com.misa.cukcuklitever1.menu_cook.entity;

public class Food {
    private int id;
    private String name;
    private int price;
    private String unit;
    private String color;
    private String icon;
    private boolean status;

    public Food() {
    }

    public Food(int id, String name, int price, String unit, String color, String icon, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.color = color;
        this.icon = icon;
        this.status = status;
    }
    public Food( String name, int price, String unit, String color, String icon, boolean status) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.color = color;
        this.icon = icon;
        this.status = status;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
