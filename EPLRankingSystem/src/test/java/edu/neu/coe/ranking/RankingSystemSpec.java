/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.ranking;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankingSystemSpec {

    @Test
    public void testGetTeams() throws Exception {
        RankingSystem sys = new RankingSystem();
        Set<String> teams = sys.getTeams();
        assertEquals(teams.size(), 20);
    }

    @Test
    public void testGenerateRanking() throws Exception {
        RankingSystem sys = new RankingSystem();
        Set<String> teams = sys.getTeams();
        assertEquals(teams.size(), 20);
    }

}