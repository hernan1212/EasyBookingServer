package LN;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Passenger {
	
	@PrimaryKey
	int identificator = 0;
	String name = null;
	int age = 0;
	
	public Passenger(int identificator, String name, int age) {
		super();
		this.identificator = identificator;
		this.name = name;
		this.age = age;
	}
}
