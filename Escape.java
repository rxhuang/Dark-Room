/**
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */
import java.util.*;
/**
 *  Title: class Escape
 *  Description: class with main that actually plays the game
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-03-2016
 * */
public class Escape extends DarkRoom {
    static DarkRoom room = new DarkRoom(); // Create a dark room
    public static void main(String[] args) {
        // Read from a file
        room.readFromFile(args[0]);
        //room.readFromFile("C:/Users/julia/Desktop/CSE12/HW4/moreRooms/room3.txt");
        
        // Use stack/queue to play the game
        room.escapeDarkRoom("stack");
        room.clear();
        room.escapeDarkRoom("queue");
    }
}