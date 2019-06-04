package LN;

import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reservation {

	@PrimaryKey
	String id;
	
	int num_seats;
	@Column(name="code_res")
	Payment Pay;
	@Column(name="email_res")
	User u;
	@Column(name="id_res")
	Flight f;
	public Reservation(String id, int num_seats, Payment pay, User u, Flight f) {
		super();
		this.id = id;
		this.num_seats = num_seats;
		Pay = pay;
		this.u = u;
		this.f = f;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum_seats() {
		return num_seats;
	}
	public void setNum_seats(int num_seats) {
		this.num_seats = num_seats;
	}
	public Payment getPay() {
		return Pay;
	}
	public void setPay(Payment pay) {
		Pay = pay;
	}
	public User getU() {
		return u;
	}
	public void setU(User u) {
		this.u = u;
	}
	public Flight getF() {
		return f;
	}
	public void setF(Flight f) {
		this.f = f;
	}
	
	
}
