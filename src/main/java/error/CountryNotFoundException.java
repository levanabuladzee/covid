package error;

public class CountryNotFoundException extends RuntimeException {
    private int id;

    public CountryNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
