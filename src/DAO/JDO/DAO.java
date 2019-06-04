package DAO.JDO;


import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import LN.*;

public class DAO implements IDAO {

	private PersistenceManagerFactory persistentManagerFactory;
	private PersistenceManager persistentManager;
	private Transaction transaction;
	public DAO() {
		super();
		try
		{
			persistentManagerFactory=JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		}
		catch (Exception ex) {
		System.err.println("* Exception: " + ex.getMessage());
		}
	}
			

	@Override
	public boolean GuardarVuelos(List<Flight> vuelos) {
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			
		    transaction.begin();
		    for(Flight f:vuelos)
		    {
		    	persistentManager.makePersistent(f);
		    }
		    System.out.println("- Inserted into db: " + vuelos.size());
		    transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception inserting flights data into db: " + ex.getMessage());
			return false;
		} finally {		    
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
		    
		    persistentManager.close();
		}
		return true;
	}

	@Override
	public boolean GuardarPago(Payment pago) {
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			
		    transaction.begin();
		    persistentManager.makePersistent(pago);
		    System.out.println("- Inserted into db: " + pago.getCode());
		    transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception inserting payment data into db: " + ex.getMessage());
			return false;
		} finally {		    
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
		    
		    persistentManager.close();
		}
	    return true;
	}

	@Override
	public boolean GuardarUsuario(User Usuario) {
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			
		    transaction.begin();
		    persistentManager.makePersistent(Usuario);
		    System.out.println("- Inserted into db: " + Usuario.getEmail());
		    transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception inserting usuario data into db: " + ex.getMessage());
			return false;
		} finally {		    
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
		    
		    persistentManager.close();
		}
	    return true;
	}

	@Override
	public boolean ComprobarUsuario(String nombre, String contrasena) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
			
		try {
		    transaction.begin();
		    Query<User> query = persistentManager.newQuery(User.class);
			query.setFilter("email == " + nombre + " && password == " + contrasena );

			@SuppressWarnings("unchecked")
			List<User> accounts = (List<User>) query.execute();
			if(accounts.size()!=1)throw new Exception();

		    transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception executing a query: " + ex.getMessage());
			return false;
		} finally {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }

		    persistentManager.close();
		}
		return true;
	}

	@Override
	public boolean ReservarVuelo(Reservation reserva) {
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			
		    transaction.begin();
		    persistentManager.makePersistent(reserva);
		    System.out.println("- Inserted into db: " + reserva.getId());
		    transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception inserting reservation data into db: " + ex.getMessage());
			return false;
		} finally {		    
			if (transaction.isActive()) {
		        transaction.rollback();
		    }
		    
		    persistentManager.close();
		}
		return true;
	}


	@Override
	public Flight GetVuelo(int cod_vuelo) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
			
		try {
		    transaction.begin();
		    Query<User> query = persistentManager.newQuery(User.class);
			query.setFilter("flight_number == " + cod_vuelo);

			@SuppressWarnings("unchecked")
			List<Flight> accounts = (List<Flight>) query.execute();
			if(accounts.size()!=1) return accounts.get(0);
		    transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception executing a query: " + ex.getMessage());
			return null;
		} finally {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }

		    persistentManager.close();
		}
		return null;
	}


	@Override
	public User GetUser(String correo) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
			
		try {
		    transaction.begin();
		    Query<User> query = persistentManager.newQuery(User.class);
			query.setFilter("correo == " + correo);

			@SuppressWarnings("unchecked")
			List<User> accounts = (List<User>) query.execute();
			if(accounts.size()!=1) return accounts.get(0);
		    transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception executing a query: " + ex.getMessage());
			return null;
		} finally {
			if (transaction.isActive()) {
		        transaction.rollback();
		    }

		    persistentManager.close();
		}
		return null;
	}

}
