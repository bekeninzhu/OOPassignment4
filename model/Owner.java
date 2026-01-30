package model;
public class Owner extends model.Person {
    private String phone;
    private int petCount;

    // Конструктор
    public Owner(int id, String name, String phone, int petCount) {
        super(id, name);  // Parent кластың конструкторын шақыру
        this.phone = phone;
        this.petCount = petCount;
    }

    // Телефонды алу және орнату әдістері
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be empty");
        }
        this.phone = phone;
    }

    // Үй жануарларының санын алу және орнату әдістері
    public int getPetCount() {
        return petCount;
    }

    public void setPetCount(int petCount) {
        this.petCount = petCount;
    }
    // Жұмыс жасау әдісін қайта анықтау
    public void work() {
        System.out.println(getName() + " is taking care of " + petCount + " pets.");
    }

    @Override
    public String toString() {
        return "Owner{" + super.toString() + ", phone='" + phone + "', pets=" + petCount + "}";
    }
}

