package edu.neu.coe.ranking;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;

public class Team {

    /**
     * Constructor for Team
     *
     * @param teamName team name.
     */
    public Team(String teamName) {

    }
    public void predict(Map<String, Map<Integer, Integer>> map) {
        int num = 10; //set the total goals
        Map<String, Double> m = new HashMap();
        double rate = 0.0; //scoring rate, default value is 0.0
        for (int i = 0; i <= ReadCsv.array.size() - 1; i++){
            Map(Integer, Integer) map1 = map.get(ReadCsv.array.get(i).toString()); //according to the team get the map
            for(Entry<Integer, Integer> vo : map1.entrySet()){ //number of goals and times(round)
                Integer goal = vo.getKey();
                Integer round = vo.getValue();
                double scoringRate = goal/round;
                Double score = num * scoringRate;
                m.put(ReadCsv.array.get(i).toString(), score); //teams and their score
            }
        }

        // Sorting teams according to the score
        List<Map.Entry<String, Double>> list = new ArrayList<>(m.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>()
        {
           public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2)
           {
               // sorting with values
               return o2.getValue().compareTo(o1.getValue());
           }
        });

        // List traversal. Collection after we put map.Entry into list and sort
        for(Map.Entry s: list)
        {
            System.out.println(s.getKey()+"--"+s.getValue());
        }
    }
    // any attributes or helper functions needed to store previous goals

}
