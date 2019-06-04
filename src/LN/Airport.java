package LN;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Airport {

	@PrimaryKey
    int code = 0;
	String place;
	
	@Element(column="flight_number")
	List<Flight> departure_flights = new ArrayList<>();
	
	@Element(column="flight_number")
	List<Flight> arrival_flights = new ArrayList<>();
	
	@Element(column="email")
	List<User> usuarios = new ArrayList<>();

	
	public Airport(int code, String place) {
		this.code = code;
		this.place = place;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}
