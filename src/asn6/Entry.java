package asn6;

//This is our code.
//Michael O'Brien, Sydney Pugh
//CS312

/**
 * Generic hashtable element type
 */

public class Entry<K,V>
{
	protected K key;		// Given
	protected V value;		// Deterministic data
	
	/**
	 * Parameterized Entry constructor
	 * @param v - value of entry
	 */
	public Entry(K key, V value)	//should we give the constructor two parameters? value and key?
	{
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Method to check whether two values 
	 */
	public boolean equals(Entry<K,V> entry)
	{
		return (entry.key == this.key);
	}
	
	/**
	 * Method to generate string representation of Entry
	 */
	@Override
	public String toString()
	{
		return "Key is " + key.toString() + " with value of " + value.toString();
	}
}
