public class Zadanie {
    private int id;
    private String tresc;
    private boolean status;

    public Zadanie(int id, String tresc, boolean status) {
        this.id = id;
        this.tresc = tresc;
        this.status = status;
    }

    public int getId() { return id; }
    public String getTresc() { return tresc; }
    public boolean isStatus() { return status; }

    @Override
    public String toString() {
        String prefix = status ? "[ZROBIONE] " : "[DO ZROBIENIA] ";
        return prefix + tresc;
    }
}