
// Visidia imports
import visidia.simulation.process.messages.Door;

// Reception thread
public class ReceptionRules extends Thread {

    private RicartAgrawala algo;

    public ReceptionRules(RicartAgrawala a) {

        algo = a;
    }

    public void run() {

        Door d = new Door();

        while (true) {

            SyncMessage m = (SyncMessage) algo.recoit(d);
            int door = d.getNum();

            switch (m.getMsgType()) {

                case REQ:
                    algo.receiveREQ(door);
                    break;

                case REL:
                    algo.receiveREL();
                    break;

                default:
                    System.out.println("Error message type");
            }
        }
    }
}

