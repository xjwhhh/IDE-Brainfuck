package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

import rmi.RemoteHelper;


public class MainFrame extends JFrame {
	Font bigfont=new Font("serif", Font.BOLD,30);
	JTextArea textArea=new JTextArea();
	JTextArea input=new JTextArea();
	JTextArea result=new JTextArea();
	TextField usernametext=new TextField();//登录的账户
	JPasswordField passwordtext=new JPasswordField();
	JFrame logframe= new JFrame("login");
	JFrame registerframe =new JFrame("register");
	TextField filenametext=new TextField();
	TextField filenametext1=new TextField();
	JFrame saveframe=new JFrame("save");
	JFrame openframe=new JFrame("open");
	TextField userIDtext=new TextField();//注册的账户
	TextField passwordtext1=new TextField();
	JPanel title= new JPanel();
	JLabel pic=new JLabel(new ImageIcon("E:/aaa.jpg"));
	DefaultComboBoxModel opench = new DefaultComboBoxModel();
	DefaultComboBoxModel opench1 = new DefaultComboBoxModel();
	JComboBox opencombo = new JComboBox(opench); 
	JComboBox opencombo1 = new JComboBox(opench1); 
	JFrame deleteframe=new JFrame("Delete");
	DefaultComboBoxModel opench2 = new DefaultComboBoxModel();
	DefaultComboBoxModel opench3 = new DefaultComboBoxModel();
	JComboBox opencombo2 = new JComboBox(opench2); 
	JComboBox opencombo3 = new JComboBox(opench3); 
	protected UndoManager undoManager = new UndoManager();
	String c="";
	JFrame versionframe= new JFrame("version");
	ArrayList<String> text=new ArrayList<String>();
	int pointer=-1;
	// 创建窗体
	JFrame frame = new JFrame("BF Client");
	
	
//一开始的登录界面
	public MainFrame() {
		logframe.setLayout(null);
		
		title.setLayout(new BorderLayout());
		title.setSize(400,150);
		title.setLocation(40,20);
		title.add(BorderLayout.CENTER,pic);
		logframe.getContentPane().add(title);
		
		JLabel username=new JLabel("userID");
		username.setSize(100,20);
		username.setLocation(20,200);
		logframe.getContentPane().add(username);
		
		JLabel password=new JLabel("password");
		password.setSize(100,20);
		password.setLocation(20,240);
		logframe.getContentPane().add(password);
		
		usernametext.setSize(200,20);
		usernametext.setLocation(220,200);
		logframe.getContentPane().add(usernametext);
		
		passwordtext.setSize(200,20);
		passwordtext.setLocation(220,240);
		logframe.getContentPane().add(passwordtext);
		
		JButton loginbutton=new JButton("login");
		loginbutton.setSize(100,40);
		loginbutton.setLocation(100,300);
		logframe.getContentPane().add(loginbutton);
		loginbutton.addActionListener(new LoginbuttonActionListener());
		
		JButton registerbutton1=new JButton("register");
		registerbutton1.setSize(100,40);
		registerbutton1.setLocation(300,300);
		logframe.getContentPane().add(registerbutton1);
		registerbutton1.addActionListener(new Registerbutton1ActionListener());

		logframe.setSize(500, 400);
		logframe.setLocation(400,200);
		logframe.setVisible(true);
		logframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//登录按钮
	class LoginbuttonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String username=usernametext.getText();
			String password=passwordtext.getText();
			boolean isright=false;
			try {
				 isright=RemoteHelper.getInstance().getUserService().login(username, password);
			} 
			catch (RemoteException e1) {
				e1.printStackTrace();
			}
			
			//登录成功后，显示主界面
			if(isright){
				logframe.setVisible(false);
			
				//菜单栏
				JMenuBar menuBar = new JMenuBar();
				JMenu fileMenu = new JMenu("File(F)");
				 fileMenu.setMnemonic(KeyEvent.VK_F);

				JMenu runMenu=new JMenu("Run(R)");
				 runMenu.setMnemonic(KeyEvent.VK_R);

				JMenu userMenu=new JMenu("User(U)");
				 userMenu.setMnemonic(KeyEvent.VK_U);
				
				menuBar.add(fileMenu);
				menuBar.add(runMenu);
				menuBar.add(userMenu);
				
				JMenuItem newMenuItem = new JMenuItem("New");
				newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
				fileMenu.add(newMenuItem);
				JMenuItem openMenuItem = new JMenuItem("Open");
				openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
				fileMenu.add(openMenuItem);
				JMenuItem saveMenuItem = new JMenuItem("Save");
				saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
				fileMenu.add(saveMenuItem);
				JMenuItem deleteMenuItem = new JMenuItem("Delete");
				deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
				fileMenu.add(deleteMenuItem);
				
				JMenuItem executeMenuItem=new JMenuItem("Execute");
				executeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
				runMenu.add(executeMenuItem);
				JMenuItem undoMenuItem=new JMenuItem("undo");
//				undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
				runMenu.add(undoMenuItem);
				JMenuItem redoMenuItem=new JMenuItem("redo");
//				redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
				runMenu.add(redoMenuItem);

				JMenuItem logoutMenuItem=new JMenuItem("logout");
				logoutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
				userMenu.add(logoutMenuItem);
				
				frame.setJMenuBar(menuBar);
				
				//	事件监听
				newMenuItem.addActionListener(new NewActionListener());
				openMenuItem.addActionListener(new OpenActionListener());
				saveMenuItem.addActionListener(new SaveActionListener());
				deleteMenuItem.addActionListener(new DeleteActionListener());
				executeMenuItem.addActionListener(new ExecuteActionListener());
				logoutMenuItem.addActionListener(new LogoutActionListener());
				undoMenuItem.addActionListener(new UndoActionListener());
				redoMenuItem.addActionListener(new RedoActionListener());
				
				//输入数据和输出区域
				Dimension d_scr=Toolkit.getDefaultToolkit ().getScreenSize();
				double x1=(d_scr.getWidth())/2;
				double y1=(d_scr.getHeight())/3;
				JPanel content=new JPanel();
				content.setPreferredSize(new Dimension(0,(int)y1));
				
				input.setPreferredSize(new Dimension((int)x1,1000));
				input.setFont(bigfont);
				JScrollPane scroller1=new JScrollPane(input);
				scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroller1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				
				result.setPreferredSize(new Dimension((int)x1-20,(int)y1-10));
				result.setFont(bigfont);
				JScrollPane scroller2=new JScrollPane(result);
				scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroller2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				content.setLayout(new BorderLayout());
				content.add(BorderLayout.WEST,scroller1);
				content.add(BorderLayout.EAST,scroller2);
				
				frame.getContentPane().add(BorderLayout.SOUTH,content);
						
				//代码区
				textArea = new JTextArea();
				Color background=new Color(204,232,207);
				textArea.setBackground(background);
				textArea.setFont(bigfont);
				JScrollPane scroller=new JScrollPane(textArea);
				scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				frame.getContentPane().add(BorderLayout.CENTER,scroller);
				
				textArea.addKeyListener(new keyListener());
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(d_scr);
				frame.setLocation(0, 0);
				frame.setVisible(true);
				logframe.setVisible(false);
			}
			else{
				JFrame errorframe=new JFrame("error");
				JLabel error=new JLabel("Sorry,your username or password is wrong");
				error.setSize(300,80);
				errorframe.getContentPane().add(error);
				errorframe.setSize(400,100);
				errorframe.setLocation(450,300);
				errorframe.setVisible(true);
			}

			}
			
		}

	//注册
		class Registerbutton1ActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				logframe.setVisible(false);
				
				registerframe.setLayout(null);
				
				title.setLayout(new BorderLayout());
				title.setSize(400,150);
				title.setLocation(40,20);
				title.add(BorderLayout.CENTER,pic);
				registerframe.getContentPane().add(title);
				
				JLabel userID=new JLabel("userID");
				userID.setSize(100,20);
				userID.setLocation(20,200);
				registerframe.getContentPane().add(userID);
				
				JLabel password=new JLabel("password");
				password.setSize(100,20);
				password.setLocation(20,240);
				registerframe.getContentPane().add(password);
				
				userIDtext.setSize(200,20);
				userIDtext.setLocation(220,200);
				registerframe.getContentPane().add(userIDtext);
				
				passwordtext1.setSize(200,20);
				passwordtext1.setLocation(220,240);
				registerframe.getContentPane().add(passwordtext1);
				
				JButton registerbutton=new JButton("register");
				registerbutton.setSize(100,40);
				registerbutton.setLocation(200,300);
				registerframe.getContentPane().add(registerbutton);
				registerbutton.addActionListener(new RegisterbuttonActionListener());
				
				registerframe.setSize(500, 400);
				registerframe.setLocation(400,200);
				registerframe.setVisible(true);
			}
		}
		
		//注册按钮
		class RegisterbuttonActionListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				boolean isright=false;
				String username=userIDtext.getText();
				String password=passwordtext1.getText();
				try {
					isright=RemoteHelper.getInstance().getUserService().register(username, password);
				} 
				catch (RemoteException e1) {
					e1.printStackTrace();
				}
				if(isright){
					userIDtext.setText(null);
					passwordtext1.setText(null);
					logframe.setVisible(true);
					registerframe.setVisible(false);
				}
				else{
					JFrame errorframe=new JFrame("error");
					JLabel error=new JLabel("Sorry,the username has been used");
					error.setSize(300,80);
					errorframe.getContentPane().add(error);
					errorframe.setSize(400,100);
					errorframe.setLocation(450,300);
					errorframe.setVisible(true);
				}
			}
		}


	//新建文件
	class NewActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			textArea.setText(null);
			input.setText(null);
			result.setText(null);
		}
	}
	
	//打开已经存在的文件
	class OpenActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			openframe.setLayout(null);
			
			title.setLayout(new BorderLayout());
			title.setSize(400,150);
			title.setLocation(40,20);
			title.add(BorderLayout.CENTER,pic);
			openframe.getContentPane().add(title);
			String userId=usernametext.getText();
			String filelist="";
			try {
				 filelist=RemoteHelper.getInstance().getIOService().readFileList(userId);
			} 
			catch (RemoteException e1) {
				e1.printStackTrace();
			}
