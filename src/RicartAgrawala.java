
// Java imports
import java.util.Random;

// Visidia imports
import visidia.simulation.process.algorithm.Algorithm;
import visidia.simulation.process.messages.Door;

public class RicartAgrawala extends Algorithm {
    // All nodes data
    int procId;
    // Higher speed means lower simulation speed
    int speed = 4;

    // To display the state
    boolean waitForCritical = false;
    boolean inCritical = false;

    // Critical section thread
    ReceptionRules rr = null;
    // State display frame
    DisplayFrame df;

    public String getDescription() {
        return ("Ricart-Agrawala Algorithm for Mutual Exclusion");
    }

    @Override
    public Object clone() {
        return new RicartAgrawala();
    }

    //
    // Nodes' code
    //
    @Override
    public void init() {
        procId = getId();
        Random rand = new Random(procId);

        rr = new ReceptionRules(this);
        rr.start();

        // Display initial state + give time to place frames
        df = new DisplayFrame(procId);
        displayState();
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ie) {
        }

        while (true) {
            // Wait for some time
            int time = (3 + rand.nextInt(10)) * speed * 1000;
            System.out.println("Process " + procId + " wait for " + time);
            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {
            }

            // Try to access critical section
            waitForCritical = true;
            askForCritical();

            // Access critical
            waitForCritical = false;
            inCritical = true;

            displayState();

            // Simulate critical resource use
            time = (1 + rand.nextInt(3)) * 1000;
            System.out.println("Process " + procId + " enter SC " + time);
            try {
                Thread.sleep(time);
            } catch (InterruptedException ie) {
            }
            System.out.println("Process " + procId + " exit SC ");

            // Release critical use
            inCritical = false;
            endCriticalUse();
        }
    }

    //--------------------
    // Rules
    //-------------------

    // Rule 1 : ask for critical section
    synchronized void askForCritical() {
        // TODO
    }

    // Rule 2 : receive REQ from d
    synchronized void receiveREQ(int d) {
        // TODO
    }

    // Rule 3 : receive REL from d
    synchronized void receiveREL() {
        // TODO
    }

    // Rule 4
    void endCriticalUse() {
        // TODO
    }

    // Access to receive function
    public SyncMessage recoit(Door d) {

        SyncMessage sm = (SyncMessage) receive(d);
        return sm;
    }

    // Display state
    void displayState() {

        String state = new String("\n");
        state = state + "--------------------------------------\n";
        if (inCritical)
            state = state + "** ACCESS CRITICAL **";
        else if (waitForCritical)
            state = state + "* WAIT FOR *";
        else
            state = state + "-- SLEEPING --";

        df.display(state);
    }
}
