package SessionStorageSimulator;

import SessionStorageSimulator.es.ES;
import SessionStorageSimulator.es.ESSession;
import SessionStorageSimulator.shared.VisitId;
import SessionStorageSimulator.storage.SessionStorage;

import java.util.HashSet;

public class Simulation {
    ES elasticSearch;
    SessionStorage sessionStorage;

    public Simulation() {
        elasticSearch = new ES();
        sessionStorage = new SessionStorage();
    }

    public int getNumVisits() {
        return elasticSearch.getNumSessions();
    }

    public void addVisits(int amount) {
        elasticSearch.addSessions(amount);
        sessionStorage.addSessions(amount, elasticSearch.getVisitIds());
    }

    public HashSet<VisitId> getVisitIds() {
        return  elasticSearch.getVisitIds();
    }

    public ESSession getVisit(VisitId visitId) {
        return elasticSearch.getSession(visitId);
    }
}
