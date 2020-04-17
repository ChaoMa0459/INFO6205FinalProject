/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.ranking;

import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankingSystemSpec {

    @Test
    public void testGetTeams() throws Exception {
        RankingSystem r = new RankingSystem();
        Set<String> teams = r.getTeams();
        assertEquals(teams.size(), 20);
    }

    @Test
    public void testCurrentSeasonRanking() throws Exception {
        RankingSystem r = new RankingSystem();
        List<Map.Entry<String, Integer>> rankingList = r.generateCurrentSeasonRanking();
        assertEquals(rankingList.size(), 20);
    }

    @Test
    public void testNewSeasonRanking() throws Exception {
        RankingSystem r = new RankingSystem();
        List<Map.Entry<String, Integer>> rankingList = r.generateNewSeasonRanking();
        assertEquals(rankingList.size(), 20);
    }

    @Test
    public void testCurrentSeasonChampion() throws Exception {
        RankingSystem r = new RankingSystem();
        List<Map.Entry<String, Integer>> rankingList = r.generateCurrentSeasonRanking();
        String champion = rankingList.get(0).getKey();
        assertEquals(champion, "Liverpool");
    }

}