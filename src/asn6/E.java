package asn6;

//This is our code
//Michael O'Brien, Sydney Pugh
//CS312

/**
 * Class to represent a generic object
 */
public class E implements Comparable<E>
{
	protected Object value;
	
	
	public E(Object v)
	{
		value = v;
	}
	
	
	/**
	 * Method to check whether two values 
	 */
	@Override
	public boolean equals(Object entry)
	{
		if(this == entry)
			return true;
		
		if(entry == null)
			return false;
		
		if(getClass() != entry.getClass())
			return false;
		
		E obj = (E) entry;
		return (obj.value.equals(this.value));
	}
	
	
	/**
	 * Method to compare two E objects
	 * @return -1 for now
	 */
	@Override
	public int compareTo(E entry)
	{
		return -1;
	}
	
	
	/**
	 * Method to generate string representation of Entry
	 */
	@Override
	public String toString()
	{
		return "Value of E object is " + value.toString();
	}
}