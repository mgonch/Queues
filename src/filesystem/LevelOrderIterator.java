package filesystem;

import structures.Queue;
import structures.UnboundedQueueInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 * 
 * @author liberato
 *
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	private Queue<File> node = new Queue<File>();



	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		if(!rootNode.exists()){
			throw new FileNotFoundException();
		}
		node = new Queue<>();
		node.enqueue(rootNode);
	}
	
	@Override
	public boolean hasNext(){
		if(!node.isEmpty()){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public File next() throws NoSuchElementException {

		if(node.isEmpty()){
			throw new NoSuchElementException();
		}
		File tempFile = node.peek();
		if(tempFile.isDirectory()) {
			File[] sortedFile = tempFile.listFiles();
			Arrays.sort(sortedFile);
			for (int i = 0; i < sortedFile.length; i++) {
				node.enqueue(sortedFile[i]);
			}
		}
		return node.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}
