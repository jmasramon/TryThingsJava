package SessionStorageSimulator

import SessionStorageSimulator.shared.VisitId

class STSimulatorTest extends GroovyTestCase {
    STSimulator simulator;

    void setUp() {
        super.setUp()
        simulator = new STSimulator();
    }

    void testSimulateVisits() {
        Simulation emptySimulation = new Simulation()
        assertEquals(0, emptySimulation.getNumVisits())

        Simulation simulation = simulator.simulateVisits(emptySimulation, 3)

        assertNotNull(simulation)
        assertEquals(emptySimulation, simulation)
        assertEquals(3, simulation.getNumVisits())

        Simulation oldSimulation = simulation
        simulation = simulator.simulateVisits(simulation, 2);

        assertEquals(oldSimulation, simulation)
        assertEquals(5, simulation.getNumVisits())

        assertEquals(VisitId.getVisitId(1), simulation.getVisit(VisitId.getVisitId(1)).getVisitId())
        assertEquals(VisitId.getVisitId(3), simulation.getVisit(VisitId.getVisitId(3)).getVisitId())
        assertEquals(VisitId.getVisitId(5), simulation.getVisit(VisitId.getVisitId(5)).getVisitId())
        assertEquals(VisitId.getVisitId(2), simulation.getVisit(VisitId.getVisitId(2)).getVisitId())
        assertEquals(VisitId.getVisitId(4), simulation.getVisit(VisitId.getVisitId(4)).getVisitId())

        // TODO: We should check that the visits created are correct (once we make them realistic)

        // Needs to create data in Session Storage too
    }

    void testSimulateReplay() {
        Simulation emptySimulation = new Simulation()
        Simulation simulation = simulator.simulateVisits(emptySimulation, 3)

        simulator.simulateReplay(simulation, VisitId.getVisitId(2))
    }
}
