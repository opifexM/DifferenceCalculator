package hexlet.code;

public enum Status {
    UNCHANGED(1),
    DELETED(2),
    ADDED(3);

    private final int level;
    Status(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
