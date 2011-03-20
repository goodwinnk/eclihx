package eclihx.core.util.language;

/**
 * Simple pair class.
 *
 * @param <T1> type of the first element.
 * @param <T2> type of the second element.
 */
public class Pair<T1, T2> {
	
	private final T1 first;
	private final T2 second;
	
	/**
	 * The only constructor 
	 * @param first the first element.
	 * @param second the second element.
	 */
	public Pair(T1 first, T2 second) {
		super();
		this.first = first;
		this.second = second;
	}
	
	/**
	 * First element
	 * @return First element
	 */
	public T1 getFirst() {
		return first;
	}
	
	/**
	 * Second element
	 * @return Second element
	 */
	public T2 getSecond() {
		return second;
	}
	
	@Override
	public int hashCode() {
        int hashFirst = first != null ? first.hashCode() : 0;
        int hashSecond = second != null ? second.hashCode() : 0;

        return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

	@Override
    public boolean equals(Object other) {
        if (other instanceof Pair) {
        	Pair<?, ?> otherPair = (Pair<?, ?>)other;
        	boolean firstAreEqual = (this.first == otherPair.first ||
                    (this.first != null && otherPair.first != null &&
                     this.first.equals(otherPair.first)));
        	boolean secondAreEqual = (this.second == otherPair.second ||
                    (this.second != null && otherPair.second != null &&
                     this.second.equals(otherPair.second)));
            return firstAreEqual && secondAreEqual;
        }

        return false;
    }
}
