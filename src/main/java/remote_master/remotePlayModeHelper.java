package remote_master;

import java.util.List;

public class remotePlayModeHelper {
	
	
	public static void main(String[] args){
		RemotePlayMode remote = new RemotePlayMode();
		List<String> output=remote.searchDFS("Captioning_track");
		for(String str:output) {
			System.out.println(str);
		}
	}

}
