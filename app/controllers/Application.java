package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.LegacyWebSocket;
import play.mvc.Result;
import play.mvc.WebSocket;

public class Application extends Controller {

    public Result index() {
        return ok(views.html.Index.render());
    }

    public Result learner() {
        return ok(views.html.Learner.render());
    }

    public Result instructor() {
        return ok(views.html.Instructor.render());
    }
}
