package warehouse;

import java.util.Comparator;
import java.util.List;

public class Request implements Comparator<Request>, Comparable<Request>{

  //private static int count = 0;
  private int id;
  private List<Integer> skus;
  private String status;
  
  /**
   * Creates a Request for getting and processing fascias.
   * @param skus the fascias to be found and processed.
   */
  public Request(int id, List<Integer> skus){
    this.skus = skus;
    //count++;
    //id = count;
    this.id = id;
  }
  
  /*
   * gets the identification of the request.
   */
  public int getId(){
    return id;
  }
  /**
   * gets the list of fascias that are ordered. 
   */
  public List<Integer> getRequest(){
    List<Integer> skus = this.skus.subList(0, 8);
    return skus;
  }
 
  public void setStatus(String status){
    this.status = status;
  }
  
  public String getStatus(){
    return status;
  }

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
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
	Request other = (Request) obj;
	if (id != other.id)
		return false;
	return true;
}
public int compare(Request o1, Request o2) {
	// TODO Auto-generated method stub
	return o1.getId()-o2.getId();
}

  
  public int compareTo(Request o) {
    return this.id - o.getId();
  }
}
