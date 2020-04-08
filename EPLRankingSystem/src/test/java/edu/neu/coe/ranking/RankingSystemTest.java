/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.ranking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RankingSystemTest {

    @Test
    public void testPrediction() throws Exception {
        RankingSystem rs = new RankingSystem();
        assertEquals("Win", rs.predict("Liverpool", "Arsenal"));
    }

}