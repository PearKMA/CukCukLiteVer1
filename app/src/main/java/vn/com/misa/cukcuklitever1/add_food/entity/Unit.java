package vn.com.misa.cukcuklitever1.add_food.entity;

public class Unit {
    private int id;
    private String unit;

    public Unit() {
    }

    public Unit(int id, String unit) {
        this.id = id;
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
