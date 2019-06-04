package LN;

import java.util.Date;

import javax.jdo.annotations.ForeignKey;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation_Passenger {
	//ESTA VA A SER LA CLASE ASIENTO SI LA HACEMOS.
	@PrimaryKey
	Date reservation_date = new Date();
	@PrimaryKey
	int num_seats = 0;
	String code;
	int row = 0;
	int column = 0;
	

	public Reservation_Passenger(Date reservation_date, int num_seats, String code, int row, int column) {
		super();
		this.reservation_date = reservation_date;
		this.num_seats = num_seats;
		this.code = code;
		this.row = row;
		this.column = column;
	}
	
	
	
}
