package model;

public interface InputValidator {
    boolean isValidPetId(int petId);
    boolean isValidOwnerId(int ownerId);
    boolean isValidPhone(String phone);
}