//			System.out.println(filelist);xj
			String[] choice=filelist.split("/");
			 opench.removeAllElements();
			 ArrayList<String> jj=new ArrayList<String>();
			 for(int i=1;i<choice.length;i++){
				 String[] filech=choice[i].split("_");
				 jj.add(filech[0]+"_"+filech[1]);
			 }
			 HashSet<String> hs = new HashSet<String>(jj);
			 Iterator<String> iterator=hs.iterator();
				while(iterator.hasNext()){
					opench.addElement(iterator.next());
				}
			 opencombo.setSize(400,40);
			 opencombo.setLocation(40,200);
			
			openframe.getContentPane().add(opencombo);
			JButton yes1button=new JButton("yes");
			yes1button.setSize(100,40);
			yes1button.setLocation(200,300);
			openframe.getContentPane().add(yes1button);
			yes1button.addActionListener(new Yes1buttonActionListener());
			openframe.setSize(500, 400);
			openframe.setLocation(400,200);
			openframe.setVisible(true);
		}
	}

	class Yes1buttonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
		String filelist1="";
		c=(String) opencombo.getItemAt(opencombo.getSelectedIndex());
		try {
			 filelist1=RemoteHelper.getInstance().getIOService().readFileList(c);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String[] filea=filelist1.split("/");
		opench1.removeAllElements();
		ArrayList<String> jj=new ArrayList<String>();
		for(int i=1;i<filea.length;i++){
			String[] aa=filea[i].split("_");
			if((aa[0]+"_"+aa[1]).equals(c)){
				
				 jj.add(aa[2]);
		}
		}
		 HashSet<String> hs = new HashSet<String>(jj);
		 Iterator<String> iterator=hs.iterator();
			while(iterator.hasNext()){
				opench1.addElement(iterator.next());
			}
		
		versionframe.setLayout(null);
		title.setLayout(new BorderLayout());
		title.setSize(400,150);
		title.setLocation(40,20);
		title.add(BorderLayout.CENTER,pic);
		versionframe.getContentPane().add(title);
		 opencombo1.setSize(400,40);
		 opencombo1.setLocation(40,200);
		 versionframe.getContentPane().add(opencombo1);
		 JButton openbutton=new JButton("open");
			openbutton.setSize(100,40);
			openbutton.setLocation(200,300);
			versionframe.getContentPane().add(openbutton);
			openbutton.addActionListener(new OpenbuttonActionListener());
			versionframe.setSize(500, 400);
			versionframe.setLocation(400,200);
			versionframe.setVisible(true);
		}
	}
	
	class OpenbuttonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String code="";
			
			String[] info=c.split("_");
			String username=info[0];
			String filename=info[1];
			String time=(String) opencombo1.getItemAt(opencombo1.getSelectedIndex());
			try {
				code=RemoteHelper.getInstance().getIOService().readFile(username, filename,time);
			} 
			catch (RemoteException e1) {
				e1.printStackTrace();
			}
			textArea.setText(code);
			versionframe.setVisible(false);
			openframe.setVisible(false);
		}
	}
	
	//保存文件
	class SaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			saveframe.setLayout(null);
			
			title.setLayout(new BorderLayout());
			title.setSize(400,150);
			title.setLocation(40,20);
			title.add(BorderLayout.CENTER,pic);
			saveframe.getContentPane().add(title);
			
			JLabel filename=new JLabel("filename");
			filename.setSize(100,20);
			filename.setLocation(40,200);
			saveframe.getContentPane().add(filename);
			filenametext.setSize(200,20);
			filenametext.setLocation(220,200);
			filenametext.setText(null);
			saveframe.getContentPane().add(filenametext);
			JButton savebutton=new JButton("save");
			savebutton.setSize(100,40);
			savebutton.setLocation(200,300);
			saveframe.getContentPane().add(savebutton);
			savebutton.addActionListener(new SavebuttonActionListener());
			saveframe.setSize(500, 400);
			saveframe.setLocation(400,200);
			saveframe.setVisible(true);
		}
	}
	
	//保存按钮
	class SavebuttonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String code = textArea.getText();
			String username=usernametext.getText();
			String filename=filenametext.getText();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//设置日期格式
			String versiondate=df.format(new Date());// new Date()为获取当前系统时间
			try {
				RemoteHelper.getInstance().getIOService().writeFile(code,username, filename,versiondate);
			}
			catch (RemoteException e1) {
				e1.printStackTrace();
			}
			saveframe.setVisible(false);
			result.setText(null);
		}
	}
	
	//删除文件
	class DeleteActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			deleteframe.setLayout(null);
			
			title.setLayout(new BorderLayout());
			title.setSize(400,150);
			title.setLocation(40,20);
			title.add(BorderLayout.CENTER,pic);
			deleteframe.getContentPane().add(title);
			String userId=usernametext.getText();
			String filelist="";
			try {
				 filelist=RemoteHelper.getInstance().getIOService().readFileList(userId);
			} 
			catch (RemoteException e1) {
				e1.printStackTrace();
			}
			String[] choice=filelist.split("/");
			 opench2.removeAllElements();
			 ArrayList<String> jj=new ArrayList<String>();
			 for(int i=1;i<choice.length;i++){
				 String[] filech=choice[i].split("_");
				 jj.add(filech[0]+"_"+filech[1]);
			 }
			 HashSet<String> hs = new HashSet<String>(jj);
			 Iterator<String> iterator=hs.iterator();
				while(iterator.hasNext()){
					opench2.addElement(iterator.next());
				}
			 opencombo2.setSize(400,40);
			 opencombo2.setLocation(40,200);
			 JButton yes2button=new JButton("yes");
				yes2button.setSize(100,40);
				yes2button.setLocation(200,300);
				deleteframe.getContentPane().add(yes2button);
				yes2button.addActionListener(new Yes2buttonActionListener());
			
			deleteframe.getContentPane().add(opencombo2);
			deleteframe.setSize(500, 400);
			deleteframe.setLocation(400,200);
			deleteframe.setVisible(true);
		}
	}
	
	class Yes2buttonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String s=(String) opencombo2.getItemAt(opencombo2.getSelectedIndex());
			String filelist[]=s.split("_");
			String filename=filelist[1];
			String userId=filelist[0];
			boolean isright=false;
			try {
			isright=RemoteHelper.getInstance().getIOService().deleteFile(userId,filename);
		} 
		catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				deleteframe.setVisible(false);
		}
	}
	//执行代码
	class ExecuteActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String code=textArea.getText();
			String param=input.getText();
			String coderesult="";
		
			//运行
			try{
//				RemoteHelper.getInstance().getIOService().writeFile(code, "userId", "filename");
				coderesult=RemoteHelper.getInstance().getExecuteService().execute(code, param);
				result.setText(coderesult);
			}
			catch(RemoteException e1){
				e1.printStackTrace();
			}
		}
	}
	
		
	
		
	//登出，返回登录界面
	class LogoutActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			frame.setVisible(false);
			logframe.setVisible(true);
			usernametext.setText(null);
			passwordtext.setText(null);
			textArea.setText(null);
		}
	}
	
	//撤销
	class UndoActionListener implements  ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
