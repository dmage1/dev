package util;

public class Database {

    int uniqueId = 42;

    public boolean isAvailable() {
        // TODO implement the access to the database
        return false;
    }
    public int getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public void updateName(int id, String name) {

    }
}
