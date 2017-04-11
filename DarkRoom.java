/**
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
/**
 *  Title: class DarkRoom
 *  Description: class that plays a game of dark room that finds the door in a dark room.
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-03-2016
 * */
public class DarkRoom implements DarkRoomInterface {

    protected char [][] darkRoom; // Array thay represents the dark room
    protected int numRows=0; // number of rows in the room
    protected int numCols; // number of columns in the room

    public void readFromFile(String fname) {
        String line; // The line in a file we are currently reading 
        BufferedReader inputStrem; // Create buffered reader to read faster
        StringTokenizer st; // Create a string tokenizer to break strings into tokens

        try {
            int currentRow = 0; // The row number in the file we are currently reading

            inputStrem = new BufferedReader(new FileReader(fname)); // Wrap FileReader in BufferedReader

            while ((line = inputStrem.readLine()) != null) { // While the line we are reading in the file is not null
                if (numRows == 0) { // If we just started to read the file
                    st = new StringTokenizer(line); // Use tokenizer on first line
                    numRows = Integer.parseInt(st.nextToken()); // Assign numRows to the next token
                    numCols = Integer.parseInt(st.nextToken()); // Assign numCols to the next token
                    darkRoom = new char[numRows][numCols]; // Initiate the size of the dark room 
                } else if (line.length() == 1) // If the length of the line we are reading is only one
                    break; // stop reading the file
                else {
                    for (int c = 0; c < numCols; c++) { // Loop through each character in a line
                        darkRoom[currentRow][c] = line.charAt(c); // Lat darkRoom take in each character in the line
                    }
                    currentRow ++; // Advance to next row
                }
            }
        }
        catch (IOException e) { // Catch input ouput exception
            System.out.println (e.toString()); // Print exception`s calss name
            System.out.println("Could not find file " + fname); // Print that file was not found
        }

    }

    //Helper methods:
    /**
     * Method that returns the Location of "start"
     * @return     starting location
     */ 
    public Location findStart()
    {
        Location start;
        int row=0, col=0;
        // Loop through the whole room
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numCols; j++)
            {
                if(darkRoom[i][j] == 'S')
                {
                    // Save the coordinates of start
                    row=i;
                    col=j;
                    break;
                } 
            }
        }
        start = new Location(row,col);
        return start;
    }

    /**
     * Method that checks if the goal was found
     * @param     the location to check
     * @return    true if it is a door otherwise false
     */ 
    public boolean isDoor (Location loc)
    {
        if(darkRoom[loc.getRow()][loc.getColumn()] == 'D') // Check if the door is here
        {
            return true; 
        }else
        {
            return false;
        }
    }

    /**
     * Method that checks if you can move
     * @param     the location to check
     * @return    true if it is movable otherwise false
     */ 
    public boolean canMove(Location loc)
    {
        boolean s = false;
        if(darkRoom[loc.getRow()][loc.getColumn()] == ' ') //check if is an empty space
        {
            s = true;
        }else
        {
            s = false;
        }
        return s;
    }

    /**
     * Marks explored (visited) positions
     * @param     the location to be marked
     */
    public void markVisited (Location loc)
    { 
        darkRoom[loc.getRow()][loc.getColumn()] = '.'; // Mark with a dot
    }

    /**
     * counts the number of visited positions
     * @return     the number of visited location
     */
    public int countVisited()
    {
        int counter=0;
        // Loop through whole room and count dots
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numCols; j++)
            {
                if(darkRoom[i][j] == '.')
                {
                    counter++;
                }
            }
        }
        return counter;
    }

    /**
     * removes marks from visiting (removes '.')
     */
    public void clear()
    {
        // Loop through whole room and remove all dots
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numCols; j++)
            {
                if(darkRoom[i][j] == '.')
                {
                    darkRoom[i][j] = ' ';
                }
            }
        }
    }

    /**
     * prints your array that represents a room
     */
    public void printRoom()
    {
        // Loop through whole room and print everything in it
        for(int i=0; i<numRows; i++)
        {
            for(int j=0; j<numCols; j++)
            {
                System.out.print(darkRoom[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Search for ESCAPE!!!
     * @param     the choice of using stack ot queue
     */
    public void escapeDarkRoom(String choice){
        int positionsLeft=0;
        Stack_QueueInterface <Location> storage; // Create new storage
        if("stack".equals(choice))
        {
            storage = new MyStack<Location>();
        }else
        {
            storage = new MyQueue<Location>();
        }

        Location currentLoc = findStart(); // Find the start location
        storage.addElement(currentLoc); // Store start in the stack/queue
        // Check if any neighbor location is the door
        while(!isDoor(currentLoc.left())&&!isDoor(currentLoc.up())&&!isDoor(currentLoc.right())&&!isDoor(currentLoc.down()))
        {
            // Check if there is empty space around, and add it to stack/queue
            if(canMove(currentLoc.left()))
            {
                storage.addElement(currentLoc.left());
                positionsLeft++;
            }
            if(canMove(currentLoc.up()))
            {
                storage.addElement(currentLoc.up());
                positionsLeft++;
            }
            if(canMove(currentLoc.right()))
            {
                storage.addElement(currentLoc.right());
                positionsLeft++;
            }
            if(canMove(currentLoc.down()))
            {
                storage.addElement(currentLoc.down());
                positionsLeft++;
            }
            currentLoc = storage.removeElement(); // Remove a location and "move"
            positionsLeft--;
            // Do not replace start with a dot
            if(!(currentLoc.row==findStart().row&&currentLoc.column==findStart().column))
            {
                markVisited(currentLoc);
            }else // Do not put in the empty neighbors of start into queue/stack twice
            {
                currentLoc = storage.removeElement();
                markVisited(currentLoc);
            }
        }

        printGoal(choice, countVisited(), positionsLeft);
        printRoom();
        clear();
    }

    /**
     * print results
     * @param     stepsTaken the number of steps taken
     * @param     positionLeft the number of elements left in the stack/queue
     */
    public void printGoal(String choice, int stepsTaken, int positionsLeft)
    {
        System.out.println("Goal found (with " + choice + "): It took "
            + stepsTaken + " explored positions");
        System.out.println("There is (are) " + positionsLeft 
            + " position(s) left to explore in " + choice);
    }
}
