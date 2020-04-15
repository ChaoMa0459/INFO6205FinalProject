package edu.neu.coe.ranking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class RankingSystem {

    /**
     * getTeams method
     */
    public Set<String> getTeams() {
        String osName = System.getProperty("os.name");
        boolean ifMac = osName.contains("Mac");
        String p = ifMac ? "src/main/resources/EPL20192020.csv": "src\\main\\resources\\EPL20192020.csv";

        // set of team names
        Set<String> teams = new HashSet<>();
        try {
            BufferedReader reade = new BufferedReader(new FileReader(p));
            String line = null;
            int index = 0;
            while((line = reade.readLine()) != null){
                if(index > 0) {
                    String item[] = line.split(",");
                    teams.add(item[2]);
                }
                index++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return teams;
    }

    /**
     * generateRanking method
     */
    public List<Map.Entry<String, Integer>> generateRanking() {
        Set<String> teams = getTeams();
        int size = teams.size();
        String[] teamArr = new String[size];
        teamArr = teams.toArray(teamArr);

        ReadCsv readcsv = new ReadCsv();
        Map<String, Map<Integer, Integer>> hmap = readcsv.hmap;
        Map<String, Map<Integer,Integer>> amap = readcsv.amap;

        Team teamObj = new Team();

        Map<String, Integer> rankingMap = new HashMap<>();
        for (String team : teamArr) {
            rankingMap.put(team, 0);
        }

        // select home team
        for (int i = 0; i < size; i++) {
            String homeTeam = teamArr[i];
            // select away team
            for (int j = 0; j < size; j++) {
                if (j == i) continue;
                String awayTeam = teamArr[j];
                // get result
                int homeRes = teamObj.result(homeTeam, awayTeam, hmap, amap);
                int awayRes = homeRes == 1 ? 1: 3 - homeRes;
                rankingMap.put(homeTeam, rankingMap.get(homeTeam) + homeRes);
                rankingMap.put(awayTeam, rankingMap.get(awayTeam) + awayRes);
            }
        }

        List<Map.Entry<String, Integer>> rankingList = new ArrayList<>(rankingMap.entrySet());
        rankingList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                // sorting with values
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        return rankingList;
    }

    /**
     * printRanking method
     */
    public void printRanking() {
        List<Map.Entry<String, Integer>> rankingList = generateRanking();
        for (Map.Entry<String, Integer> e : rankingList) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

    // any attributes or helper functions needed

    public static void main(String[] args) {
        RankingSystem r = new RankingSystem();
        Set<String> teams = r.getTeams();
        System.out.println(teams.size());
        r.printRanking();
    }
}
