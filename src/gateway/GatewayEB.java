package gateway;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
			System.out.println(1);
			PORT = "1001";
			SERVICE = "PayPal";
			String name = "//" + IP + ":" + PORT + "/" + SERVICE;
			System.out.println(1);
			Registry reg=LocateRegistry.getRegistry(1001);
			IPayment stubPayment =(IPayment) reg.lookup(name);
			System.out.println(2);
			b=stubPayment.PagoReserva(user,precio);
			System.out.println(3);
		} catch (RemoteException e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.err.println("- Exception running gatewaaaaay: " + e.getMessage());
			e.printStackTrace();
		}
		return b;
	}

	@Override
	public boolean ComprobarUsuario(String user, String contrasena) 
	{
		boolean b=false;
		try {
			System.out.println(1);
			PORT = "1002";
			SERVICE = "Google";
			String name = "//" + IP + ":" + PORT + "/" + SERVICE;
			Registry reg=LocateRegistry.getRegistry(1002);
			System.out.println(name);
			
			IAuthentification stubAuthentification =(IAuthentification) reg.lookup(name);
			System.out.println(3);
			b=stubAuthentification.ComprobarUsuario(user,contrasena);
			System.out.println(4);
			
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
			Registry reg=LocateRegistry.getRegistry(1003);
			IAirline stubAirline =(IAirline) reg.lookup(name);
			if(opcion)vuelos=stubAirline.BuscarVuelos(origen,destination,date,passenger);
			else vuelos=stubAirline.BuscarTodoVuelos();
			
			PORT = "1004";
			SERVICE = "AmericanAirlines";
			name = "//" + IP + ":" + PORT + "/" + SERVICE;
			reg=LocateRegistry.getRegistry(1004);
			stubAirline =(IAirline) reg.lookup(name);
			if(opcion) vuelos.addAll(stubAirline.BuscarVuelos(origen,destination,date,passenger));
			else vuelos=stubAirline.BuscarTodoVuelos();
			
		}catch (RemoteException e) {
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
			Registry reg=LocateRegistry.getRegistry(Integer.parseInt(PORT));
			IAirline stubAirline =(IAirline) reg.lookup(name);
			b=stubAirline.ReservaVuelo(cod_vuelo, usuario, pasajeros);
			
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
