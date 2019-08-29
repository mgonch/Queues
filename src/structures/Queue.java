package structures;

import java.util.NoSuchElementException;

public class Queue<T> implements UnboundedQueueInterface<T> {
	private Node<T> top;
	private Node<T> bottom;
	int stackSize = 0;

	public Queue() {		
            top = null;
            bottom = null;
    }
	
	public Queue(Queue<T> other) {
		Node<T> otherNode = other.top;
		for(int i = 0; i < other.size(); i++){
			this.enqueue(otherNode.getData());
			otherNode = otherNode.getNext();
		}
	}
	
	@Override
	public boolean isEmpty() {
		if(top == null){
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public int size() {
            return stackSize;
	}

	@Override
	public void enqueue(T element) {
        Node<T> newNode = new Node<T>(element);
        stackSize++;
        if(bottom == null){
            top = newNode;
        }
        else{
            bottom.setNext(newNode);
        }
        bottom = newNode;
	}

	@Override
	public T dequeue() throws NoSuchElementException {

        if(isEmpty()){
            throw new NoSuchElementException("Dequeue attempted on empty queue.");
        }
        T toReturn = top.getData();
        top = top.getNext();
        if(top == null){
            bottom = null;
        }
        stackSize--;
        return toReturn;
	}

	@Override
	public T peek() throws NoSuchElementException {
            if(isEmpty()){
                throw new NoSuchElementException("Cannot work on an empty queue.");
            }
            return top.getData();
	}

	@Override
	public UnboundedQueueInterface<T> reversed() {
		Queue<T> returnedVar = new Queue<T>();

		LinkedStack<T> stack = new LinkedStack<T>();
		Node<T> tempNode = top;
		while (tempNode != null){
			stack.push(tempNode.getData());
			tempNode = tempNode.getNext();
		}

		while (!stack.isEmpty()){
			returnedVar.enqueue(stack.pop());
		}
		return returnedVar;
	}
}
