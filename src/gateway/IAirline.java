package gateway;

import java.util.List;

import LN.Flight;

public interface IAirline {
	public List<Flight> BuscarVuelos(String origen, String destination, String fecha, int passenger);
	public List<Flight> BuscarTodoVuelos();
	public String ReservaVuelo(int cod_vuelo, String usuario, int pasajeros);
}
