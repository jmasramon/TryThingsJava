package SessionStorageSimulator.es;

import SessionStorageSimulator.shared.VisitId;

import java.util.ArrayList;
import java.util.HashSet;

// TODO: Should implement, at least, filters
public class ES {
    int numSessions;
    HashSet<VisitId> visitIds;
    ArrayList<ESSession> sessions;

    public ES() {
        numSessions = 0;
        visitIds = new HashSet<>();
        sessions = new ArrayList<>();
    }

    public ES(int numSessions, HashSet<VisitId> visitIds, ArrayList<ESSession> sessions) {
        this.numSessions = numSessions;
        this.visitIds = visitIds;
        this.sessions = sessions;
    }

    public ESSession getSession(VisitId visitId){
        return sessions.get(visitId.getId() - 1); // Zero indexed
    }

    public void addSession(ESSession newSession) {
        numSessions++; // One indexed: visitId numbers start at 1
        if (newSession == null) {
            VisitId visitId = VisitId.getVisitId(numSessions);
            visitIds.add(visitId);
            // TODO: should randomize a more realistic session
            sessions.add(new ESSession(visitId));
        } else {
            if (visitIds.add(newSession.getVisitId())) {
                sessions.add(newSession);
            }
            // TODO: visitId should be unique !!! Should we throw exception if already there?
        }
    }

    public int getNumSessions() {
        return numSessions;
    }

    public void addSessions(int amount) {
        while(amount > 0) {
            addSession(null);
            amount--;
        }
    }

    public HashSet<VisitId> getVisitIds() {
        return visitIds;
    }
}
