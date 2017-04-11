/**
 *  NAME: Ruoxin Huang
 *  ID: A99084753
 *  LOGIN: cs12whl
 * */
import java.util.*;
/**
 *  Title: class DoubleEndedLL
 *  Description: class that implements DoubleEndedLLInterface
 *  @author Ruoxin Huang
 *  @version 1.0
 *  @since 02-03-2016
 * */
public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T>
{
    private int nelems;
    private Node head;
    private Node tail;

    protected class Node {
        T data;
        Node next;

        /** Constructor to create singleton Node */
        public Node(T element)
        {
            data = element;
        }

        /** Constructor to create singleton link it between previous and next 
         *   @param element Element to add, can be null
         *   @param nextNode successor Node, can be null 
         */
        public Node(T element, Node nextNode)
        {
            data = element;
            next = nextNode;
        }

        /** Remove this node from the list. Update previous and next nodes */
        public void remove()
        {
            next = null;
        }

        /** Set the next node in the list
         *  @param n new next node
         */
        public void setNext(Node n)
        {
            next = n;
        }

        /** Set the element 
         *  @param e new element 
         */
        public void setElement(T t)
        {
            data = t;
        }

        /** Accessor to get the next Node in the list */
        public Node getNext()
        {
            return next; 
        }

        /** Accessor to get the Nodes Element */
        public T getElement()
        {
            return data; 
        } 
    }

    // Implementatiton of DoubleEndedLL interface
    /** Only 0-argument constructor is define */
    public DoubleEndedLL()
    {
        // The list is empty when initiated
        nelems = 0;
        // Create dummy nodes
        head = new Node(null); 
        tail = new Node(null);
        // Link head and tail together
        head.next = tail;
    }

    /** Checks if the list is empty 
     * @return returns true if the list is empty, false otherwise
     */ 
    public boolean isEmpty()
    {
        return nelems==0;  
    }

    /** Checks the size of the list 
     * @return returns the number of elements in the list
     */ 
    public int size()
    {
        return nelems; 
    }

    /** Adds a new node to the front of the list 
     * @param newItem - an element to add
     */ 
    public void addFirst(T newItem) throws NullPointerException
    {
        // Check for exception
        if(newItem==null){
            throw new NullPointerException();
        }

        if(isEmpty()) // Adding into empty list
        {
            Node temp = new Node(newItem, tail);
            head.setNext(temp);
        }else{ // Adding into not empty list
            Node temp = new Node(newItem, head.getNext());
            head.setNext(temp);
        }
        nelems++;
    }

    /** Adds a new node to the end of the list 
     * @param newItem - an element to add
     */
    public void addLast(T newItem) throws NullPointerException
    {
        // Check for exception
        if(newItem==null){
            throw new NullPointerException();
        }

        if(isEmpty()) // Adding into empty list
        {
            Node temp = new Node(newItem, tail);
            head.setNext(temp);
        }else{ // Adding into not empty list
            Node temp = head.getNext();
            while(temp.next != tail)
            {
                temp = temp.getNext();
            }
            Node temp2 = new Node(newItem, tail);
            temp.setNext(temp2);
        }
        nelems++;
    }

    /** Removes a node from the beginning of the list
     * @return element the data found
     * @throws NullPointerException
     */
    public T removeFirst() throws NullPointerException
    {
        Node temp = head.getNext(); // Let temp be first node
        T newItem = temp.getElement();
        if(newItem==null){
            throw new NullPointerException();
        }       
        head.setNext(temp.getNext()); // Link head to second node
        temp.remove();
        nelems--;
        return newItem;
    }

    /** Removes a node from the end of the list
     * @return element the data found
     * @throws NullPointerException
     */
    public T removeLast() throws NullPointerException
    {
        // Let temp be last node
        Node temp = head.getNext(); 
        while(temp.next != tail)
        {
            temp = temp.getNext();
        }
        T newItem = temp.getElement();
        if(newItem==null){
            throw new NullPointerException();
        }
        // Let temp2 be second to last node
        Node temp2 = head.getNext();
        while(temp2.next != temp)
        {
            temp2 = temp2.getNext();
        }
        temp2.setNext(tail); // Link second to last node with tail
        temp.remove();
        nelems--;
        return newItem;
    }
    
    /** Helper method to get the Node at the Nth index
     * @param index  where in the list to get
     * @return the Nth Node
     */ 
    private Node getNth(int index) 
    {
        Node temp = head.getNext(); // Start at first index
        for(int i=0; i<index; i++) // Loop through the list to get to the index
        {
            temp = temp.getNext();
        }
        return temp;
    }
    
    /** gets the data of the element at index 
     * @param index  where in the list to get
     * @return data of the element at index
     */ 
    public T get(int index) throws IndexOutOfBoundsException
    {
        Node temp = getNth(index);
        if(index<0||index>size()||size()==0){
            throw new IndexOutOfBoundsException();
        }
        return temp.getElement();
    }
}

