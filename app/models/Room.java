package models;

import akka.actor.ActorRef;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Room {
    private final int roomId;
    private Peer instructor;
    private final List<Peer> learners = new ArrayList<>();
    private int state;

    public Room(int roomId) {
        this.roomId = roomId;
    }

    public void addInstructor(String name, ActorRef actorRef) {
        instructor = new Peer(name, actorRef);
    }

    public void addNewLearner(String name, ActorRef actorRef) {
        Peer learner = new Peer(name, actorRef);
        learners.add(learner);
    }

    public boolean hasInstructor() {
        return instructor != null;
    }

    public Peer findPeerByName(String peerName) {
        if (instructor != null && instructor.name.equals(peerName)) {
            return instructor;
        }
        return learners.stream().filter(peer -> peer.name.equals(peerName)).findFirst().orElse(null);
    }

    public void removePeer(ActorRef actorRef) {
        if (instructor.actorRef.compareTo(actorRef) == 0) {
            instructor = null;
            return;
        }
        learners.removeIf(peer -> peer.actorRef.compareTo(actorRef) == 0);
    }

    public int getRoomId() {
        return roomId;
    }

    public Peer getInstructor() {
        return instructor;
    }

    public void setInstructor(Peer instructor) {
        this.instructor = instructor;
    }

    public List<Peer> getLearners() {
        return learners;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
