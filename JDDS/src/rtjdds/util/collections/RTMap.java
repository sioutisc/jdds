package rtjdds.util.collections;

import rtjdds.rtps.exceptions.CollectionsException;

public interface RTMap extends java.util.Map {

	/**
	 * Puts an object into the table. Runs in <i>O(1)</i> because
	 * it is possible to associate more values to the same key. If the
	 * uniqueness key policy is needed, use the <code>putUnique()</code> method.<br/>
	 * 
	 * @param key
	 * @param value
	 * @throws CollectionsException 
	 * 							If the table is full
	 */
	public Object put(Object key, Object value) ;

	/**
	 * Puts the key-value pair in the table according to the key-uniqueness policy.<br/>
	 * Runs in <i>O(n)</i> because a call to <code>contains(key)</code> is done before
	 * the insertion
	 * 
	 * @param key
	 * @param value
	 * @throws CollectionsException 
	 * 			If the table is full or if there is already one or more values associated
	 * 			to the given key. 
	 */
	public void putUnique(Object key, Object value) throws CollectionsException;

	/**
	 * Lookups the value associated with the given key.<br/>
	 * The execution time of this method strongly depends on the implementation
	 * of the <code>KeyHasher</code> and the capacity of the given <code>ObjectPool</code>. 
	 * If <code>pool.getSize>=hasher.getDomainWidth()</code> the method surely
	 * executes in <i>O(1)</i>, else the execution time depends on the number of 
	 * collisions. Anyway this time is bounded to <i>O(n)</i> where <i>n</i> is the
	 * maximum collisions grade of the provided hash function.
	 * 
	 * @param key
	 * @return the reference to the value if the the key 
	 */
	public Object get(Object key);

	/**
	 * Fills the passed array with references to all the objects contained in this hashtable.
	 * If no used objects are available, <code>null</code> is returned.<br/>
	 * 
	 * @param array
	 * 			The array that will be filled with contained references
	 * @return
	 * 			The array that will be filled with contained references
	 * @throws CollectionsException
	 * 			If the given array is smaller than the free objects contained in this pool
	 */
	public Object[] getAll(Object[] array) throws CollectionsException;

	/**
	 * Removes the value associated with the given key from the table.<br/>
	 * As the <code>get()</code> method, the execution time here is linear
	 * and bounded at <i>O(n)</i>.
	 * 
	 * @param key
	 * @return <code>true</code> if a value has been removed, <code>false</code> otherwise.
	 */
	public Object remove(Object key);

	/**
	 * 
	 * @param key
	 * @return <code>true</code> if there is at least one value associated 
	 * 			to the given key, <code>false</code> otherwise.
	 */
	public boolean contains(Object key);

	public int capacity();

	public int freePos();

	public int size();

	public void clear();

}