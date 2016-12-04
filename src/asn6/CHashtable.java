package asn6;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

// This is our code
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
	private int numRehashes;
	private double numProbes;
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
		numProbes = 0.0;
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

		if (index < 0)
			index += table.length;
		
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
	 * @param subject - object to be found in hashtable
	 * @return true if in the hashtable
	 * 			otherwise false
	 */
	public boolean find(E subject)
	{
		int index = subject.value.hashCode() % table.length;
		
		if (index < 0)
			index += table.length;
		
		if(table[index].contains(subject))
			return true;
		
		return false;
	}
	
	/**
	 * Method to count the number of probes it takes to find a
	 * given object in the hashtable
	 * @param subject - object searching for
	 */
	public void countProbes(E subject)
	{
		boolean result = find(subject);
		
		if(result)
		{			
			int index = subject.value.hashCode() % table.length;
			
			if (index < 0)
				index += table.length;
			
			for(E entry : table[index])
			{
				if(entry.equals(subject))
				{
					probes++;
					return;
				}
				probes++;
			}
		}
	}
	
	
	/**
	 * Method to print the number of probes and the
	 * number of rehashes
	 */
	public void displayData()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Number of probes: " + df.format((double) probes / numKeys));
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
		ArrayList<E> list = new ArrayList<E>(); 
		
		for(int i=0; i<100000; i++)
		{
			int x = Math.abs(r.nextInt()) % 100000;
			ht.add(new E(x));
			list.add(new E(x));
			//System.out.println(x);
		}
		
		for(E in : list)
		{
			ht.countProbes(in);
		}
		
		//ht.remove(new E(44799));
		ht.dumpTable();
		ht.displayData();
	}
}
