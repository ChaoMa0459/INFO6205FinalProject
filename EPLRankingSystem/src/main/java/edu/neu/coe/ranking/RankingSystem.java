package edu.neu.coe.ranking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class RankingSystem {

    String osName = System.getProperty("os.name");
    boolean ifMac = osName.contains("Mac");
    String p = ifMac ? "src/main/resources/EPL20192020.csv": "src\\main\\resources\\EPL20192020.csv";
    String schedule = ifMac ? "src/main/resources/epl-2019-GMTStandardTime.csv": "src\\main\\resources\\epl-2019-GMTStandardTime.csv";

    /**
     * getTeams method
     */
    public Set<String> getTeams() {
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
     * generateCurrentSeasonRanking method
     * generate standings based on current 2019/2020 standings
     */
    public List<Map.Entry<String, Integer>> generateCurrentSeasonRanking() {
        try {
            Set<String> teams = getTeams();
            int size = teams.size();
            String[] teamArr = new String[size];
            teamArr = teams.toArray(teamArr);

            ReadCsv readcsv = new ReadCsv();
            Map<String, Map<Integer, Integer>> hmap = readcsv.hmap;
            Map<String, Map<Integer,Integer>> amap = readcsv.amap;

            Prediction predictionObj = new Prediction();

            Map<String, Integer> rankingMap = new HashMap<>();
            for (String team : teamArr) {
                rankingMap.put(team, 0);
            }

            BufferedReader reade = new BufferedReader(new FileReader(schedule));
            String line = null;
            int index = 0;
            while((line = reade.readLine()) != null){
                if(index > 0) {
                    String item[] = line.split(",");
                    String homeTeam = item[3];
                    String awayTeam = item[4];
                    // past games
                    if (item.length >= 6) {
                        String result = item[5];
                        int hGoal = Character.getNumericValue(result.charAt(0));
                        int aGoal = Character.getNumericValue(result.charAt(4));
                        int homeRes;
                        if (hGoal > aGoal) {
                            homeRes = 3;
                        } else if (hGoal == aGoal) {
                            homeRes = 1;
                        } else {
                            homeRes = 0;
                        }
                        int awayRes = homeRes == 1 ? 1: 3 - homeRes;

//                        System.out.println(index + " " + homeTeam + " " + awayTeam);
                        rankingMap.put(homeTeam, rankingMap.get(homeTeam) + homeRes);
                        rankingMap.put(awayTeam, rankingMap.get(awayTeam) + awayRes);
                    }
                    // future games
                    else {
                        int homeRes = predictionObj.result(homeTeam, awayTeam, hmap, amap);
                        int awayRes = homeRes == 1 ? 1: 3 - homeRes;
                        rankingMap.put(homeTeam, rankingMap.get(homeTeam) + homeRes);
                        rankingMap.put(awayTeam, rankingMap.get(awayTeam) + awayRes);
                    }
                }
                index++;
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

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * generateNewSeasonRanking method
     * generate a whole new 38 games season ranking
     */
    public List<Map.Entry<String, Integer>> generateNewSeasonRanking() {
        Set<String> teams = getTeams();
        int size = teams.size();
        String[] teamArr = new String[size];
        teamArr = teams.toArray(teamArr);

        ReadCsv readcsv = new ReadCsv();
        Map<String, Map<Integer, Integer>> hmap = readcsv.hmap;
        Map<String, Map<Integer,Integer>> amap = readcsv.amap;

        Prediction predictionObj = new Prediction();

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
                int homeRes = predictionObj.result(homeTeam, awayTeam, hmap, amap);
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
    public void printRanking(List<Map.Entry<String, Integer>> rankingList) {
        System.out.format("%2s%17s%10s\n", "#", "Team", "Points");
        int i = 1;
        for (Map.Entry<String, Integer> e : rankingList) {
            System.out.format("%2d%17s%10s\n", i++, e.getKey(), e.getValue());
        }
    }

    public static void main(String[] args) {
        RankingSystem r = new RankingSystem();

//        List<Map.Entry<String, Integer>> rankingList1 = r.generateNewSeasonRanking();
//        r.printRanking(rankingList1);

        List<Map.Entry<String, Integer>> rankingList2 = r.generateCurrentSeasonRanking();
        r.printRanking(rankingList2);
    }
}
