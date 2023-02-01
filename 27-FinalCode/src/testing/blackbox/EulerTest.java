package testing.blackbox;
import interfaces.given.*;
import interfaces.own.DataInterface;
import org.junit.jupiter.api.Test;
import titan.math.Vector3d;
import titan.math.equations.Equation1;
import titan.physics.State;
import titan.solvers.Euler;
import titan.utility.Planet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EulerTest {

    /**
     * Tests Euler's step method for the differential equation dy/dt = y with y(0) = 1
     * Tested interval is [0, 0.2] is step size 0.2
     * Assumes that x value of the position vector of an object in the state is y for this function.
     * Therefore, initial position of the test object is (1, 0, 0) and everything else of the object is not relevant.
     */
    @Test public void testStep(){
        ODEFunctionInterface function = new Equation1();

        Planet p = new Planet();
        p.setPosition(new Vector3d(1, 0, 0));
        p.setVelocity(new Vector3d());

        DataInterface[] objects = { p };
        ODESolverInterface solver = new Euler();

        State step = (State) solver.step(function, 0, new State(objects), 0.2);
        assertEquals(1.2, step.getObjects()[0].getPosition().getX(), 0.1);
    }

    /**
     * Tests Euler's solve(Func, State, tf, h) method for the differential equation dy/dt = y with y(0) = 1
     * Tested interval is [0, 0.8] where h is constant and equal to 0.2
     * Assumes that x value of the position vector of an object in the state is y for this function.
     * Therefore, initial position of the test object is (1, 0, 0) and everything else of the object is not relevant.
     */
    @Test public void testSolve(){
        ODEFunctionInterface function = new Equation1();

        Planet p = new Planet();
        p.setPosition(new Vector3d(1, 0, 0));
        p.setVelocity(new Vector3d());

        DataInterface[] objects = { p };
        ODESolverInterface solver = new Euler();

        State[] results = (State[]) solver.solve(function, new State(objects), 0.8, 0.2);

        assertEquals(1,     results[0].getObjects()[0].getPosition().getX());   // t = 0
        assertEquals(1.2,   results[1].getObjects()[0].getPosition().getX());   // t = 0.2
        assertEquals(1.44,  results[2].getObjects()[0].getPosition().getX());   // t = 0.4
        assertEquals(1.728, results[3].getObjects()[0].getPosition().getX());   // t = 0.6
        assertEquals(2.0736,results[4].getObjects()[0].getPosition().getX());   // t = 0.8
    }
}
