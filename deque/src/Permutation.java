import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

    public static void main(String[] args){

        RandomizedQueue<String> r = new RandomizedQueue<String>();
        //StdOut.println(" size: " + r.size());

        // input from command line
        int k = Integer.parseInt(args[0]);

        String s;
        //StdOut.println("Input strings: "); -> no redundant output!

        // use ctrl+z to end input
        while(!StdIn.isEmpty()){
            //r.enqueue(s); -> the last string won't be enqueued!
            s = StdIn.readString();
            r.enqueue(s);
        }

        int count = 0;
        for(String ss:r){
            if(count < k){
                StdOut.println(ss);
                count++;
            }else break;
        }

    }

}