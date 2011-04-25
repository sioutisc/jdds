/*
 * Copyright (c) 2005 by the University of California, Irvine
 * All Rights Reserved.
 * 
 * This software is released under the terms of the RTZen license, which
 * you should have received along with this software. If not, you may
 * obtain a copy here: http://zen.ece.uci.edu/rtzen/license.php
 */

// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
// !!!!!!!!!!!!!!	TODO: check the Zen licence	   !!!!!!!!!!!!!!!!!!!
// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

package rtjdds.util;

/**
 * This class provides a constant space hashtable which is Scoped Memory safe.
 * 
 * @author Krishna Raman
 */
public class Hashtable {

    /**
     * The hashtable uses linear chaining and this class provides a link list
     * node.
     */
    class HashNode {
        int key;

        Object data;

        HashNode next;
    }

    /** Table of hash value-objects. */
    HashNode[] list;

    /** Linked list of unused hash nodes. */
    HashNode unusedHashNodes;

    /**
     * Adds a hash node to the begining of the unused node list.
     * 
     * @param h
     *            The hash node to return to the unused list.
     */
    private void push(HashNode h) {
        h.next = unusedHashNodes;
        unusedHashNodes = h;
    }

    /** Get a hash node from the begining of the unused node list. */
    private HashNode pop() {
        synchronized (list) {
            HashNode tmp = unusedHashNodes;
            unusedHashNodes = unusedHashNodes.next;
            return tmp;
        }
    }
    
    /**
     * Creates an <code>Hashtable</code> with max <code>limit</code>
     * entries.
     * 
     * @param limit
     */
    public Hashtable(int limit) {
    	init(limit);
    }

    /**
     * Initialize the hash table and create the hash nodes.
     * 
     * @param limit
     *            The maximum number of values that will be stored in the table.
     */
    public void init(int limit) {
        list = new HashNode[limit];
        for (int i = 0; i < limit; i++) {
            push(new HashNode());
        }
    }

    /**
     * Associate the key with the data in the hash table.
     * Collisions of hash code are handled by a linked list...
     * 
     * @param key
     *            The key into the hashtable.
     * @param data
     *            The data to associate with the key.
     */
    public void put(int key, Object data) {
        int hash = key % list.length;
        HashNode hn = pop();
        hn.key = key;
        hn.data = data;
        synchronized (list) {
            hn.next = list[hash];
            list[hash] = hn;
        }
    }

    /**
     * Lookup the key in the hashtable.<br/>
     * Since collisions are handled by a linked list,
     * this method runs in <i>O(n)</i> where <i>n</i>
     * is the number of keys in collision.
     * 
     * @param key
     *            Key to loop up.
     * @return The object associated with the key or null.
     */
    public Object get(int key) {
        int hash = key % list.length;
        synchronized (list) {
            for (HashNode i = list[hash]; i != null; i = i.next) {
                if (i.key == key) return i.data;
            }
        }
        return null;
    }

    public void clear() {
    }

    /**
     * Remove the key association from the hash table.
     * Since collisions are handled by a linked list,
     * this method runs in <i>O(n)</i> where <i>n</i>
     * is the number of keys in collision.
     * 
     * @param key
     *            The key to remove.
     */
    public void remove(int key) {
        int hash = key % list.length;
        synchronized (list) {
            HashNode prev = null;
            for (HashNode i = list[hash]; i != null; prev = i, i = i.next) {
                if (i.key == key) {
                    if (prev == null) {
                        HashNode tmp = i;
                        list[hash] = i.next;
                        push(tmp);
                    } else {
                        HashNode tmp = i;
                        prev.next = i.next;
                        push(tmp);
                    }
                }
            }
        }
    }
}