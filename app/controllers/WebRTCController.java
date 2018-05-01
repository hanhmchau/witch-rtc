package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.WebSocket;

public class WebRTCController extends Controller {

    public LegacyWebSocket<JsonNode> signalingSocket() {
        return WebSocket.withActor(WebSocketActor::props);
    }
}
