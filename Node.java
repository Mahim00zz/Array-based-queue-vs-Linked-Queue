
public class Node<E> {
    private E data;
    private Node<E> nextNode;
    private Node<E> previousNode;

    public Node(E data) {
        this.data = data;
        this.nextNode = null;
        this.previousNode = null;
    }

    public Node(Node<E> previousNode, E data, Node<E> nextNode) {
        this.data = data;
        this.nextNode = nextNode;
        this.previousNode = previousNode;
    }

  
    public E getElement() {
        return data;
    }

   
    public void setElement(E data) {
        this.data = data;
    }

   
    public Node<E> getNext() {
        return nextNode;
    }

   
    public void setNext(Node<E> nextNode) {
        this.nextNode = nextNode;
    }

   
    public Node<E> getPrevious() {
        return previousNode;
    }

    
    public void setPrevious(Node<E> previousNode) {
        this.previousNode = previousNode;
    }
}
