package util;

public class Service {

    private final Database database;

    public Service(Database database) {
        this.database = database;
    }

    public boolean query(String query) {
        return database.isAvailable();
    }

    public void updateName(int id, String name) {
        database.updateName(id, name);
    }

    @Override
    public String toString() {
        return "Using database with id: " + database.getUniqueId();
    }
}
