package remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import assemble.EasyBookingAssembler;
import assemble.FlightDTO;
import server.*;


public class Facade extends UnicastRemoteObject implements IFacade 
{

	private static final long serialVersionUID = 1L;
	private AP_Aero aeroServ;
	private AP_Auth authServ;
	private AP_Pago pagoServ;

	public Facade(AP_Aero aeroServ, AP_Auth authServ, AP_Pago pagoServ) throws RemoteException 
	{
		super();
		this.authServ = authServ;
		this.aeroServ = aeroServ;
		this.pagoServ = pagoServ;
	}
	
	@Override
	public List<FlightDTO> BuscarVuelos(String origen, String destination, String fecha, int passenger)
			throws RemoteException {
		return EasyBookingAssembler.assemble(aeroServ.BuscarVuelos(origen, destination, fecha, passenger));
	}

	@Override
	public boolean ReservarVuelo(FlightDTO MiVuelo, String usuario, int pasajeros) throws RemoteException {
		return aeroServ.ReservarVuelo(MiVuelo.getFlight_number(), usuario, pasajeros);
		
	}

	@Override
	public boolean IniciarSesion(String usuario, String contrasena) throws RemoteException {
		return authServ.IniciarSesion(usuario, contrasena);
	}

	@Override
	public boolean NuevoUsuario(String usuario, String contrasena, String auth_sys_name, String Payment)
			throws RemoteException {
		return authServ.NuevoUsuario(usuario, contrasena, auth_sys_name, Payment);
	}

	@Override
	public boolean PagoReserva(String usuario, int precio) throws RemoteException {
		return pagoServ.PagoReserva(usuario, precio);
	}

	@Override
	public String sayHello() throws RemoteException {
		
		return "Holaaaaaaaaa";
	}
}