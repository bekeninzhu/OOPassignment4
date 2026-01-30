package model;
abstract class Person {
    private final int id;
    private final String name;

    // Конструктор
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // ID алу әдісі
    public int getId() {
        return id;
    }

    // Аты алу әдісі
    public String getName() {
        return name;
    }

    // Класс туралы ақпаратты шығару әдісі
    @Override
    public String toString() {
        return "Person{id=" + id + ", name='" + name + "'}";
    }
}

