package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remote.Facade;
import remote.IFacade;

public class EasyBookinganagerServer 
{

	public static void main(String[] args) 
	{

		if (System.getSecurityManager() == null) 
		{
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + "127.0.0.1" + ":" + "1000" + "/" + "ServEB";

		try 
		{
			Registry registry = LocateRegistry.createRegistry((Integer.valueOf("1000")));
			AP_Aero aero = new AP_Aero();
			AP_Pago pago = new AP_Pago();
			AP_Auth auth = new AP_Auth();

			IFacade gateway = new Facade(aero,auth,pago);			
			registry.rebind(name, gateway);
			System.out.println("* Server Service '" + name + "' active and waiting...");
		} 
		catch (Exception e) 
		{
			System.err.println("$ EasyBooking Server exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
}