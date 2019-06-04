package assemble;
public class FlightDTO {
	
    int flight_number;
	String departure_date;
	String arrival_date;
	String departure_airport;
	String arrival_airport;
	boolean b;
	int precio;
	
	public FlightDTO(int flight_number, String departure_date, String arrival_date, String departure_airport,
			String arrival_airport, int precio) {
		super();
		this.flight_number = flight_number;
		this.departure_date = departure_date;
		this.arrival_date = arrival_date;
		this.departure_airport = departure_airport;
		this.arrival_airport = arrival_airport;
		this.precio = precio;
	}

	public int getFlight_number() {
		return flight_number;
	}

	public String getDeparture_date() {
		return departure_date;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public String getDeparture_airport() {
		return departure_airport;
	}

	public String getArrival_airport() {
		return arrival_airport;
	}

	public int getPrecio() {
		return precio;
	}
	
	

}
