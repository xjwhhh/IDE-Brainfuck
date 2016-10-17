package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;
import java.util.ArrayList;

import service.UserService;

public class UserServiceImpl implements UserService{
	String line=new String();
	String[] str;
	public boolean register(String username,String password)throws RemoteException{
		File userinformation=new File("userinformation");
		boolean isright1=true;
//		System.out.println(username);
			FileReader filereader1;
			try {
				filereader1 = new FileReader(userinformation);
			BufferedReader reader1=new BufferedReader(filereader1);
				try {
					while((line=reader1.readLine())!=null){
						str=line.split(",");
//						System.out.println(str[0]);
//						str[0]=str[0].substring(1);
						if(str.length>0){
						if(username.equals(str[0])){
							isright1=false;
							break;
						}
					
					}
					}
					reader1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			if(isright1){
			File userinformationlist1=new File("userinformation");
				FileWriter writer1;
				try {
					writer1 = new FileWriter(userinformationlist1,true);
						writer1.write("\r\n"+username+","+password);
					writer1.close();
				} 
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
			
		}
		return isright1;
	}
	
	public boolean login(String username, String password) throws IOException {
		boolean isright2=false;
	
		
			File userinfo=new File("userinformation");
			FileReader userinforeader;
			
				userinforeader = new FileReader(userinfo);
				BufferedReader reader=new BufferedReader(userinforeader);
				while((line=reader.readLine())!=null){
					 str=line.split(",");
//					 str[0] = str[0].substring(1);
//					 System.out.println(str[0]);
					if(username.equals(str[0])&&password.equals(str[1])){
						isright2=true;
						break;
					}
					
				}
				reader.close();
			
	
				
		
		
		return isright2;
	}

	public boolean logout(String username) throws RemoteException {
		return true;
	}

}
