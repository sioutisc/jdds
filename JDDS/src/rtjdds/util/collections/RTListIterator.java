package rtjdds.util.collections;

import rtjdds.rtps.exceptions.CollectionsException;

public interface RTListIterator {

	public boolean hasNext();

	public Object next();

	public boolean hasPrevious();

	public Object previous();

	public int nextIndex();

	public int previousIndex();

	public void remove() throws CollectionsException, IndexOutOfBoundsException;

	public void add(Object o) throws CollectionsException,
			IndexOutOfBoundsException;

	public void set(Object o);

}