package controllers;

import akka.actor.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import commons.Const;
import play.libs.Json;
import services.SignalingService;

import java.util.HashMap;
import java.util.Map;

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

        ObjectNode result = Json.newObject();
        result.putPOJO("name", name);

        if (type == Const.ClientMessageType.HELLO.value) {

            SignalingService.addNewClient(name, out);
            result.put("response", String.format("Hello, %s!", name));
            out.tell(result, self());

        } else if (type == Const.ClientMessageType.CALL.value) {

            String callee = json.findPath("callee").asText();
            ActorRef actorRef = SignalingService.getActorRef(callee);
            result.putPOJO("sdp", json.findPath("sdp"));
            if (actorRef != null) {
                actorRef.tell(json, self());
            }

        } else if (type == Const.ClientMessageType.ICE.value) {

            String callee = json.findPath("callee").asText();
            ActorRef actorRef = SignalingService.getActorRef(callee);
//            result.putPOJO("ice", json.findPath("ice"));
            if (actorRef != null) {
                actorRef.tell(json, self());
            }
        }
    }

    @Override
    public void postStop() throws Exception {
        SignalingService.removeClient(out);
    }
}