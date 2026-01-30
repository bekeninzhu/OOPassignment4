package model;
public class Pet {
    private int id;
    private String name;
    private String type;

    // Конструктор
    public Pet(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    // ID, атын және түрін алу/орнату әдістері
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pet{id=" + id + ", name='" + name + "', type='" + type + "'}";
    }
}
