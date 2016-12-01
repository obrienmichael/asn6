package asn6;

//This is our code.
//Michael O'Brien, Sydney Pugh
//CS312

import java.util.LinkedList;


/**
 * Hashtable using chaining
 */
public class CHashtable
{
	private LinkedList<Integer>[] table;
	private double loadThreshold;
	private int numKeys;
	private int numDeletes;
	private final int DELETED = -1;
	private final int UNUSED = -1;
	
	/**
	 * Parameterized CHashtable constructor
	 * @param size - size 
	 * @param load - load threshold
	 */
	public CHashtable(int size, double load)
	{
		table = new LinkedList[size];
		loadThreshold = load;
	}
	
	/**
	 * Method to add an object into the hash table
	 * @param it - object to be inserted
	 */
	public void add(Integer it)
	{
		
	}
	
	/**
	 * Method to remove an object from the hash table
	 * @param it - object to be removed
	 */
	public void remove(Integer it)
	{
		
	}
	
	public void locateInt(int id)
	{
		
	}
	
	/**
	 * Method to rehash the table, doubling the size of the table
	 * and transferring only non-null and non-deleted elements
	 * into new array
	 */
	private void rehash()
	{
		
	}
	
	/**
	 * Method to print out contents of the hash table
	 */
	public void dumpTable()
	{
		
	}
	
	public static void main(String[] args)
	{
		
	}
}
