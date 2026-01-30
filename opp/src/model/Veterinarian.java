package model;
public class Veterinarian extends model.Person {
    private final String specialty;
    private final int experience;

    // Конструктор
    public Veterinarian(int id, String name, String specialty, int experience) {
        super(id, name);  // Parent кластың конструкторын шақыру
        this.specialty = specialty;
        this.experience = experience;
    }

    // Мамандық және тәжірибе алу/орнату әдістері
    public String getSpecialty() {
        return specialty;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "Vet{" + super.toString() + ", specialty='" + specialty + "', exp=" + experience + "}";
    }
}
