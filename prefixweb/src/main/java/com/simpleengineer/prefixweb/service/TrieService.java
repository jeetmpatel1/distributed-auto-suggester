package com.simpleengineer.prefixweb.service;

import data.PrefixTrie;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Communicate with the current running instance of the trie representing the current range
 */
@Service
public class TrieService {
        private PrefixTrie prefixTrie;

        public TrieService(){
                this.prefixTrie = new PrefixTrie();
        }

        public List<String> getMatchingPhrases(String prefix){
                return prefixTrie.findMatchingPhrases(prefix);
        }

        public void insertPhrases(String prefix){
               prefixTrie.insertPrefix(prefix);
        }
}
