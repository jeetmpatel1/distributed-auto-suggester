package com.simpleengineer.prefixweb.service;

import data.PrefixTrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Communicate with the current running instance of the trie representing the current range
 */
@Service
public class TrieService {
        private PrefixTrie prefixTrie;

        @Autowired
        private RedisService redisService;

        @Autowired
        private RedisTemplate<String,String> redisTemplate;

        public TrieService(){
                this.prefixTrie = new PrefixTrie();
        }

        public List<String> getMatchingPhrases(String prefix){
                Set<String> results = checkCachePrefix(prefix);
                if(results.size() == 0 ){
                        return prefixTrie.findMatchingPhrases(prefix);
                }
                return  new ArrayList<>(results);
        }

        public void insertPhrases(String prefix){
               prefixTrie.insertPrefix(prefix);
               insertInCache(prefix);

               // Once it's been inserted for the first time into the trie,
                // we will want ot inject it into the cache.
        }

        private Set<String> checkCachePrefix(String prefix){
                Set<String> results = redisTemplate.opsForZSet().reverseRange(prefix,0,-1);
                return results;
        }
        private  void insertInCache(String prefix){
                redisTemplate.opsForZSet().add(prefix,prefix,1);
        }
}
