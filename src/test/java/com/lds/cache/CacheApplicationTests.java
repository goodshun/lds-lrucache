package com.lds.cache;

import com.lds.cache.component.LruCacheQueen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {


    private LruCacheQueen cacheQueen = new LruCacheQueen(5);

    @Test
    public void contextLoads() {
        cacheQueen.put("1","1");

        cacheQueen.put("2","2");

        cacheQueen.put("3","3");

        cacheQueen.put("4","4");

        cacheQueen.put("5","5");

        System.err.print(cacheQueen.toString());


        cacheQueen.get("3");

        System.err.print(cacheQueen.toString());

        cacheQueen.put("6","6");

        System.err.print(cacheQueen.toString());
    }

}
