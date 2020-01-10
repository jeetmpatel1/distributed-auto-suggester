package data;

import java.util.HashMap;
import java.util.Map;

public class PrefixTrie {

    public  static  class TrieNode {
        protected Map<Character,TrieNode> children;
        protected  boolean EOW;
        protected  String phrase;
        protected  int weight;

        public TrieNode(){
            children = new HashMap<Character,TrieNode>();
            EOW = false;
            phrase = "";
            weight = 0;
        }

        public Map<Character,TrieNode> getChildren(){
            return  children;
        }
    }

    private TrieNode root;

    public  PrefixTrie(){
        root = new TrieNode();
    }

    /**
     * Inserts the phrase into the trie
     * @param phrase take a phrase like "ryan" and then recursively inject into the trieNode
     */
    public void insertPrefix(String phrase){
        insertPrefix(phrase,root,0);
    }

    private void insertPrefix(String phrase,TrieNode curr,int index){
        if(phrase.length() == index  ){
            curr.EOW = true;
            curr.phrase = phrase;
            curr.weight = 1;
            return;
        }

        char currChar = phrase.charAt(index);
        TrieNode newNode = curr.children.get(currChar);

        if(newNode == null ){
            newNode = new TrieNode();
            curr.children.put(currChar,newNode);
        }

        insertPrefix(phrase,newNode,index +1  );
    }
}