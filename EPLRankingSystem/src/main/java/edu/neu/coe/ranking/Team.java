package edu.neu.coe.ranking;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

//import main.java.edu.neu.coe.ranking.ReadCsv;
public class Team {

    public int result(String A, String B) {
        ReadCsv readcsvh = new ReadCsv();
        Map<String, Map<Integer, Integer>> hmap = readcsvh.hmap;
        ReadCsv readcsva = new ReadCsv();
        Map<String, Map<Integer,Integer>> amap = readcsva.amap;

        Map<String, Double> map = new HashMap<>();
        for (Entry<Integer, Integer> teamA : hmap.get(A).entrySet()) {
            int numA = teamA.getKey();
            int htotal = 0;
            for (Map.Entry<Integer, Integer> teamAtotal : hmap.get(A).entrySet()) {

                htotal += teamAtotal.getValue();

            }
            double scoringRateA = (double) hmap.get(A).get(numA) / htotal;
            //}
            for (Entry<Integer, Integer> teamB : amap.get(B).entrySet()) {
                int numB = teamB.getKey();
                int atotal = 0;
                for (Entry<Integer, Integer> teamBtotal : hmap.get(B).entrySet()) {
                    atotal += teamBtotal.getValue();
                }
                double scoringRateB = (double) amap.get(B).get(numB) / atotal;

                String score = Integer.toString(numA) + numB;
                double pscore =  scoringRateA * scoringRateB ;

                map.put(score, pscore);
            }
        }
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Entry<String, Double>>() {

            @Override
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
                // sorting with values
                return o2.getValue().compareTo(o1.getValue());
            }
        });
//        for(Map.Entry s : list){
//            System.out.println(s);
//        }
            String score = list.get(0).getKey();
            if (Character.getNumericValue(score.charAt(0)) > Character.getNumericValue(score.charAt(1))) {
                return 1;
            } else if (Character.getNumericValue(score.charAt(0)) == Character.getNumericValue(score.charAt(1))) {
                return 0;
            } else {
                return -1;
            }

    }

//    main function for test
//    public static void main(String[] args) {
//        Team abc = new Team();
//        int a = abc.result("Liverpool","Norwich");
//        System.out.println(a);
//    }
}



