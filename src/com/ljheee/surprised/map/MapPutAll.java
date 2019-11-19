package com.ljheee.surprised.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lijianhua04 on 2019/11/17.
 */
public class MapPutAll {


    public static void main(String[] args) {
        Map<Long, Map<Long, String>> map1 = new HashMap<>();
        Map<Long, String> subMap1 = new HashMap<>();
        subMap1.put(1L, "11");
        map1.put(3380L, subMap1);

        Map<Long, Map<Long, String>> map2 = new HashMap<>();
        Map<Long, String> subMap2 = new HashMap<>();
        subMap2.put(2L, "22");
        map2.put(3380L, subMap2);


        Map<Long, Map<Long, String>> mapAll = new HashMap<>();
//        mapAll.putAll(map1);
//        mapAll.putAll(map2);

        mapPutAll(mapAll, map1);
        mapPutAll(mapAll, map2);

        System.out.println(mapAll);
    }

    /**
     * 将 subMap 添加到全局mapAll
     * 注意 key相同时，value不用覆盖
     *
     * @param mapAll
     * @param subMap
     */
    private static void mapPutAll(Map<Long, Map<Long, String>> mapAll, Map<Long, Map<Long, String>> subMap) {

        for (Map.Entry<Long, Map<Long, String>> entry : subMap.entrySet()) {
            Long skuId = entry.getKey();

            Map<Long, String> shelfQuantityMap = mapAll.get(skuId);
            if (shelfQuantityMap == null) {
                mapAll.put(skuId, entry.getValue());
            } else {
                Map<Long, String> valueMap = entry.getValue();
                for (Map.Entry<Long, String> en : valueMap.entrySet()) {
                    shelfQuantityMap.put(en.getKey(), en.getValue());
                }
            }
        }
    }

    private static void mapPutAll0(Map<Long, Map<Long, String>> mapAll, Map<Long, Map<Long, String>> subMap) {

        for (Map.Entry<Long, Map<Long, String>> entry : subMap.entrySet()) {
            Long skuId = entry.getKey();

            Map<Long, String> shelfQuantityMap = mapAll.get(skuId);
            if (shelfQuantityMap == null) {
                mapAll.put(skuId, entry.getValue());
            } else {
                Map<Long, String> valueMap = entry.getValue();
                shelfQuantityMap.putAll(valueMap);
            }
        }
    }
}
