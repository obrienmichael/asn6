package asn6;

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
	public boolean equals(E entry)
	{
		return (entry.value == this.value);
	}
	
	/**
	 * Method to generate string representation of Entry
	 */
	@Override
	public String toString()
	{
		return "Value of E oject is " + value.toString();
	}
}