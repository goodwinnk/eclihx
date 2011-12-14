package eclihx.ui.internal.ui.editors.extensions.bracketinserter;

/**
 * Generic filter for some items
 *
 * @param <T> Type of the filtered items
 */
public interface IFilter<T> {
	/**
	 * Check if given item is accepted with the filter 
	 * 
	 * @param item
	 * @return <code>true</code> if item is accepted.
	 */
	boolean isAccepted(T item);
}
