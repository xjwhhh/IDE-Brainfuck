//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
//		System.out.println(code);
		String result="";
		boolean isright=true;
		char[] chcode=code.toCharArray();
		char[] chparam=param.toCharArray();
		int [] num=new int[100];
		int pointer=0;//指针
		int j=0;
		for(int i=0;i<chcode.length;i++){
			switch(chcode[i]){
			case('>'):
			pointer++;
			break;
			case('<'):
			pointer--;
			break;
			case('+'):
			num[pointer]++;
			break;
			case('-'):
			num[pointer]--;
			break;
			case('.'):
			result=result+(char)(num[pointer]);
			break;
			case(','):
			num[pointer] =(int)(chparam[j]);
			j++;
			break;
			case('['):
				if((num[pointer])==0){
					i =getright(chcode,i);					
				}
			break;
			case(']'):
				if((num[pointer])!=0){
					i = getleft(chcode,i);
					
				}
			break;
//			default:
//				isright=false;
			}
		}
//		if(isright!=true){
//			result="Sorry, your code is wrong!";
//		}
		return result;
	}
	//寻找对应的]
	public int getright(char[] chcode, int i){
		int rightresult=0;
		ArrayList<Character> code = new ArrayList<Character>();	
		for(char cell:chcode){
			code.add(cell);
		}
		for(int m=i;m<chcode.length;m++){
			if((chcode[m]==']')){
				if(code.subList(i+1, m).contains('[')){
					i=m;
				}
				else{
					rightresult = m;
					break;
				}
			}
		}

		return rightresult;
	}
	//寻找对应的[
	public int getleft(char[] chcode,int i){
		
		int leftresult=0;
		ArrayList<Character> code = new ArrayList<Character>();	
		for(char cell:chcode){
			code.add(cell);
		}
		for(int m=i;m>=0;m--){
			if((chcode[m]=='[')){
				if(code.subList(m, i).contains(']')){
					i=m;
				}
				else{
					leftresult = m;
					break;
				}
			}
		}
		return leftresult;
	}

}
