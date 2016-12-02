package asn6;

import java.util.ArrayList;

// This is our code.//Michael O'Brien, Sydney Pugh
//CS312

// Michael O'Brien, Sydney Pugh
// CS312

/**
 * Hashtable using chaining
 */
public class CHashtable
{
	private ArrayList<E>[] table;
	private double loadThreshold;
	private int numKeys;
	private int probes;
	
	/**
	 * Parameterized CHashtable constructor
	 * @param size - size 
	 * @param load - load threshold
	 */
	public CHashtable(int size, double load)
	{
		@SuppressWarnings("unchecked")
		ArrayList<E>[] var = (ArrayList<E>[]) new ArrayList[size];
		table = var;
		
		for(int i=0; i<size; i++)
			table[i] = new ArrayList<E>();
		
		loadThreshold = load;
		probes = 0;
	}
	
	/**
	 * Method to add an object into the hash table
	 * @param in - object to be inserted
	 */
	public void add(E in)
	{
		int index = in.value.hashCode() % table.length;
		
		if (index < 0)
			index += table.length;
		
		if(!table[index].contains(in))
		{
		  table[index].add(in);
		  numKeys++;
		}
		
		if(numKeys > (loadThreshold * table.length))
			rehash();
	}
	
	/**
	 * Method to remove an object from the hash table
	 * @param it - object to be removed
	 */
	public void remove(E it)
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
