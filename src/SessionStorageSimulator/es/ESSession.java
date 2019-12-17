package SessionStorageSimulator.es;

import SessionStorageSimulator.shared.VisitId;

public class ESSession {
    final private VisitId visitId;
    // TODO: make the session realistic. Many more properties

    public ESSession(VisitId id) {
        visitId = id;
        // TODO:  should randomize a more realistic session?
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
