//需要客户端的Stub
package service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote{
	public boolean register(String username,String password)throws RemoteException, FileNotFoundException, IOException;
	
	public boolean login(String username, String password) throws RemoteException, FileNotFoundException, IOException;

	public boolean logout(String username) throws RemoteException;
}
