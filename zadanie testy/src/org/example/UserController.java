package org.example;

public class UserController {
    private final MyRepository repository;

    // Konstruktor przyjmuje bazę jako argument
    public UserController(MyRepository repository) {
        this.repository = repository;
    }

    public void registerUser(String username) {
        try {
            repository.save(username);
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}
