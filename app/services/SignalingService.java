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
        return rooms.putIfAbsent(roomId, new Room(roomId));
    }

    public static Optional<Room> getRoomById(int roomId) {
        return Optional.of(rooms.get(roomId));
    }
}
