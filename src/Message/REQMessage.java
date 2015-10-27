package Message;

import visidia.simulation.process.messages.Message;

public class REQMessage extends NetMessage {

    private MsgType type;
    private int h;

    public REQMessage(MsgType t) {
        type = t;
    }

    @Override
    public MsgType getMsgType() {
        return type;
    }

    @Override
    public Message clone() {
        return new REQMessage(type);
    }

    @Override
    public String toString() {
        return type.toString();
    }

    @Override
    public String getData() {
        return this.toString();
    }

    public int getH() {
        return h;
    }
}
