package warehouse;

public class Person{
  private String name;
  private int requestId;
  
  /**
   * Creates a new person.
   * @param name Is the name of the person.
   */
  public Person(String name){
    this.name = name;
  }
  
  /**
   * Sets the identification for the person.
   * @param id The id of the person.
   */
  public void setRequestId(int id){
    requestId = id;
  }
  
  /**
   * Gets the persons id. 
   * @return the requested id.
   */
  public int getRequestId(){
    return requestId;
  }
  
  /**
   * gets the name of the person.
   * @return the name of the person.
   */
  public String getName(){
	  return name;
  }

  @Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Person other = (Person) obj;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
	}
}



