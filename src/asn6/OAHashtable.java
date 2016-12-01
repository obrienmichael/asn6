package asn6;

// This is our code.
// Michael O'Brien, Sydney Pugh
// CS312

import java.util.Random;
import java.util.Enumeration; //allows for methods: boolean hasMoreElements() and <E> nextElement
import java.util.Iterator;    //maybe this is a better option that Enumerator

/**
 * Hashtable using open addressing
 */
public class OAHashtable<K,V> 
{
	private Entry<K,V>[] table;
	private double loadThreshold;
	private int numKeys;
	private int numDeletes;
	private final Entry<K,V> DELETED = new Entry<K,V>(null, null);

	/**
	 * Parameterized OAHashtable constructor
	 * @param size - size 
	 * @param load - load threshold
	 */
	@SuppressWarnings("unchecked")
	public OAHashtable(int size, double load)
	{
		table = new Entry[size];
		loadThreshold = load;
	}
	
	/**
	 * Method to add an object into the hash table
	 * @param it - object to be inserted
	 */
	public void add(K nkey, V nvalue)
	{
		int index = nkey.hashCode() % table.length;

		if(index < 0)
			index += table.length;

		while(table[index] != null && table[index].key.equals(nkey))
		{
			index++;
			if(index >= table.length)
				index = 0;
		}

		if(table[index] == null)
		{		
			table[index] = new Entry<K, V>(nkey, nvalue);
			numKeys++;

			double load = (double) (numKeys + numDeletes) / table.length;
			if(load > loadThreshold)
				rehash();
		}
	}
	
	/*private Entry<K, V> Entry(K nkey, V nvalue) {
		// TODO Auto-generated method stub
		return null;
	}*/

	/**
	 * Method to remove an object from the hash table
	 * @param it - object to be removed
	 */
	//need to use find/locate method within remove method?
	public void remove(Entry<K,V> it)
	{
		
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
	}
	
//---------------------------------------------------------------------	
	//textbook version for find(Object key)
	private int find(Entry<E> key)
	{
		int index = key.hashCode() % table.length;
		
		if (index < 0)
			index += table.length;
		
		while ((table[index] != null) && (!key.equals(table[index].getKey())))
		{
			index++;
			if(index >=  table.length)
				index = 0;
		}
		return index;
	}*/
	
//---------------------------------------------------------------------	
	
	/**
	 * Method to rehash the table, doubling the size of the table
	 * and transferring only non-null and non-deleted elements
	 * into new array
	 */
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		Entry<K,V>[] oldTable = table;
		table = new Entry[oldTable.length * 2];
		numKeys = 0;
		numDeletes = 0;

		for(Entry<K,V> element : oldTable)
		{
			if(element != null && element != DELETED)
				add(element.key, element.value);
		}
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
		//Entry<Integer, Integer> n = new Entry(3,3);
		//System.out.println(n);
		
		
		//Random r = new Random(42);
		OAHashtable<Integer,Integer> ht = new OAHashtable<Integer,Integer>(5, 0.1);

		/*for(int i=0; i<20; i++)
	    {
	      int x = Math.abs(r.nextInt()) % 1000000;
	      ht.add(x);
	      System.out.print(" " + x);
	    }*/
		
		ht.dumpTable();

		ht.add(3,3);
		ht.add(4,4);
		ht.add(5,5);

		ht.dumpTable();
	}
}
