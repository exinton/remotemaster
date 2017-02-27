package remote_master;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class remotePlayModeHelper {
	static String device;
	static int timeout;
	int testdevice;
	static File myFolder;
	static File testerFolder;
	public remotePlayModeHelper() {
		device="COM5";
		testdevice=11;
		timeout=400;
		myFolder = new File("C:\\Users\\xtong\\git\\python-irtoy");
		testerFolder = new File("C:\\Users\\xtong\\workspace\\780ctester\\src");
	}
	
	public void testHDMI() {
		Monitor m = new Monitor();
		List<List<String>> list = m.sendHDMI();
		for(List<String> slist:list) {
			System.out.println("new tester output combination");
			for(String str:slist) {
				try {
					String command="irtoy "+testdevice+" "+str;
					System.out.println(command);
					Runtime.getRuntime().exec(new String[]{"cmd","/c",command},null,testerFolder);
					Thread.sleep(timeout);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//remote work here
			start();
			
		}
		
	}
	
	public void start(){
		String device="COM5";
		RemotePlayMode remote = new RemotePlayMode();
		List<String> output=remote.searchDFS("Picture_size");
		File myFolder = new File("C:\\Users\\xtong\\git\\python-irtoy");
		pressButton("info");
		tranverse(output,device,myFolder);
		interate(5,device,"right",myFolder);
		pressButton("info");
		pressButton("ok");
		pressButton("ok");
	}
	
	
	public static void pressButton(String button) {
		
		try {
			Runtime.getRuntime().exec("python irtoy.py --device="+device+" play "+button+".bin", null, myFolder);
			Thread.sleep(timeout);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public static void tranverse(List<String> list,String device,File myFolder) {		
		
		//python irtoy.py --device=COM5 play power.bin
		//Runtime.getRuntime().exec("netsh");	
		for(String str:list) {
			String command="python irtoy.py --device="+device+" play ";
			command=command+str+".bin";
			execute(command,myFolder);
		}
		
	}
	
	public static void execute(String cmd,File myFolder) {
		try {
			System.out.println(cmd);
			Runtime.getRuntime().exec(cmd,null,myFolder);
			Thread.sleep(timeout);
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void interate(int count,String device,String direction,File myFolder) {
		// TODO Auto-generated method stub
		for(int i=0;i<count;i++) {
			String cmd="python irtoy.py --device="+device+" play "+direction+".bin";
			execute(cmd,myFolder);
			
		}
	}
	
	public static void main(String[] args) {
		remotePlayModeHelper helper = new remotePlayModeHelper();
		helper.testHDMI();
	}

}
