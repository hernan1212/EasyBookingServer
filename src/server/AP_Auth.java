package server;

import DAO.JDO.DAO;
import LN.User;
import gateway.GatewayEB;

public class AP_Auth 
{
	GatewayEB G=new GatewayEB();
	DAO dao=new DAO();
	public AP_Auth() 
	{
	}

	public synchronized boolean IniciarSesion(String usuario, String contrasena) 
	{
		if(!dao.ComprobarUsuario(usuario, contrasena))
		{
			return false;
		}
		return true;
	}

	public synchronized boolean NuevoUsuario(String usuario, String contrasena, String auth_sys_name, String payment) 
	{
		if(dao.ComprobarUsuario(usuario, contrasena))
		{
			return false;
		}
		if(!G.ComprobarUsuario(usuario, contrasena))
		{
			return false;
		}
		if(dao.GuardarUsuario(new User(usuario, contrasena, auth_sys_name, payment)))
		{
			return true;
		}
		return false;
		
	}
}
