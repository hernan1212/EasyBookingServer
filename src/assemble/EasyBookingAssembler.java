package assemble;

import java.util.ArrayList;
import java.util.List;

import LN.Flight;

public class EasyBookingAssembler {
	private static EasyBookingAssembler assembler;
	private EasyBookingAssembler(){}
	
	public static EasyBookingAssembler getInstance(){
		synchronized(EasyBookingAssembler.class){
			if(assembler==null)
			{
				assembler=new EasyBookingAssembler();
			}
		}
		return assembler;
	}
	public static List<FlightDTO> assemble(List<Flight> vuelos) 
	{
		List<FlightDTO> vuelosDTO = new ArrayList<>();

		for (Flight v : vuelos) 
		{
			vuelosDTO.add(new FlightDTO(v.getFlight_number(), v.getDeparture_date(),v.getArrival_date(),v.getDeparture().getPlace(),v.getArrival().getPlace(),v.getPrecio()));
		}

		System.out.println("* Assembling Flights ...");
		
		return vuelosDTO;
	}
}
