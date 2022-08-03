package com.smile.basic.core.util;

import cn.hutool.core.collection.CollUtil;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CoreUtil {

    //请求url两个集合取并集 coll1 gatewayUrl
    public static List<String> UrlUnionDistinct(String application,Collection<String> coll1,
                                                 Collection<String> coll2){

        coll1 = coll1.stream().filter(x-> x.contains(application))
                .map(v->v.substring(application.length())).collect(Collectors.toList());
        coll1.forEach(System.out::println);
        coll1.addAll(coll2);
        return coll1.stream().distinct().collect(Collectors.toList());
    }

    public static List<String> UrlMinus(String application,Collection<String> coll1,
                                                Collection<String> coll2){

        return coll1.stream().filter(x-> x.contains(application))
                .map(v->v.substring(application.length())).collect(Collectors.toList())
                .stream().filter(coll2::contains).collect(Collectors.toList());
    }


    //两个集合取并集
    @SafeVarargs
    public static <T> List<T> unionAllIsDistinct(boolean distinct, Collection<T> coll1,
                                                 Collection<T> coll2, Collection<T>... otherCols){
        List<T> ts = CollUtil.unionAll(coll1, coll2, otherCols);
        return distinct ? CollUtil.distinct(ts) : ts ;
    }

}
