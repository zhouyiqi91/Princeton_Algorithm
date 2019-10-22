import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

    private class Node{
        private Node prev;
        private Node next;
        private Item item;

        public Node(Item item,Node prev,Node next){
            this.item = item;
            this.prev = prev;
            this.next = next;
            if (next!=null)
                next.prev = this;
            if (prev!=null)
                prev.next = this;
            }
        }


    private Node sentinel;
    private int size;

    // construct an empty deque
    public Deque(){
        sentinel = new Node(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return size==0;
    }

    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node newNode = new Node(item,sentinel,sentinel.next);
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if(item == null) throw new IllegalArgumentException();
        Node newNode = new Node(item, sentinel.prev, sentinel);
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if(isEmpty()) throw new NoSuchElementException();
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if(isEmpty()) throw new NoSuchElementException();
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size--;
        return last.item;
    }


    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = sentinel;
        public boolean hasNext(){return current.next!=sentinel;}
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            current = current.next;
            return current.item;
        }
        public void remove() {throw new UnsupportedOperationException();}
    }


    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> dq = new Deque<String>();
        dq.addFirst("Hugh");
        dq.addFirst("am");
        dq.addFirst("I");
        dq.addLast("I");
        dq.addLast("Love");
        dq.addLast("Gals");

        for(String s:dq){ StdOut.println(s);}
        StdOut.println("remove first: "+dq.removeFirst());
        StdOut.println("remove last: "+dq.removeLast());
        for(String s:dq){ StdOut.println(s);}
    }

}
