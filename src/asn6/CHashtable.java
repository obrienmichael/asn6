package asn6;

import java.util.ArrayList;
import java.util.Random;

// This is our code
//Michael O'Brien, Sydney Pugh
//CS312

/**
 * Hashtable using chaining
 */
public class CHashtable
{
	private ArrayList<E>[] table;
	private double loadThreshold;
	private int numKeys;
	private int numRehashes;
	private int numProbes;
	
	
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
		numProbes = 0;
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
		
		for(E elem : table[index])
		{
			if(elem.equals(in))
				return;
		}
		
		table[index].add(in);
		numKeys++;
		
		if(numKeys > (loadThreshold * table.length))
			rehash();
	}
	
	
	/**
	 * Method to remove an object from the hash table
	 * @param it - object to be removed
	 */
	public void remove(E it)
	{
		int index = it.value.hashCode() % table.length;

		if(table[index].contains(it))
			table[index].remove(it);
	}
	
	
	/**
	 * Method to rehash the table, doubling the size of the table
	 * and transferring only non-null and non-deleted elements
	 * into new array
	 */
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		numRehashes++;
		
		ArrayList<E>[] oldTable = table;
		table = (ArrayList<E>[]) new ArrayList[2 * oldTable.length + 1];
		
		for(int i=0; i<table.length; i++)
			table[i] = new ArrayList<E>();
		
		numKeys = 0;

		for(ArrayList<E> list : oldTable)
		{
			for(E elem : list)
			  add(elem);
		}
	}
	
	
	/**
	 * Method to find the index of a given object in the hashtable
	 * @param subject
	 * @return 
	 */
	private int find(E subject)
	{
		int index = subject.value.hashCode() % table.length;
		
		if (index < 0)
			index += table.length;
		
		while ((table[index] != null) && (!subject.value.equals(table[index])))
		{
			index++;
			if(index >=  table.length)
				index = 0;
		}
		return index;
	}
	
	
	/**
	 * Method to print the number of probes and the
	 * number of rehashes
	 */
	public void displayData()
	{
		System.out.println("Number of probes: " + numProbes);
		System.out.println("Number of rehashes: " + numRehashes);
	}

	
	/**
	 * Method to print out contents of the hash table
	 */
	public void dumpTable()
	{
		System.out.println("Table contents: ");
		for(ArrayList<E> list : table)
			System.out.println(list + " ");
	}
	
	
	public static void main(String[] args)
	{
		Random r = new Random(42);
		CHashtable ht = new CHashtable(4, 0.1);
		
		for(int i=0; i<100; i++)
		{
			int x = Math.abs(r.nextInt()) % 100000;
			ht.add(new E(x));
			System.out.println(x);
		}
		
		ht.remove(new E(44799));
		ht.dumpTable();
		ht.displayData();
	}
}
