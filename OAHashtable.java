package asn6;

//This is our code.
// Michael O'Brien, Sydney Pugh
// CS312

//import java.util.Random;
import java.util.Enumeration; //allows for methods: boolean hasMoreElements() and <E> nextElement
import java.util.Iterator;    //maybe this is a better option that Enumerator

/**
 * Hashtable using open addressing
 */
//sydney git push test
public class OAHashtable<E> 
{
	private Entry<E>[] table;
	private double loadThreshold;
	private int numKeys;
	private int numDeletes;
	private final Entry<E> DELETED = new Entry<E>(null);
	//private final int UNUSED = -1;

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
	public void add(Entry<E> it)
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
	//need to use find/locate method within remove method?
	public void remove(Entry<E> it)
	{
	}
	
//---------------------------------------------------------------------		
	
	//find method from website javamadesoeasy
	//does not require a getter method but requires a next method to iterate through
	public int locate(Entry<E> key)
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
	}
	
//---------------------------------------------------------------------	
	
	/**
	 * Method to rehash the table, doubling the size of the table
	 * and transferring only non-null and non-deleted elements
	 * into new array
	 */
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		Entry<E>[] oldTable = table;
		table = new Entry[oldTable.length * 2];
		numKeys = 0;
		numDeletes = 0;

		for(Entry<E> element : oldTable)
		{
			if(element != null && element != DELETED)
				add(element);
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
		//Random r = new Random(42);
		OAHashtable<Integer> ht = new OAHashtable<Integer>(5, 0.1);

		/*for(int i=0; i<20; i++)
	    {
	      int x = Math.abs(r.nextInt()) % 1000000;
	      ht.add(x);
	      System.out.print(" " + x);
	    }*/
		
		ht.dumpTable();

		ht.add(new Entry<Integer>(3));
		ht.add(new Entry<Integer>(4));
		ht.add(new Entry<Integer>(5));

		ht.dumpTable();
	}
}
