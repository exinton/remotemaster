package remote_master;

import java.util.HashMap;
import java.util.Map;

public class Page {

	String pageName;
	Map<String,Object[]> map;
	private boolean notLeaf;
	public Page(String name){
		pageName=name;
		map = new HashMap<String,Object[]>();
	}
	
	public Page addPage(String name,String button){
		Page tmp=new Page(name);		
		if(!map.containsKey(name)){
			map.put(name,new Object[]{button,tmp});	
		}else{
			return tmp;
		}	
		tmp.addPage(this, goback(button));
		return tmp;
	}
	
	public boolean isNotLeaf() {
		return notLeaf;
	}

	public void setNotLeaf(boolean notLeaf) {
		this.notLeaf = notLeaf;
	}

	public Page addPage(Page page,String button){	
		if(!map.containsKey(page.pageName)){
			map.put(page.pageName,new Object[]{button,page});			
		}
		return (Page)map.get(page.pageName)[1];
	}
	
	
	public String goback(String button){
		switch (button){
			case "up": return "down";
			case "down": return "up";
			case "left": return "right";
			case "right": return "left";		
		}
		return "";		
	}
	

}
