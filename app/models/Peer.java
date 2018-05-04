package models;

import akka.actor.ActorRef;

public class Peer {
    public String name;
    public ActorRef actorRef;

    public Peer(String name, ActorRef actorRef) {
        this.name = name;
        this.actorRef = actorRef;
    }
}
