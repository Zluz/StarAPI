package jmr.home.api.comm.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISimpleMessageConsumer extends Remote {

	public enum Type { MESSAGE, CONTROL };
	
	public enum Signal {
			INITIALIZE,
			SHUTDOWN,
			REQ_STATUS,
	}
	
	public boolean sendMessage(	final Type type,
								final String strText ) throws RemoteException;

	public String sendSignal( final Signal signal ) throws RemoteException;

	

	public static ISimpleMessageConsumer TEST = new ISimpleMessageConsumer() {
		@Override
		public boolean sendMessage(Type type, String strText) 
						throws RemoteException {
			return false;
		}
		@Override
		public String sendSignal(Signal signal) throws RemoteException {
			return null;
		}};
	
}
