package commons;

public class Const {

    public enum ClientMessageType {
        HELLO(1),
        CALL(2),
        BYE(3),
        ICE(4);

        public int value;

        ClientMessageType(int value) {
            this.value = value;
        }
    }

    public enum ServerMessageType {
        OFFER(1);

        public int value;

        ServerMessageType(int value) {
            this.value = value;
        }
    }
}
