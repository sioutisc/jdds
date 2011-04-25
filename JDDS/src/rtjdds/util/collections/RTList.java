package rtjdds.util.collections;

import rtjdds.rtps.exceptions.CollectionsException;

public interface RTList extends Copyable {

	/**
	 * Adds the given object to the list. Throws exception if the list
	 * is full or the given index is invalid. To be valid the index
	 * has to respect the following relation:<br/>
	 * <CODE> (0<= index && index<size()) </CODE>.<br/>
	 * Since this list is implemented as doubly linked, the execution
	 * time of this method is bounded at <i>O(size()/2)</i>
	 * 
	 */
	public void add(int index, Object o) throws CollectionsException,
			IndexOutOfBoundsException;

	/**
	 * Adds the passed object at the tail of this list.
	 * 
	 * @param o
	 * @return 
	 * 			always true
	 * @throws CollectionsException
	 * 			if the list is full
	 */
	public boolean add(Object o) throws CollectionsException;

	/**
	 * Checks if the given object is in this list.<br/>
	 * Returns true is there (at least) is an element <code>e</code> in the list that satisfy
	 * the following condition: <code>(o==null ? e==null : o.equals(e))</code>
	 * @param arg0
	 * @return
	 */
	public boolean contains(Object o);

	/**
	 * Returns the reference to the object contained at the given position
	 * or null if <code>(index < 0 || index >=size())</code>.
	 * Runs bounded at <i>O(size()/2)</i>.
	 * 
	 * @param arg0
	 * @return
	 */
	public Object get(int index) throws IndexOutOfBoundsException;
	
	public Object getHead();
	public Object getTail();
	public Object removeHead();
	public Object removeTail();
	
	/**
	 * Returns the FIRST element in the list that matches
	 * the following condition:<br/>
	 * <code>(o==null ? e==null : o.equals(e))</code>
	 * @param o
	 * @return
	 * 		the index of the element if it is in the list.
	 * 		-1 otherwise.
	 */
	public int indexOf(Object o);

	/**
	 * @return
	 * 			<code>true</code> if the list is empty (size()==0),
	 * 			<code>false</code> otherwise
	 */
	public boolean isEmpty();

	/**
	 * Returns the LAST element in the list that matches
	 * the following condition:<br/>
	 * <code>(o==null ? e==null : o.equals(e))</code>
	 * @param o
	 * @return
	 * 		the index of the element if it is in the list.
	 * 		-1 otherwise.
	 */
	public int lastIndexOf(Object o);

	/**
	 * Sets the element at the given position at the given object.
	 * 
	 * @param index
	 * @param o
	 * @return
	 * @throws IndexOutOfBoundsException
	 * 			if the given index is invalid
	 */
	public Object set(int index, Object o) throws IndexOutOfBoundsException;

	/**
	 * Removes the element at the given position.
	 * 
	 * @param index
	 * @return
	 * @throws CollectionsException
	 * 			If the list is empty
	 * @throws IndexOutOfBoundsException
	 * 			If the given index is invalid
	 */
	public Object remove(int index) throws CollectionsException,
			IndexOutOfBoundsException;

	/**
	 * Removes the FIRST <code>e<code> element that satisfy the following condition:<br/>
	 * <code>(o==null ? e==null : o.equals(e))</code>
	 * 
	 * @param o
	 * @return
	 * 			<code>true</code> if one object has been removed,
	 * 			<code>false</code> otherwise.
	 * @throws CollectionsException
	 */
	public Object remove(Object o) throws CollectionsException;

	/**
	 * Removes from the list all the objects that satisfy the following condition:<br/>
	 * <code>(o==null ? e==null : o.equals(e))</code>
	 * @param o
	 * @return
	 * 			<code>true</code> if at least one object has been removed,
	 * 			<code>false</code> otherwise.
	 * @throws CollectionsException
	 */
	public boolean removeAll(Object o) throws CollectionsException;

	/**
	 * Removes all the elements from this list.<br/>
	 * The postcondition is:<br/>
	 * <code>this.size() == 0</code>
	 * @throws CollectionsException
	 */
	public void clear() throws CollectionsException;
	
	public RTListIterator listIterator();

	public RTListIterator listIterator(int index) throws IndexOutOfBoundsException;

	public Object[] toArray(Object[] array) throws CollectionsException;

	public int size();

	public int capacity();

	public int freePos();

}