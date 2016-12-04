package asn6;

import java.util.Random;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration; //allows for methods: boolean hasMoreElements() and <E> nextElement
import java.util.Iterator;    //maybe this is a better option that Enumerator

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
	private double numProbes;
	private int numFinds;
	private int numRehashes;
	private final E DELETED;

	
	/**
	 * Parameterized OAHashtable constructor
	 * @param size - size 
	 * @param load - load threshold
	 */
	public OAHashtable(int size, double load, E deleted)
	{
		@SuppressWarnings("unchecked")
		//Slinging[] t = (Slinging[]) new Comparable[size];
		E[] t = (E[]) new Comparable[size];
		table = t;
		loadThreshold = load;
		DELETED = deleted;
		numProbes = 0;
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
	//need to use find/locate method within remove method?
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
	
	
//---------------------------------------------------------------------		
	
	//find method from website javamadesoeasy
	//does not require a getter method but requires a next method to iterate through
	/*public int locate(Entry<K,V> key)
	{
		int hash = hash(key);
		//Enumeration en = table.keys(); --> temp = temp.nextElement(); ?
		
		if (table[hash] == null)
			return null;
		else
		{
			Entry<E> temp = table[hash];
			while(temp != null)
			{
				if(temp.key.equals(key))
					return temp.value;
				temp = temp.next;
			}
		return null;	
		}
	}*/
	
//---------------------------------------------------------------------	
	//textbook version for find(Object key)
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
	 * Method to print the number of probes and the
	 * number of rehashes
	 */
	public void displayData()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		System.out.println("Number of probes: " + df.format(numProbes));
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
		
		for(int i=0; i<100; i++)
	    {
	      int x = Math.abs(r.nextInt()) % 1000000;
	      ht.add(new E(x));
		  list.add(new E(x));
	      //System.out.print(" " + x);
	    }
		
		//ht.remove(new E());
		ht.dumpTable();
		ht.displayData();
	}
}
