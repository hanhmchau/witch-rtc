package services;

import akka.actor.ActorRef;
import akka.actor.dsl.Creators;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SignalingService {

    // TODO: refactor this to use Redis and whatnot
    private static Map<String, ActorRef> clients = new HashMap<>();

    public static void addNewClient(String name, ActorRef actorRef) {
        clients.put(name, actorRef);
    }

    public static void removeClient(ActorRef closedActor) {
        clients.entrySet().removeIf(client -> client.getValue().compareTo(closedActor) == 0);
    }

    public static ActorRef getActorRef(String name) {
        return clients.get(name);
    }
}
