package SessionStorageSimulator.shared;

import java.util.HashMap;

// TODO: visits should be immutable
public class VisitId {
    static private HashMap<Integer, VisitId> visitIds = new HashMap<>();

    static public VisitId getVisitId(int id) {
        VisitId existing = visitIds.get(id);
        if (existing == null) {
            visitIds.put(id, new VisitId(id));
            existing = visitIds.get(id);
        }
        return existing;
    }

    final private int id;

    private VisitId(int id) {
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
