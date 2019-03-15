package SessionStorageSimulator.shared;

public class VisitId {
    final private int id;

    public VisitId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return this.id == ((VisitId)o).getId();
    }

    @Override
    public String toString() {
        return "visit " + id;
    }
}
