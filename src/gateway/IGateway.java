package gateway;

import java.util.List;

import LN.Flight;

public interface IGateway 
{
	public String PagoReserva(String user, int precio);
	public boolean ComprobarUsuario(String user, String contrasena);
	public List<Flight> BuscarVuelo(String origen, String destination, String date, int passenger, boolean opcion);
	public String ReservarVuelo(int cod_vuelo, String usuario, int pasajeros, boolean aerolinea);
}
