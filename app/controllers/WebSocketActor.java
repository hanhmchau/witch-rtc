package controllers;

import akka.actor.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commons.Const;
import models.Peer;
import models.Room;
import play.libs.Json;
import services.SignalingService;
import sun.misc.Signal;

public class WebSocketActor extends UntypedActor {

    static Props props(ActorRef out) {
        return Props.create(WebSocketActor.class, out);
    }

    private final ActorRef out;

    public WebSocketActor(ActorRef out) {
        this.out = out;
    }

    public void onReceive(Object message) throws Exception {
        JsonNode json = (JsonNode) message;
        int type = json.findPath("type").asInt();
        String name = json.findPath("name").asText();

        int roomId = json.findPath("room").asInt();
        Room room = SignalingService.getRoomById(roomId);
        if (room == null) {
            room = SignalingService.startRoom(roomId);
        }

        ObjectNode result = Json.newObject();
        result.putPOJO("name", name);
        result.put("room", roomId);

        if (type == Const.ClientMessageType.CALL.value || type == Const.ClientMessageType.ICE.value) {

            String callee = json.findPath("callee").asText();
            Peer peer = room.findPeerByName(callee);
            if (peer != null) {
                peer.actorRef.tell(json, self());
            }

        } else if (type == Const.ClientMessageType.INSTRUCTOR_HELLO.value) {

            room.addInstructor(name, out);
            result.put("notice", Const.ServerMessageType.CAN_MAKE_OFFER.value);
            result.put("instructor", room.getInstructor().name);
            room.getLearners().forEach(peer -> peer.actorRef.tell(result, self()));

            ObjectNode responseResult = Json.newObject();
            responseResult.put("response", String.format("Welcome to room %d, %s!", roomId, name));
            out.tell(responseResult, self());

        } else if (type == Const.ClientMessageType.LEARNER_HELLO.value) {

            room.addNewLearner(name, out);
            if (room.hasInstructor()) {
                result.put("notice", Const.ServerMessageType.CAN_MAKE_OFFER.value);
                result.put("instructor", room.getInstructor().name);
                out.tell(result, self());
            }
            ObjectNode responseResult = Json.newObject();
            responseResult.put("response", String.format("Welcome to room %d, %s!", roomId, name));
            out.tell(responseResult, self());
        }
    }

    @Override
    public void postStop() throws Exception {
        SignalingService.removePeer(out);
    }
}