package DAO.JDO;

import java.util.List;

import LN.Flight;
import LN.Payment;
import LN.Reservation;
import LN.User;

public interface IDAO 
{
	public boolean GuardarVuelos(List<Flight> vuelos);
	public boolean GuardarPago(Payment pago);
	public boolean GuardarUsuario(User Usuario);
	public boolean ComprobarUsuario(String nombre, String contrasena);
	public boolean ReservarVuelo(Reservation reserva);
	public Flight GetVuelo(int cod_vuelo);
	public User GetUser(String correo);
}
