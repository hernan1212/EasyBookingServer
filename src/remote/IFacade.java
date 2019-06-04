package remote;


import java.rmi.Remote;
import assemble.*;
import java.rmi.RemoteException;
import java.util.List;

public interface IFacade extends Remote {
	public List<FlightDTO> BuscarVuelos(String origen, String destination, String fecha, int passenger) throws RemoteException;
    public boolean ReservarVuelo(FlightDTO MiVuelo, String usuario, int pasajeros) throws RemoteException;    
    public boolean PagoReserva(String usuario, int precio) throws RemoteException; 
    public boolean IniciarSesion(String usuario, String contrasena) throws RemoteException; 
    public boolean NuevoUsuario(String usuario, String contrasena, String auth_sys_name, String Payment) throws RemoteException; 
 }