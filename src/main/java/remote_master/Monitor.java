package remote_master;

import java.util.ArrayList;
import java.util.List;

public class Monitor {
	
	
	public List<List<String>> sendHDMI(){
		String[] inputTV = new String[] {"480i30","480p60","720p24","720p30","720p60","1080ii60","1080p24","1080p30","1080p60",
				"640x480_60hz","720x400_70hz","800x600_56hz","800x600_60hz","1024x768_60hz","1280x720_60hz","1280875","1280860",
				"1280x960_60hz","1280x1024_60hz","1440960","1680x1050_60hz","1920x1080_60hz"};
		String[] colorSpace= new String[] {"DVSM 2","DVSM 4"};
		String[] bitDepth= new String[] {"NBPC 8","NBPC 10"};		
		//prepare the command sets
		List<List<String>> list = new ArrayList<>();
		for(String resolution:inputTV) {
			for(String colorspace:colorSpace) {
				for(String bitdepth:bitDepth) {
					List<String> tmp = new ArrayList<>();
					tmp.add("FMTL "+resolution);
					tmp.add(colorspace);
					tmp.add(bitdepth);
					tmp.add("FMTU");
					list.add(tmp);
				}
			}
		}
		return list;
		
	}
	


}
