package edu.neu.coe.ranking;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

import main.java.edu.neu.coe.ranking.ReadCsv;
public class Team {

    /**
     * Constructor for Team
     *
     * @param teamName team name.
     */
    public Team(String teamName) {

    }

    public int result(String A, String B) {
        Map<String, Double> map = new HashMap();
        for (Entry<Integer, Integer> teamA : hmap.get(A).entrySet()) {
            int numA = teamA.getKey();
            //int htotal = teamA.getValue();
            double scoringRateA = hmap.get(A).get(numA) / 95;
            for (Entry<Integer, Ingeger> teamB : amap.get(B).entrySet()) {
                int numB = teamB.getKey();
                String score = String.valueOf(numA) + String.valueOf(numB);
                double scoringRateB = amap.get(B).get(numB) / 95;
                double pscore = hmap.get(A).get(numA) / 95 * amap.get(B).get(numB) / 95;
            }
            map.put(score, pscore);
        }
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {

            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                // sorting with values
                return o2.getValue().compareTo(o1.getValue());
            }
        });
            String score = list.get(0).getKey();
            if (Integer.parseInt(score[0]) > Integer.parseInt(score[1])) {
                return 1;
            } else if (Integer.parseInt(score[0]) == Integer.parseInt(score[1]) {
                return 0;
            } else {
                return -1;
            }

    }

}

//    public void predict(Map<String, Map<Integer, Integer>> map) {
//        int num = 10; //set the total goals
//        Map<String, Double> m = new HashMap();
//        double rate = 0.0; //scoring rate, default value is 0.0
//        for (int i = 0; i <= ReadCsv.array.size() - 1; i++){
//            Map<Integer, Integer> map1 = map.get(ReadCsv.array.get(i).toString()); //according to the team get the map
//            for(Entry<Integer, Integer> vo : map1.entrySet()){ //number of goals and times(round)
//                Integer goal = vo.getKey();
//                Integer round = vo.getValue();
//                double scoringRate = goal/round;
//                Double score = num * scoringRate;
//                m.put(ReadCsv.array.get(i).toString(), score); //teams and their score
//            }
//        }

        // Sorting teams according to the score
//        List<Map.Entry<String, Double>> list = new ArrayList<>(m.entrySet());
//        Collections.sort(list, new Comparator<Map.Entry<String, Double>>()
//        {
//            @Override
//            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2)
//           {
//               // sorting with values
//               return o2.getValue().compareTo(o1.getValue());
//           }
//        });

        // List traversal. Collection after we put map.Entry into list and sort
//        for(Map.Entry s: list)
//        {
//            System.out.println(s.getKey()+"--"+s.getValue());
//        }
//    }
    // any attributes or helper functions needed to store previous goals

//}
//public int result(String A, String B){
//hmap.get(A)调用出来的是A队的小map
//Map<String, Double> 比分当成string，例如0：1存成string。double所指的value是两个进球率相乘的结果（根据比分）--> hashmap排序
// 找最高的概率，然后得出key。如果key第一位大于第二位，返回1；相等返回0；小于返回-1
// }