package asn6;

//This is our code.
// Michael O'Brien, Sydney Pugh
// CS312

import java.util.Random;

/**
 * Hashtable using open addressing
 */
public class OAHashtable 
{
	private Integer[] table;
	private double loadThreshold;
	private int numKeys;
	private int numDeletes;
	private final int DELETED = -1;
	private final int UNUSED = -1;


	/**
	 * Parameterized OAHashtable constructor
	 * @param size - size 
	 * @param load - load threshold
	 */
	public OAHashtable(int size, double load)
	{
		table = new Integer[size];
		loadThreshold = load;
	}

	
	/**
	 * Method to add an object into the hash table
	 * @param it - object to be inserted
	 */
	public void add(Integer it)
	{
		int index = it.hashCode() % table.length;

		if(index < 0)
			index += table.length;

		while(table[index] != null && table[index] != it)
		{
			index++;
			if(index >= table.length)
				index = 0;
		}

		if(table[index] == null)
		{
			table[index] = it;
			numKeys++;

			double load = (double) (numKeys + numDeletes) / table.length;
			if(load > loadThreshold)
				rehash();
		}
	}
	
	
	/**
	 * Method to remove an object from the hash table
	 * @param it - object to be removed
	 */
	public void remove(Integer it)
	{
	}
	
	
	/**
	 * Method to rehash the table, doubling the size of the table
	 * and transferring only non-null and non-deleted elements
	 * into new array
	 */
	private void rehash()
	{
		Integer[] oldTable = table;
		table = new Integer[oldTable.length * 2];
		numKeys = 0;
		numDeletes = 0;

		for(Integer integer : oldTable)
		{
			if(integer != null && integer != DELETED)
				add(integer);
		}
	}


	/**
	 * Method to print out contents of the hash table
	 */
	public void dumpTable()
	{
		System.out.print("Table content: ");
		for(int i=0; i<table.length; i++)
			System.out.print(" " + table[i]);
		System.out.println();
	}

	
	public static void main(String [] args)
	{
		Random r = new Random(42);
		OAHashtable ht = new OAHashtable(5, 0.1);

		/*for(int i=0; i<20; i++)
	    {
	      int x = Math.abs(r.nextInt()) % 1000000;
	      ht.add(x);
	      System.out.print(" " + x);
	    }*/
		ht.dumpTable();

		ht.add(3);
		ht.add(4);
		ht.add(5);

		ht.dumpTable();
	}
}
