import java.util.Iterator;
import java.util.Vector;

public class ElementIterator<E> implements Iterator<E> {

	private Vector<E> myElements;

	public ElementIterator(Vector<E> myElements) {
		this.myElements = myElements;
	}

	@Override
	public boolean hasNext() {
		if (this.myElements.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public E next() {
		E element = null;

		element = myElements.get(0);
		myElements.remove(0);

		return element;
	}

}