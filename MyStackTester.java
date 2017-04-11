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
 *  Title: class MyStackTester
 *  Description: JUnit test class for MyStack class
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-03-2016
 * */
public class MyStackTester
{
    private MyStack<Integer> empty;
    private MyStack<Integer> one;
    private MyStack<Integer> several;
    static final int DIM = 5;

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and 
     * a list with several entries (0,1,2)
     */ 
    @Before
    public void setUp()
    {
        empty = new MyStack<Integer>();
        one = new MyStack<Integer>();
        one.addElement(new Integer(0));
        several = new MyStack<Integer>();
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several.addElement(new Integer(i));
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
    
    /** Test addElement on several list */
    @Test
    public void testAddElement()
    {
        try 
        {
            several.addElement(null);
            fail("Should have generated an exception!");  
        }
        catch(NullPointerException e)
        {
            //  normal
        }
        several.addElement(0);// Adding into several list
        assertEquals("0 added to end of empty", new Integer(0), several.peek()); 
    }
    
    /** Test removeElement on several list */
    @Test
    public void testRemoveElement()
    {
        try 
        {
            empty.removeElement();
            fail("Should have generated an exception!");  
        }
        catch(EmptyStackException e)
        {
            //  normal
        }
        assertEquals("Check removed element is returned",new Integer(1), several.removeElement()); // Check removed element is returned
        assertEquals("Check first element is removed",new Integer(2), several.peek()); // Check if first node removed
        assertEquals("Check the new size",DIM - 1, several.size()); // Check list resized
    }
    

}
