package asn6;

//This is our code.
//Michael O'Brien, Sydney Pugh
//CS312

/**
 * Class to represent a generic object
 */
public class E
{
	protected Object value;
	
	
	public E(Object v)
	{
		value = v;
	}
	
	
	/**
	 * Method to check whether two values 
	 */
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
	 * Method to generate string representation of Entry
	 */
	@Override
	public String toString()
	{
		return "Value of E object is " + value.toString();
	}
}