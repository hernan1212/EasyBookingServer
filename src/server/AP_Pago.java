package server;

import DAO.JDO.DAO;
import LN.Payment;
import gateway.GatewayEB;

public class AP_Pago 
{
	GatewayEB G=new GatewayEB();
	DAO dao=new DAO();
	public AP_Pago() 
	{
	}

	public synchronized boolean PagoReserva(String usuario, int precio) 
	{
		String CodeReserva=G.PagoReserva(usuario, precio);
		if(CodeReserva.isEmpty())
		{
			return false;
		}
		Payment p=new Payment(CodeReserva);
		if(!dao.GuardarPago(p))
		{
			return false;
		}
		System.out.println("* Creating a Reservation: " + CodeReserva);
		return true;
	}
}