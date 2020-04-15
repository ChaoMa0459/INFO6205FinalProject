package edu.neu.coe.ranking;

import java.util.*;
import java.util.Map.Entry;

//import main.java.edu.neu.coe.ranking.ReadCsv;
public class Prediction {

    // A: home team, B: away team
    // return a prediction result based on probability
    public int result(String A, String B, Map<String, Map<Integer, Integer>> hmap, Map<String, Map<Integer,Integer>> amap) {

        // map of final score and probability
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
        // list of score and probability
        List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Entry<String, Double>>() {

            @Override
            public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
                // sorting with values
                return o2.getValue().compareTo(o1.getValue());
            }
        });

//        for(Map.Entry<String, Double> s : list){
//            System.out.println(s);
//        }

        // get win, draw, lose record of home
        String win = "win";
        String lose = "lose";
        String draw = "draw";

        // key: win/lose/draw, value: probability
        Map<String, Double> resultMap = new HashMap<>();
        resultMap.put(win, 0.0);
        resultMap.put(lose, 0.0);
        resultMap.put(draw, 0.0);

        for(Map.Entry<String, Double> s : map.entrySet()){
            String score = s.getKey();
            Double probability = s.getValue();
            int hGoal = Character.getNumericValue(score.charAt(0));
            int aGoal = Character.getNumericValue(score.charAt(1));
            if (hGoal > aGoal) {
                resultMap.put(win, resultMap.get(win) + probability);
            } else if (hGoal == aGoal) {
                resultMap.put(draw, resultMap.get(draw) + probability);
            } else {
                resultMap.put(lose, resultMap.get(lose) + probability);
            }
        }

//        for (String res: resultMap.keySet()) {
//            System.out.println(res + ": " + resultMap.get(res));
//        }

        // rand < P(win) -> home team win
        // P(win) < rand < P(win) + P(draw) -> draw
        // P(win) + P(draw) < rand < 1 -> home team lose
        double rand = (double)(new Random().nextInt(99)) / 100;
        if (rand <= resultMap.get(win)) {
            return 3;
        } else if (rand <= resultMap.get(win) + resultMap.get(draw)) {
            return 1;
        } else {
            return 0;
        }
    }

//    main function for test
    public static void main(String[] args) {
        Prediction abc = new Prediction();
        ReadCsv readcsv = new ReadCsv();
        Map<String, Map<Integer, Integer>> hmap = readcsv.hmap;
        Map<String, Map<Integer,Integer>> amap = readcsv.amap;
        for (int i = 0; i < 100; i++) {
            int a = abc.result("Liverpool", "Chelsea", hmap, amap);
            System.out.println(a);
        }
    }
}



