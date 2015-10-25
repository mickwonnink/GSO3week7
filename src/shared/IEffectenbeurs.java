package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IEffectenbeurs extends Remote {

	List<IFonds> getKoersen() throws RemoteException;

}