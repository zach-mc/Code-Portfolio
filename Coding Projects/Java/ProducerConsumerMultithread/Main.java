package ProducerConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import static ProducerConsumer.Main.EOF;


public class Main {

    public static final String EOF = "EOF";

    public static void main(String[] args) {

        List<String> buffer = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();

        StringProducer producer = new StringProducer(buffer, ThreadColor.ANSI_RED, lock);
        StringConsumer c1 = new StringConsumer(buffer, ThreadColor.ANSI_GREEN, lock);
        StringConsumer c2 = new StringConsumer(buffer, ThreadColor.ANSI_PURPLE, lock);
        StringConsumer c3 = new StringConsumer(buffer, ThreadColor.ANSI_BLUE, lock);

        new Thread(producer).start();
        new Thread(c1).start();
        new Thread(c2).start();
        new Thread(c3).start();

    }
}


class StringProducer implements Runnable{

    private List<String> buffer;
    private String color;
    private ReentrantLock lock;

    public StringProducer(List<String> buffer, String color, ReentrantLock lock) {
        this.buffer = buffer;
        this.color = color;
        this.lock = lock;
    }

    public void run() {
        String[] bufferString = {"Apple", "Banana", "Apricot", "Avocado", "Blackberry",
                "Blueberry", "Boysenberry", "Cantaloupe", "Cherry", "Coconut",
                "Cranberry", "Currant", "Grape", "Grapefruit", "Durian"};

        for(String string: bufferString){
            try{
                System.out.println(color +"Adding: "+string);
                lock.lock();
                buffer.add(string);
                lock.unlock();
                Thread.sleep(500);
            } catch(InterruptedException e){
                System.out.println("Interrupted");
            }
        }

        System.out.println(color+"Finishing...");
        lock.lock();
        buffer.add(EOF);
        lock.unlock();
    }
}


class StringConsumer implements Runnable{

    private List<String> buffer;
    private String color;
    private ReentrantLock lock;

    public StringConsumer(List<String> buffer, String color, ReentrantLock lock){
        this.buffer = buffer;
        this.color = color;
        this.lock = lock;
    }

    public void run(){
        while (true) {
            lock.lock();
            if(buffer.isEmpty()){
                lock.unlock();
                continue;
            }
            if(buffer.getFirst().equals(EOF)){
                System.out.println(color+"Finishing...");
                lock.unlock();
                break;
            }
            else{
                String string = buffer.removeFirst();
                StringBuilder reverseString = new StringBuilder(string);
                System.out.println(color+"Length: "+string.length());
                System.out.println(color+"Reversed: "+reverseString.reverse());
            }
            lock.unlock();
        }
    }
}