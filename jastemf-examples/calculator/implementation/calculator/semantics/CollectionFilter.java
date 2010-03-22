package calculator.semantics;

import java.util.*;

/**
 * This class allows to filter collections by a given
 * {@link AFilterCriteria criteria} and append the filtered elements
 * to another collection. The Original collection is not changed at all.
 * @author C. Bürger
 */
public class CollectionFilter {
	/**
	 * Filter a given collection based on a given criteria and append
	 * the filtered elements to another collection.
	 * @param <T> The type of the input collection's elements.
	 * @param <C> The type of the collection to which the filtered
	 * elements are added.
	 * @param toFilter The source collection to filter. It is not
	 * changed at all.
	 * @param filterToUse An appropriate filter to execute, which
	 * is able to filter collections with elements of type <tt>T</tt>.
	 * @param resultCollection The collection to which all elements
	 * satisfying the given filter are added (Actually this are all
	 * elements, which passed the given filter).
	 * @return The given <tt>resultCollection</tt>.
	 */
	public static
	<
	// For any type T...
	T,
	// ...and a given input collection of elements of type T or a sub-type...
	I extends Iterable<? extends T>,
	// ...and a filter, which filters collections of type T or a super-type,...
	F extends AFilterCriteria<? super T>,
	// ...we can append the filtered elements to a collection of type T.
	C extends Collection<T>
	>
	C filter(I toFilter, F filterToUse, C resultCollection) {
		for (T elem:toFilter) {
			if (filterToUse.passed(elem))
				resultCollection.add(elem);
		}
		return resultCollection;
	}
	
	/**
	 * Implementations of this abstract class represent filter criteria for
	 * collections. A filter may depend on another filter. Elements
	 * only pass the filter iff they satisfy its {@link #_passed(Object)}
	 * method as well as the {@link #_passed(Object)} methods of all filters
	 * this one depends on.
	 * @author C. Bürger
	 */
	public static abstract class AFilterCriteria<T> {
		protected AFilterCriteria<T> additionalFilter;
		
		/**
		 * Constructs a filter instance not depending on any other filter.
		 */
		public AFilterCriteria() {additionalFilter = null;}
		
		/**
		 * Constructs a filter instance depending on another filter.
		 * @param additionalFilter The other filter this one depends on.
		 */
		public AFilterCriteria(AFilterCriteria<T> additionalFilter) {
			this.additionalFilter = additionalFilter;
		}
		
		/**
		 * Returns, whether this filter depends on another filter or not.
		 * @return True, iff this filter depends on another one.
		 */
		public boolean isBasedOnOtherFilter() {
			return additionalFilter != null;
		}
		
		/**
		 * Specifies, that this filter depends on another filter, whereas a
		 * previous filter dependency is overwritten.
		 * @param criteria Another filter this one depends on or
		 * <tt>null</tt> if this filter is independent.
		 */
		public void setAdditionalFilter(AFilterCriteria<T> criteria) {
			additionalFilter = criteria;
		}
		
		/**
		 * Decides, whether an element passed this filter, as well as all the
		 * filters this one depends on, or not. The filters this one depends on
		 * are checked first.
		 * @param elem The element to check.
		 * @return True, iff the element passed the filter.
		 */
		public boolean passed(T elem) {
			return additionalFilter != null ?
					additionalFilter.passed(elem) && _passed(elem) :
					_passed(elem);
		}
		
		/**
		 * Performs the actual check, if an element is filtered by this filter
		 * or not. The filters, this one depends on, are not considered.
		 * @param elem The element to check.
		 * @return True, iff the element passes this filter.
		 */
		public abstract boolean _passed(T elem);
	}
}
