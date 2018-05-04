package services;

import akka.actor.ActorRef;
import akka.actor.dsl.Creators;
import models.Peer;
import models.Room;

import java.util.*;

public class SignalingService {

    // TODO: refactor this to use Redis and whatnot
    private static Map<Integer, Room> rooms = new HashMap<>();

    public static Room startRoom(int roomId) {
        Room newRoom = new Room(roomId);
        rooms.put(roomId, newRoom);
        return newRoom;
    }

    public static Room getRoomById(int roomId) {
        int n = 0;
        return rooms.get(roomId);
    }

    public static void removePeer(ActorRef actorRef) {
        rooms.values().forEach(room -> room.removePeer(actorRef));
    }
}
