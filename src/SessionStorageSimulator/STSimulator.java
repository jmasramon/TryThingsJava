package SessionStorageSimulator;

import SessionStorageSimulator.replay.ReplayData;
import SessionStorageSimulator.shared.VisitId;

public class STSimulator {
    Simulation simulateVisits(Simulation initialState, int amount) {
        Simulation endState = initialState == null ? new Simulation() : initialState;

        endState.addVisits(amount);

        return endState;
    }

    ReplayData simulateReplay(Simulation state, VisitId visitID){
        return null;
    }

    static void main() {
        STSimulator simulator = new STSimulator();

        Simulation simulation = simulator.simulateVisits(new Simulation(), 3);
        simulator.simulateReplay(simulation, new VisitId(2));
    }

}
