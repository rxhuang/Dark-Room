/**
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */
import java.util.*;
/**
 *  Title: class MyStack
 *  Description: class that implements Stack_QueueInterface
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-03-2016
 * */
public class MyStack<T> implements Stack_QueueInterface<T>
{
    private DoubleEndedLL<T> stack = new DoubleEndedLL<T>();
    public MyStack()
    {
    }
    
    /** Tests if the storage is empty. 
     * @return true a storage contains no items; false otherwise.
     */ 
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    /** Adds an element to a storage 
     * @param newItem - item to be added
     */  
    public void addElement(T newItem)
    {
        stack.addFirst(newItem);
    }

    /** Removes the object from the storage and returns
     * that object as the value of this function.
     * @return value of the removed object.
     */  
    public T removeElement()
    {
        if(stack.size() <=0)
        {
            throw new EmptyStackException();
        }
        return stack.removeFirst();
    }

    /** Returns the size of the storage 
     * @return the size of the storage
     */ 
    public int size()
    {
        return stack.size();
    }
    
    public T peek()
    {
        return stack.get(0);
    }
}
