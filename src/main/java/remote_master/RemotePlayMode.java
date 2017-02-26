package remote_master;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class RemotePlayMode {
	
	Page root;
	public RemotePlayMode(Page page){
		this.root=page;
	}
	
	public RemotePlayMode(){
		this.root=setup();
	}
	
	//setup sample
	public Page setup() {
		Page TV_Brightness = new Page("TV_Brightness");
		String tmp =TV_Brightness.goback("up");
		Page Picture_mode = TV_Brightness.addPage("Picture_mode","down");
		Page Picture_size = Picture_mode.addPage("Picture_size","down");
		Page Audio_effect = Picture_size.addPage("Audio_effect","down");
		Page Sleep_timer = Audio_effect.addPage("Sleep_timer","down");
		Page Closed_captioning = Sleep_timer.addPage("Closed_captioning","down");
		Page Captioning_track = Closed_captioning.addPage("Captioning_track","down");
		Page SAP = Captioning_track.addPage("SAP","down");
		Page Advanced_picture_settings = SAP.addPage("Advanced_picture_settings","down");
		Advanced_picture_settings.addPage(TV_Brightness, "down");
		
		
		
		return TV_Brightness;
	}
	
	
	//search, loop detection later
	

	
	public List<String> searchDFS(String button) {
		List<String> list=new ArrayList<>();
		if(searchHelper(root,button,list,new HashSet<>()))
			return list;
		else
			return null;
	}
	
	public boolean searchHelper(Page curr,String button,List<String> list,Set<Page> visited) {
		if(visited.contains(curr))
			return false;
		if(curr.pageName.equals(button)) {
			return true;
		}else {		
			visited.add(curr);
			Set<Map.Entry<String,Object[]>> set = curr.map.entrySet();
			for(Map.Entry<String, Object[]> entry:set) {
				if(visited.contains((Page)entry.getValue()[1]))
					 continue;
				list.add((String) entry.getValue()[0]);				
				if(searchHelper((Page)entry.getValue()[1],button,list,visited)) {
					return true;
				}else {
					list.remove(list.size()-1);
				}
			}
		}
		
		return false;
	}
	//add key

}
