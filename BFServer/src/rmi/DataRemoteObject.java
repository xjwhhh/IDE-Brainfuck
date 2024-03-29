package rmi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.ExecuteService;
import service.IOService;
import service.UserService;
import serviceImpl.ExecuteServiceImpl;
import serviceImpl.IOServiceImpl;
import serviceImpl.UserServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService,ExecuteService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	private ExecuteService executeService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		executeService= new ExecuteServiceImpl();
	}
	
	public String execute(String code,String param)throws RemoteException{
		return executeService.execute(code, param);
	}

	public boolean writeFile(String file, String userId, String fileName,String time) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName,time);
	}

	public String readFile(String userId, String fileName,String time) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName,time);
	}

	public String readFileList(String userId) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId);
	}
	
	public boolean deleteFile(String userId,String fileName) throws RemoteException{
		return iOService.deleteFile(userId,fileName);
	}
	
	public boolean register(String username,String password)throws IOException{
		return userService.register(username, password);
	}
	public boolean login(String username, String password) throws FileNotFoundException, IOException ,RemoteException{
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	public boolean logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.logout(username);
	}

}
