package SessionStorageSimulator

import SessionStorageSimulator.shared.VisitId

class STSimulatorTest extends GroovyTestCase {
    STSimulator simulator;

    void setUp() {
        super.setUp()
        simulator = new STSimulator();
    }

    void testSimulateVisits() {
        Simulation simulation = simulator.simulateVisits(new Simulation(), 3)

        assertNotNull(simulation)
        assertEquals(3, simulation.getNumVisits())

        simulation = simulator.simulateVisits(simulation, 2);

        assertEquals(5, simulation.getNumVisits())

        assertEquals(new VisitId(1), simulation.getVisit(new VisitId(1)).getVisitId())
        assertEquals(new VisitId(3), simulation.getVisit(new VisitId(3)).getVisitId())
        assertEquals(new VisitId(5), simulation.getVisit(new VisitId(5)).getVisitId())

        assertEquals([new VisitId(1),new VisitId(2),new VisitId(3),new VisitId(4),new VisitId(5)], simulation.getVisitIds())

        assertEquals(null, simulation.getVisit(new VisitId(2)))
//        assertEquals(new Simulation(), simulation)
    }

    void testSimulateReplay() {
    }
}
