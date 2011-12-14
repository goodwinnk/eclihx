package eclihx.ui.internal.ui.editors.extensions.bracketinserter;

/**
 * Generic filter builder for an abstract items.
 * 
 * @param <T> Type of the filtered items
 */
public class Filter<T> implements IFilter<T> {
	
	private enum Operation {
		NONE {
			@Override
			public <U> boolean accept(IFilter<U> left, IFilter<U> right, U item) {
				return left.isAccepted(item);
			}
		}, 
		NOT {
			@Override
			public <U> boolean accept(IFilter<U> left, IFilter<U> right, U item) {
				return !left.isAccepted(item);
			}
		}, 
		AND {
			@Override
			public <U> boolean accept(IFilter<U> left, IFilter<U> right, U item) {
				return left.isAccepted(item) && right.isAccepted(item);
			}
		}, 
		OR {
			@Override
			public <U> boolean accept(IFilter<U> left, IFilter<U> right, U item) {
				return left.isAccepted(item) || right.isAccepted(item);
			}
		};		
		
		public abstract <U> boolean accept(IFilter<U> left, IFilter<U> right, U item);
	}
	
	private final IFilter<T> leftFilter;
	private final IFilter<T> rightFilter;
	private final Operation operation;
	
	private Filter(IFilter<T> left, IFilter<T> right, Operation operation) {
		leftFilter = left;
		rightFilter = right;
		this.operation = operation;
	}
	
	/**
	 * @return Filter which accepts any element.
	 */
	public static <U> IFilter<U> any() {
		return new IFilter<U>() {
			@Override
			public boolean isAccepted(U item) {
				return true;
			}
		};
	}
	
	/**
	 *  @return Filter which doesn't accept any element.
	 */
	public static <U> IFilter<U> none() {
		return new IFilter<U>() {
			@Override
			public boolean isAccepted(U item) {
				return false;
			}
		};
	}
	
	/**
	 * Create a filter with internal AND predicate.
	 * 
	 * @param left Left filter operand.
	 * @param right Right filter operand.
	 * @return A new filter compound with AND condition.
	 */
	public static <U> IFilter<U> and(IFilter<U> left, IFilter<U> right) {
		return new Filter<U>(left, right, Operation.AND);
	}
	
	/**
	 * Create a filter with internal OR predicate.
	 * 
	 * @param left Left filter operand.
	 * @param right Right filter operand.
	 * @return A new filter compound with OR condition.
	 */
	public static <U> IFilter<U> or(IFilter<U> left, IFilter<U> right) {
		return new Filter<U>(left, right, Operation.OR);
	}
	
	/**
	 * Constructs new filter by inverting result of given filter.
	 * 
	 * @param filter filter operand.
	 * @return New filter with negation operation.
	 */
	public static <U> IFilter<U> not(IFilter<U> filter) {
		return new Filter<U>(filter, null, Operation.NOT);
	}	
	
	public boolean isAccepted(T item) {
		return operation.accept(leftFilter, rightFilter, item);
	}
}
