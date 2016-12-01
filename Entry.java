package asn6;

//This is our code.
//Michael O'Brien, Sydney Pugh
//CS312

/**
 * Generic hashtable element type
 */
public class Entry<E>
{
	private E value;
	
	/**
	 * Parameterized Entry constructor
	 * @param v - value of entry
	 */
	public Entry(E v)	//should we give the constructor two parameters? value and key?
	{
		value = v;
	}
	
	/**
	 * Method to generate string representation of Entry
	 */
	@Override
	public String toString()
	{
		return value.toString();
	}
}
