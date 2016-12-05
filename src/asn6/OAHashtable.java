package asn6;

import java.util.Random;
import java.text.DecimalFormat;
import java.util.ArrayList;

// This is our code
// Michael O'Brien, Sydney Pugh
// CS312

/**
 * Hashtable using open addressing
 */
public class OAHashtable
{
	private E[] table;
	private double loadThreshold;
	private int numKeys;
	private int numDeletes;
	private int probes;
	private int finds;
	private int numRehashes;
	private final E DELETED;

	
	/**
	 * Parameterized OAHashtable constructor
	 * @param size - size 
	 * @param load - load threshold
	 */
	public OAHashtable(int size, double load, E deleted)
	{
		table = new E[size];
		loadThreshold = load;
		DELETED = deleted;
		numKeys = 0;
		numDeletes = 0;
		probes = 0;
		finds = 0;
		numRehashes = 0;
	}
	
	
	/**
	 * Method to add an object into the hash table
	 * @param in - object to be inserted
	 */
	public void add(E in)
	{
		int index = find(in);

		if(table[index] == null)
		{		
			table[index] = in;
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
	public void remove(E it)
	{
		int index = it.value.hashCode() % table.length;
		
		if (index < 0)
			index += table.length;
		
		if(table[index] == null)
			return;
		
		while(table[index] != null)
		{
			if(table[index].equals(it))
			{
				table[index] = DELETED;
				numDeletes++;
				numKeys--;
			}
			
			index++;
			if(index >=  table.length)
				index = 0;
		}
	}
	
	
	/**
	 * Method to find the index of a given object in the hashtable
	 * or the first empty spot
	 * @param subject
	 * @return 
	 */
	private int find(E subject)
	{
		int index = subject.value.hashCode() % table.length;
		
		if (index < 0)
			index += table.length;
		
		while ((table[index] != null) && (!subject.equals(table[index])))
		{
			index++;
			if(index >=  table.length)
				index = 0;
		}
		return index;
	}
		
	
	/**
	 * Method to rehash the table, doubling the size of the table
	 * and transferring only non-null and non-deleted elements
	 * into new array
	 */
	private void rehash()
	{
		numRehashes++;
		
		E[] oldTable = table;
		table = new E[2 * oldTable.length + 1];
		numKeys = 0;
		numDeletes = 0;

		for(E element : oldTable)
		{
			if(element != null && element != DELETED)
				add(element);
		}
	}

	
	/**
	 * Method to check if an object is in the hashtable
	 * @param subject - object searched for
	 * @return true if it is in hashtable
	 * 			otherwise false
	 */
	public boolean contains(E subject)
	{
		int index = find(subject);

		while(table[index] != null)
		{
			if(table[index].equals(subject))
				return true;
			
			index++;
			if(index >= table.length)
				index = 0;
		}
		
		return false;
	}
	
	/**
	 * Method to count the number of probes it takes to find a
	 * given object in the hashtable
	 * @param subject - object searching for
	 */
	public void countProbes(E subject)
	{
		boolean result = contains(subject);

		if(result)
		{			
			int index = subject.value.hashCode() % table.length;
			
			if (index < 0)
				index += table.length;
			
			while(table[index] != null)
			{
				if(table[index].equals(subject))
				{
					probes++;
					finds++;
					return;
				}
				
				probes++;
				index++;
				if(index >=  table.length)
					index = 0;
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
		System.out.println("Number of probes: " + df.format((double) probes / finds));
		System.out.println("Number of rehashes: " + numRehashes);
	}
	
	
	/**
	 * Method to print out contents of the hash table
	 */
	public void dumpTable()
	{
		System.out.println("Table content: ");
		for(int i=0; i<table.length; i++)
			System.out.println(" " + table[i]);
		System.out.println();
	}
	
	
	public static void main(String [] args)
	{
		Random r = new Random(42);
		E deleted = new E(-1);
		OAHashtable ht = new OAHashtable(4, 0.1, deleted);
		ArrayList<E> list = new ArrayList<E>(); 
		
		for(int i=0; i<100000; i++)
	    {
	      int x = Math.abs(r.nextInt()) % 1000000;
	      ht.add(new E(x));
		  list.add(new E(x));
	    }
		
		for(E in : list)
			ht.countProbes(in);
	
		ht.displayData();
		

		
		// K=20% Deletion
		System.out.println("\n \n \n \n");
		Random s = new Random();
		OAHashtable ht2 = new OAHashtable(4, 0.1, deleted);
		ArrayList<E> list2 = new ArrayList<E>(); 

		for(int i=0; i<100000; i++)
		{
			int x = Math.abs(r.nextInt()) % 100000;
			ht2.add(new E(x));
			list2.add(new E(x));

			if(s.nextInt(101) <= 20)
			{
				E item = list2.get(new Random().nextInt(list2.size()));	
				ht2.remove(item);
				list2.remove(item);
			}
		}

		for(E in : list2)
			ht2.countProbes(in);

		ht2.displayData();


		// K=80% Deletion
		System.out.println("\n \n \n \n");
		Random y = new Random();
		OAHashtable ht3 = new OAHashtable(4, 0.1, deleted);
		ArrayList<E> list3 = new ArrayList<E>(); 


		for(int i=0; i<100000; i++)
		{
			int x = Math.abs(r.nextInt()) % 100000;
			ht3.add(new E(x));
			list3.add(new E(x));

			if(y.nextInt(101) <= 80)
			{
				E item = list3.get(new Random().nextInt(list3.size()));	
				ht3.remove(item);
				list3.remove(item);
			}
		}

		for(E in : list3)
			ht3.countProbes(in);

		ht3.displayData();
	}
}
