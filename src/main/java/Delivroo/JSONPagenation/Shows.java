package Delivroo.JSONPagenation;

import java.util.Comparator;

public class Shows implements Comparable<Shows>  {
	
	String name;
	double rating;
	
	public Shows(String name,
	double rating)
	{
		this.name=name;
		this.rating=rating;
	}

	@Override
	public int compareTo(Shows o1) {
		// TODO Auto-generated method stub
		if(this.rating<o1.rating)
		return 1;
		else if(this.rating>o1.rating)
			return -1;
		else 
			return 0;
	}

	@Override
	public String toString() {
		return "Shows [name=" + name + ", rating=" + rating + "]";
	}



}
