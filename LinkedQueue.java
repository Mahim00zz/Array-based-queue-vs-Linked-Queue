

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class LinkedQueue<E> implements QueueInterface<E>, Iterable<E> {

	private Node<E> front = null;
	private Node<E> back = null;
	
	private int size = 0;
	
	@Override
	public Iterator<E> iterator() {
		Vector<E> v = new Vector<E>();
		Node<E> curNode = this.front;
		
		while (curNode != null) {
			v.addElement(curNode.getElement());
			curNode = curNode.getNext();
		}
		
		ElementIterator<E> myIterator = new ElementIterator<E>(v);
		
		return myIterator;
	}

	@Override
	public boolean isEmpty() {
		return (this.size == 0);
	}

	@Override
	public int size() {
		return this.size;
	}
	@Override
	public void enqueue(E e) throws IllegalStateException, NullPointerException {
		if (e == null) {
			NullPointerException npe = new NullPointerException();
			throw npe;
		}
		
		Node<E> newNode = new Node<E>(e);
		
		if (this.isEmpty()) {
			this.front = newNode;
			this.back = newNode;
		} else {
			newNode.setPrevious(this.back);
			this.back.setNext(newNode);
			this.back = newNode;
		}
		
		this.size++;
	}

	@Override
	public E peek() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.front.getElement();
		}
	}

	@Override
	public E dequeue() {
		
		if (this.isEmpty()) {
			return null;
		} else {
			E temp = this.front.getElement();
			
			this.front = this.front.getNext();
			
			if (this.front == null) {
				this.back = null;
			} else {
				this.front.setPrevious(null);
			}

			this.size--;
			
			return temp;
		}
	}

	@Override
	public E dequeue(int index) throws NoSuchElementException {
		if (index < 0 || index >= this.size) {
			NoSuchElementException nsee = new NoSuchElementException();
			throw nsee;
		}
		
		E temp = null;
		
		if (this.isEmpty()) {
			return temp;
		} else {	
				if (index == 0) {
					temp = this.front.getElement();
					this.front = this.front.getNext();
					
					if (this.front == null) {
						this.back = null;
					} else {
						this.front.setPrevious(null);
					}		
					
				}else {
					Node<E> curr = this.front.getNext();
					int currIndex = 1;
					while (currIndex != index) {
						curr = curr.getNext();
						currIndex++;
					}
					
					temp = curr.getElement();
					curr.getPrevious().setNext(curr.getNext());
					curr.getNext().setPrevious(curr.getPrevious());
	
				}
				
			
			this.size--;
			
		}
		return temp;
	}


	@Override
	public void removeAll() {
		this.front = null;
		this.back = null;
		this.size = 0;

	}

	@Override
	public QueueInterface<E> copy() {
	    LinkedQueue<E> copyQueue = new LinkedQueue<>();
	    Node<E> current = front;
	    while (current != null) {
	        copyQueue.enqueue(current.getElement());
	        current = current.getNext();
	    }
	    return copyQueue;
	}

	

}