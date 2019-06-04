package gateway;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import LN.Flight;

public class GatewayEB implements IGateway
{
	static String IP="127.0.0.1";
	static String PORT="";
	static String SERVICE="";

	@Override
	public String PagoReserva(String user, int precio) 
	{
		String b="";
		try {
			PORT = "1001";
			SERVICE = "Paypal";
			String name = "//" + IP + ":" + PORT + "/" + SERVICE;
			IPayment stubPayment =(IPayment) java.rmi.Naming.lookup(name);
			b=stubPayment.PagoReserva(user,precio);
			
		} catch (MalformedURLException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean ComprobarUsuario(String user, String contrasena) 
	{
		boolean b=false;
		try {
			PORT = "1002";
			SERVICE = "Google";
			String name = "//" + IP + ":" + PORT + "/" + SERVICE;
			IAuthentification stubAuthentification =(IAuthentification) java.rmi.Naming.lookup(name);
			b=stubAuthentification.ComprobarUsuario(user,contrasena);
			
		} catch (MalformedURLException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public List<Flight> BuscarVuelo(String origen, String destination, String date, int passenger, boolean opcion) {
		List<Flight> vuelos=new ArrayList<Flight>();
		try {
			PORT = "1003";
			SERVICE = "Iberia";
			String name = "//" + IP + ":" + PORT + "/" + SERVICE;
			IAirline stubAirline =(IAirline) java.rmi.Naming.lookup(name);
			if(opcion)vuelos=stubAirline.BuscarVuelos(origen,destination,date,passenger);
			else vuelos=stubAirline.BuscarTodoVuelos();
			
			PORT = "1004";
			SERVICE = "AmericanAirlines";
			name = "//" + IP + ":" + PORT + "/" + SERVICE;
			stubAirline =(IAirline) java.rmi.Naming.lookup(name);
			if(opcion) vuelos.addAll(stubAirline.BuscarVuelos(origen,destination,date,passenger));
			else vuelos=stubAirline.BuscarTodoVuelos();
			
		} catch (MalformedURLException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		return vuelos;
	}

	@Override
	public String ReservarVuelo(int cod_vuelo, String usuario, int pasajeros, boolean aerolinea) {
		String b="";
		try {
			if(aerolinea)
			{
			PORT = "1003";
			SERVICE = "Iberia";
			}
			else
			{
			PORT = "1004";
			SERVICE = "AmericanAirlines";
			}
			String name = "//" + IP + ":" + PORT + "/" + SERVICE;
			IAirline stubAirline =(IAirline) java.rmi.Naming.lookup(name);
			b=stubAirline.ReservaVuelo(cod_vuelo, usuario, pasajeros);
			
		} catch (MalformedURLException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (RemoteException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}
}
