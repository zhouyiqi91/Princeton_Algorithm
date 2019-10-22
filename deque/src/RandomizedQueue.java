import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item>{

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue(){
        items = (Item[]) new Object[8];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size==0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }
    //resize
    private void resize(int capacity){
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0;i<size;i++){
            temp[i] = items[i];
        }
        items = temp;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException();
        if (size==items.length) resize(size*2);
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        Item itemRemove = items[index];
        size--;
        items[index] = items[size];
        items[size] = null;
        if (size<items.length/4) resize(items.length/2);
        return itemRemove;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(isEmpty()) throw new NoSuchElementException();
        int index = StdRandom.uniform(size);
        return (items[index]);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedQueueIter();
    }

    private class RandomizedQueueIter implements Iterator<Item>{
        private Item[] rdq;
        int current=-1;

        public RandomizedQueueIter(){
            rdq = (Item[]) new Object[size];
            current = -1;
            for (int i=0;i<size;i++) rdq[i] = items[i];
            StdRandom.shuffle(rdq);
        }

        public boolean hasNext(){
            return ((current+1)<size);  //current+1 !!!
        }

        public Item next(){
            if (!hasNext()) throw new NoSuchElementException();
            return (rdq[++current]);
        }

        public void remove() {throw new UnsupportedOperationException();}
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> a = new RandomizedQueue<Integer>();
        a.enqueue(1);
        a.enqueue(3);
        a.enqueue(5);
        System.out.println(a.size());
        a.dequeue();
        for (int i : a) {System.out.println(i);}

    }


}
