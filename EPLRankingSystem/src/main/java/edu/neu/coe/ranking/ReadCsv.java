package edu.neu.coe.ranking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

 
public class ReadCsv {
	
	String p1="src\\main\\resources\\EPL20152016.csv";
	String p2="src\\main\\resources\\EPL20162017.csv";
	String p3="src\\main\\resources\\EPL20172018.csv";
	String p4="src\\main\\resources\\EPL20182019.csv";
	String p5="src\\main\\resources\\EPL20192020.csv";
	String[] paths= {p1,p2,p3,p4,p5};
	
    public static Map<String,Map<Integer,Integer>> hmap= read(2);
    public static Map<String,Map<Integer,Integer>> amap= read(3);
	
	public Map<String,Map<Integer,Integer>> read(int i) {
	    Map<String,Map<Integer,Integer>> map= new HashMap<>();	
	    for(String p: paths) {
	        try {       	
				BufferedReader reade = new BufferedReader(new FileReader(p));       
		        String line = null;
		        int index=0;
		        while((line=reade.readLine())!=null){
		        	if(index>0) {
			            String item[] = line.split(",");
			            if(map.get(item[i])==null) {
			            	Map<Integer,Integer> ori=new HashMap<>();
			            	ori.put(Integer.parseInt(item[i+2]),1);
			            	map.put(item[i], ori);
			            }
			            else 
			            map.get(item[i]).put(Integer.parseInt(item[i+2]), map.get(item[i]).getOrDefault(Integer.parseInt(item[i+2]),0)+1);
		            }
		        	index++;
		        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
        return map;
	}
	
}
