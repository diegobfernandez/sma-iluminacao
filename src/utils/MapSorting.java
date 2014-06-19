package utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MapSorting {
    public static <K extends Comparable, V extends Comparable> Map<K,V> sortByValues(Map<K,V> map) {
        List<Map.Entry<K,V>> entries = new LinkedList<>(map.entrySet());
        
        Collections.sort(entries, new Comparator<Map.Entry<K,V>>() {

            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
            
        });
        
        Map<K,V> sortedMap = new LinkedHashMap<>();
        
        for(Map.Entry<K,V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        return sortedMap;
    }
}