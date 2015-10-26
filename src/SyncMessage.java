
import visidia.simulation.process.messages.Message;

public class SyncMessage extends Message {

    private MsgType type;

    public SyncMessage(MsgType t) {
        type = t;
    }

    public MsgType getMsgType() {
        return type;
    }

    @Override
    public Message clone() {
        return new SyncMessage(type);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public String getData() {
        return this.toString();
    }

}
