package edu.neu.coe.ranking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
 
public class ReadCsv {

	public void read(String[] paths) {
        Map<String,Integer> map= new HashMap<>();
        for(String p: paths) {
	        try {       	
				BufferedReader reade = new BufferedReader(new FileReader(p));       
		        String line = null;
		        int index=0;
		        while((line=reade.readLine())!=null){
		        	if(index>0) {
			            String item[] = line.split(",");
			            for(int i=2;i<=3;i++) {
			                map.put(item[i], map.getOrDefault(item[i], 0)+Integer.parseInt(item[i+2]));
			            }
		            }
		        	index++;
		        }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
	}
	
	public static void main(String[] args) {
		String p1="C:\\Users\\Susan Sun\\Desktop\\INFO6205FinalProject\\EPLRankingSystem\\src\\main\\resources\\EPL20152016.csv";
		String p2="C:\\Users\\Susan Sun\\Desktop\\INFO6205FinalProject\\EPLRankingSystem\\src\\main\\resources\\EPL20162017.csv";
		String p3="C:\\Users\\Susan Sun\\Desktop\\INFO6205FinalProject\\EPLRankingSystem\\src\\main\\resources\\EPL20172018.csv";
		String p4="C:\\Users\\Susan Sun\\Desktop\\INFO6205FinalProject\\EPLRankingSystem\\src\\main\\resources\\EPL20182019.csv";
		String p5="C:\\Users\\Susan Sun\\Desktop\\INFO6205FinalProject\\EPLRankingSystem\\src\\main\\resources\\EPL20192020.csv";
		String[] paths= {p1,p2,p3,p4,p5};
		ReadCsv readcsv=new ReadCsv();
		readcsv.read(paths);
	}
}
