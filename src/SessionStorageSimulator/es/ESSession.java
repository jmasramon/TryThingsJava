package SessionStorageSimulator.es;

import SessionStorageSimulator.shared.VisitId;

public class ESSession {
    final private VisitId visitId;

    public ESSession(VisitId id) {
        visitId = id;
    }

    public VisitId getVisitId() {
        return visitId;
    }

    @Override
    public boolean equals(Object obj) {
        return visitId.equals(obj);
    }

    @Override
    public String toString() {
        return "ESSession " + visitId;
    }


}
