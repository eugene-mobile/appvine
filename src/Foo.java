import java.util.List;

public abstract class Foo<T extends Comparable<T>> {

	protected final int k;
	
	public Foo(int k) {
		this.k = k;
	}

	public abstract void offer(T object);

	public abstract List<T> getTopK();

}