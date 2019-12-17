package SessionStorageSimulator;

import SessionStorageSimulator.replay.ReplayData;
import SessionStorageSimulator.shared.VisitId;

public class STSimulator {
    /*
    * We want to simulate a cluster with a number of visits
    * Initially visits will have sequential numbers as visitId
    * */
    Simulation simulateVisits(Simulation initialState, int amount) {
        Simulation endState = initialState == null ? new Simulation() : initialState;

        endState.addVisits(amount);

        return endState;
    }

    /*
    * We want to simulate the replay of an specific session identified by visitID
    * It should return all the data used for the simulation
    * */
    ReplayData simulateReplay(Simulation state, VisitId visitID){
        return null;
    }

    static void main() {
        STSimulator simulator = new STSimulator();

        Simulation simulation = simulator.simulateVisits(new Simulation(), 3);
        simulator.simulateReplay(simulation, VisitId.getVisitId(2));
    }

}
