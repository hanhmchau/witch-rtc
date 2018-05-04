package commons;

public class Const {

    public enum ClientMessageType {
        LEARNER_HELLO(1),
        INSTRUCTOR_HELLO(2),
        CALL(3),
        BYE(4),
        ICE(5);

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
        CAN_MAKE_OFFER(1);

        public int value;

        ServerMessageType(int value) {
            this.value = value;
        }
    }
}
