package SessionStorageSimulator.storage;

import SessionStorageSimulator.shared.Time;
import SessionStorageSimulator.shared.VisitId;

import java.util.HashSet;

public class SessionStorage {
    // A session here is a folder with dat
    STSession[] sessions; //TODO: really this data structure?

    STSession[] getSessions(Time startTime, Time endTime, VisitId visitId) {
        return null;
    }

    public void addSessions(int amount, HashSet<VisitId> visitIds) {

//        while(amount > 0) {
        for (VisitId visitId: visitIds) {
            addSession(visitId);
            amount--;
        }
    }

    private void addSession(STSession newSession) {
        // TODO: fill it up
        if (newSession == null) {
         } else {
        }

    }
    private void addSession(VisitId visitId) {
        if (visitId == null) {
            // TODO: What here?
        } else {
            // TODO: fill it up
            // Assign a random duration to the session
            // Depending on the duration, put data for it in different "folders"
        }

    }
}
