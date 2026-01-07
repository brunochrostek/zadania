// 1. Klasa Modelu
class Task {
    int id;
    String title;
    String description;
    boolean isDone;

    public Task(int id, String title, String description, boolean isDone) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
    }
}