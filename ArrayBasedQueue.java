import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ArrayBasedQueue<E> implements QueueInterface<E>, Iterable<E> {
    private LinkedList<E> queue;

    public ArrayBasedQueue() {
        queue = new LinkedList<>();
    }

    @Override
    public Iterator<E> iterator() {
        return queue.iterator();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public QueueInterface<E> copy() {
        ArrayBasedQueue<E> copyQueue = new ArrayBasedQueue<>();
        copyQueue.queue.addAll(this.queue);
        return copyQueue;
    }

    @Override
    public void enqueue(E element) throws IllegalStateException, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null");
        }
        queue.addLast(element);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.getFirst();
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    @Override
    public E dequeue(int index) throws NoSuchElementException {
        if (isEmpty() || index < 0 || index >= size()) {
            throw new NoSuchElementException("Invalid index");
        }
        return queue.remove(index);
    }

    @Override
    public void removeAll() {
        queue.clear();
    }
}
