import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PriorityQeueFoo<T extends Comparable<T>> extends Foo<T> {

	private final PriorityQueue<T> internalQueue;
	
	public PriorityQeueFoo(int k) {
		super(k);
		internalQueue = new PriorityQueue<>(k+1);
	}

	@Override
	public void offer(T val) {
		if (val==null) {
			throw new IllegalArgumentException("Null's are not allowed");
		}
		internalQueue.offer(val);
		while(internalQueue.size()>k) {
			internalQueue.remove();
		}
	}

	@Override
	public List<T> getTopK() {
		return new ArrayList<T>(internalQueue);
	}

}
