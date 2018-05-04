package commons;

public class Const {

    public enum ClientMessageType {
        INSTRUCTOR_HELLO(0),
        LEARNER_HELLO(1),
        CALL(2),
        BYE(3),
        ICE(4),
        KNOCK(5);

        public int value;

        ClientMessageType(int value) {
            this.value = value;
        }
    }

    public enum StreamType {
        FACE(1),
        CANVAS(2);

        public int value;

        StreamType(int value) {
            this.value = value;
        }
    }

    public enum ServerMessageType {
        CAN_MAKE_OFFER(0);

        public int value;

        ServerMessageType(int value) {
            this.value = value;
        }
    }
}
