package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.IOService;

public class IOServiceImpl implements IOService{
	static String str="";
	public boolean writeFile(String file, String userId, String fileName,String time) {
		File f = new File(userId + "_" + fileName+ "_" +time);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String readFile(String userId, String fileName,String time) {
		// TODO Auto-generated method stub
		String code="";
		String line="";
		File f = new File(userId + "_" + fileName+ "_" +time);
		try {
			FileReader fr=new FileReader(f);
			BufferedReader reader=new BufferedReader(fr);
			try {
				while((line=reader.readLine())!=null){
					code=code+line;
				}
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return code;
	}

	public String readFileList(String userId) {
		str="";
		String readfilelist="";
		 File f = new File("C:/Users/Administrator/Desktop/BF/workspace_v1.0/workspace/BFServer");
	        if (!f.exists()) {
	            System.out.println("文件路径不存在");
	        } else {
	           
	        readfilelist=test(f,userId);
	        }
	    
		return readfilelist;
	}
	
	public boolean deleteFile(String userId,String fileName){
		boolean isright=false;
		File f=new File("C:/Users/Administrator/Desktop/BF/workspace_v1.0/workspace/BFServer");
		 if (!f.exists()) {
	            System.out.println("文件路径不存在");
	        } else {
	           isright=test1(f,userId,fileName);
	        }
		return isright;
	}
	
	public static String test(File readFile,String userId) {
		
        if (readFile.isDirectory()) {
            File[] files = readFile.listFiles();
            for (File file : files) {
                test(file,userId);
            }
        } else {
            String fileName = readFile.getName();
            if (fileName.contains(userId+"_")) {
            str=str+"/"+fileName;
            }
        }
		return str;
	}
	
public static boolean test1(File readFile,String userId,String filename) {
		boolean isright=false;
        if (readFile.isDirectory()) {
            File[] files = readFile.listFiles();
            for (File file : files) {
                test1(file,userId,filename);
            }
        } else {
            String fileName = readFile.getName();
            if (fileName.contains(userId+"_"+filename)) {
            File file=new File(fileName);
            file.delete();
            isright=true;
            }
        }
		 return isright;
	}
}
