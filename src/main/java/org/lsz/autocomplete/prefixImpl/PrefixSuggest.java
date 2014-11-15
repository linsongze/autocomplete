package org.lsz.autocomplete.prefixImpl;

import org.lsz.autocomplete.core.Item;
import org.lsz.autocomplete.core.Suggest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsz on 2014/11/15.
 */
public class PrefixSuggest implements Suggest {
    Jedis jedis;
    String KEY = "key";
    String TERMINAL = "*";
    int rangelen = 50;
    public PrefixSuggest(Jedis jedis)
    {
        this.jedis = jedis;
    }
    @Override
    public void add(Item item) {
        Pipeline pipeline = jedis.pipelined();
        String word = item.getTerm();
        for(int i = 1 ; i< word.length()+1;i++){
            pipeline.zadd(KEY,0.,word.substring(0,i));
        }
        pipeline.zadd(KEY,0,word+"*");
        pipeline.exec();
    }

    @Override
    public List<String> suggest(String prefix, int num) {
        ArrayList<String> list = new ArrayList<String>();
        long start = jedis.zrank(KEY,prefix);
        int  rangelen = 50;
        if(start == 0){
            return null;
        }
        while (true){
            for(String word: jedis.zrange(KEY,start,start+rangelen)){
                if(!word.startsWith(prefix)){
                    return list;
                }
                if(word.endsWith("*")){
                    list.add(word.substring(0,word.length()-1));
                }
                if(list.size()>=num)return list;
            }
            start = start+rangelen;

        }
    }
}
