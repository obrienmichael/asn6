package asn6;

//This is our code
//Michael O'Brien, Sydney Pugh
//CS312

/**
 *
 */
public class Slinging implements Comparable<Slinging>
{
  private int tatersUsed;

  
  /**
   * 
   * @param cnt - number of ___
   */
  public Slinging(int cnt)
  {
    tatersUsed = cnt;
  }

  
  /**
   * Method to compare 2 Slinging objects
   * @return -1 if this slinging is less than the given
   * 			0  if they are equal
   * 			1 if this slinging is greater than the given
   */
  @Override
  public int compareTo(Slinging s)
  {
    return tatersUsed < s.tatersUsed ? -1 : tatersUsed == s.tatersUsed ? 0 : 1;
  }

  
  /**
   * Method to generate the hashcode
   * @return hashcode (which is tatersUsed)
   */
  @Override
  public int hashCode()
  {
    return tatersUsed;
  }

  
  /**
   * Method to generate a string representation of 
   * a Slinging object
   * @return string of tatersUsed
   */
  @Override
  public String toString()
  {
    return "" + tatersUsed;
  }

  
  /**
   * Method to determine if this slinging and the one given
   * are equal
   * @return true if they are equal
   * 			otherwise false
   */
  @Override
  public boolean equals(Object o) 
  {
    // self check
    if (this == o)
      return true;

    // null check
    if (o == null)
      return false;

    // type check and cast
    if (getClass() != o.getClass())
      return false;

    Slinging it = (Slinging) o;
    // field comparison
      return tatersUsed == it.tatersUsed;
  }
}