//                undoManager.undo();
            	if(pointer>=0){
            	pointer=pointer-1;
            	}
//            	System.out.println(pointer);
            	if(pointer>=0){
            	textArea.setText(text.get(pointer));
            	}
            	else{
            		textArea.setText(null);
            	}
//            	System.out.println("undo");
            } catch (CannotRedoException cre) {
                cre.printStackTrace();
            }
        }
	}
	
	//重做
    class RedoActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
//                undoManager.redo();
            	if(pointer<text.size()-1){
            	pointer=pointer+1;
            	}
//            	System.out.println(pointer);
//            	System.out.println("redo");
//            	System.out.println(text.size());
            	textArea.setText(text.get(pointer));
            } catch (CannotRedoException cre) {
                cre.printStackTrace();
            }
        }
    }
    
    class keyListener implements KeyListener{
    	
			public void keyPressed(KeyEvent e){
//				text.add(textArea.getText());
//				pointer=pointer+1;
//				System.out.println(pointer);
//				System.out.println("ah");
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				String m=textArea.getText();
				if(m.length()>=1){
				if(m.substring(m.length()-1).equals("[")){
					textArea.setText(textArea.getText()+"]");
				}
				}
				text.add(pointer+1,textArea.getText());
			
				pointer=pointer+1;
//				System.out.println(pointer);
//				System.out.println(text.get(pointer));
//				System.out.println(pointer);
//				System.out.println("aaaaaaaa");
//				System.out.println("hhhhhh");
				
			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		}
    
    
    
}
    	


