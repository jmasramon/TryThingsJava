package SessionStorageSimulator.es;

import SessionStorageSimulator.shared.VisitId;

import java.util.ArrayList;

public class ES {
    int numSessions = 0;
    ArrayList<VisitId> visitIds = new ArrayList<>();
    ArrayList<ESSession> sessions = new ArrayList<>();

    public ESSession getSession(VisitId visitId){
        return sessions.get(visitId.getId() - 1); // Zero indexed
    }

    public void addSession(ESSession newSession) {
        numSessions++;
        VisitId visitId = new VisitId(numSessions);
        visitIds.add(visitId);
        sessions.add(new ESSession(visitId));
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

    public ArrayList<VisitId> getVisitIds() {
        return visitIds;
    }
}
