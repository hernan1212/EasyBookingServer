package server;
import java.util.ArrayList;
import java.util.List;

import DAO.JDO.DAO;
import gateway.GatewayEB;
import LN.*;
public class AP_Aero 
{
	private Airport a=new Airport(1,"Barcelona");
	private Airport b=new Airport(2,"Mexico");
	private ArrayList<Flight> vuelos = new ArrayList<Flight>();
	private GatewayEB G=new GatewayEB();
	private DAO dao=new DAO();
	public AP_Aero() 
	{
		 vuelos.add(new Flight(123,"01/02/2018","02/02/2018",500,350,a,b));
		 vuelos.add(new Flight(124,"01/02/2018","02/02/2018",200,600,b,a));
	}

	public synchronized List<Flight> BuscarVuelos(String origen, String destination, String fecha, int passenger) 
	{	
		List<Flight> v = new ArrayList<Flight>();
		
		//v=G.BuscarVuelo(origen, destination, fecha, passenger, false);
		v=G.BuscarVuelo(origen, destination, fecha, passenger, true);
		dao.GuardarVuelos(v);
		return v;
	}

	public synchronized boolean ReservarVuelo(int cod_vuelo, String usuario, int pasajeros) 
	{
		Flight f;
		String CodeReserva;
		if((f=dao.GetVuelo(cod_vuelo))!=null)
		{
			CodeReserva=G.ReservarVuelo(cod_vuelo, usuario, pasajeros, f.isAero());
			if(dao.ReservarVuelo(new Reservation(CodeReserva, pasajeros, null, dao.GetUser(usuario), f)))
			{
				return true;
			}
			
		}
		return false;
	}
}
