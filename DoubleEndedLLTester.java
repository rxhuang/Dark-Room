/**
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.*;

/**
 *  Title: class DoubleEndedLLTester
 *  Description: JUnit test class for DoubleEndedLL class
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-03-2016
 * */
public class DoubleEndedLLTester
{
    private DoubleEndedLL<Integer> empty;
    private DoubleEndedLL<Integer> one;
    private DoubleEndedLL<Integer> several;
    private DoubleEndedLL<String>  slist;
    static final int DIM = 5;

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and 
     * a list with several entries (0,1,2)
     */ 
    @Before
    public void setUp()
    {
        empty = new DoubleEndedLL<Integer>();
        one = new DoubleEndedLL<Integer>();
        one.addFirst(new Integer(0));
        several = new DoubleEndedLL<Integer>();
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several.addFirst(new Integer(i));
    }
    
    /** Test isEmpty */
    @Test
    public void testEmpty()
    {
        assertTrue("empty is empty",empty.isEmpty());
        assertTrue("one is not empty",!one.isEmpty());
        assertTrue("several is not empty",!several.isEmpty());
    }
    
    /** Test size */
    @Test
    public void testSize()
    {
        assertEquals("empty has size 0", 0, empty.size());
        assertEquals("one has size one", 1, one.size());
        assertEquals("several has size 5", 5, several.size());
    }
    
    /** Test addFirst on several list */
    @Test
    public void testAddFirst()
    {
        try 
        {
            several.addFirst(null);
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        several.addFirst(0);// Adding into empty list
        assertEquals("0 added to end of empty", new Integer(0), several.get(0)); 
    }
    
    /** Test addLast on several list */
    @Test
    public void testAddLast()
    {
        try 
        {
            several.addLast(null);
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        several.addLast(0);// Adding into empty list
        assertEquals("0 added to end of empty", new Integer(0), several.get(several.size()-1)); 
    }
    
    /** Test removeFirst on several list */
    @Test
    public void testRemoveFirst()
    {
        try 
        {
            empty.removeLast();
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        assertEquals("Check removed element is returned",new Integer(1), several.removeFirst()); // Check removed element is returned
        assertEquals("Check first element is removed",new Integer(2), several.get(0)); // Check if first node removed
        assertEquals("Check the new size",DIM - 1, several.size()); // Check list resized
    }
    
    /** Test removeLast on several list */
    @Test
    public void testRemoveLast()
    {
        try 
        {
            empty.removeLast();
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        assertEquals("Check removed element is returned",new Integer(5), several.removeLast()); // Check removed element is returned
        assertEquals("Check last element is removed",new Integer(4), several.get(3)); // Check if last node removed
        assertEquals("Check the new size",DIM - 1, several.size()); // Check list resized
    }
}
