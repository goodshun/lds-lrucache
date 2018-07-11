package com.lds.cache;

import com.lds.cache.component.CacheManager;
import com.lds.cache.component.LruCacheQueen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheApplicationTests {



    @Test
    public void contextLoads() {
        LruCacheQueen lruCacheQueen = CacheManager.getCache();
        System.err.print("");
    }

}
