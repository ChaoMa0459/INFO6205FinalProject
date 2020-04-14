package edu.neu.coe.ranking;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ReadCsvSpec {

    @Test
    public void testHomeMap() throws Exception {
        ReadCsv readcsv = new ReadCsv();
        Map<String, Map<Integer,Integer>> hmap = readcsv.hmap;
        int size = hmap.size();
        assert (size == 29);
        for (Map.Entry<Integer, Integer> count: hmap.get("Liverpool").entrySet()) {
            System.out.println(count.toString());
        }
        assert (hmap.get("Liverpool").get(0) == 8);
    }

    @Test
    public void testAwayMap() throws Exception {
        ReadCsv readcsv = new ReadCsv();
        Map<String, Map<Integer,Integer>> amap = readcsv.amap;
        int size = amap.size();
        assert (size == 29);
        for (Map.Entry<Integer, Integer> count: amap.get("Liverpool").entrySet()) {
            System.out.println(count.toString());
        }
        assert (amap.get("Liverpool").get(0) == 16);
    }
}
