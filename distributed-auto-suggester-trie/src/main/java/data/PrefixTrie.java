package data;

import java.util.*;
import java.util.function.Predicate;

public class PrefixTrie {

    public static class TrieNode {
        protected Map<Character, TrieNode> children;
        protected boolean EOW;
        protected String phrase;
        protected int weight;

        public TrieNode() {
            children = new HashMap<Character, TrieNode>();
            EOW = false;
            phrase = "";
            weight = 0;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }
    }

    private TrieNode root;
    private final Predicate<TrieNode> isLeaf = (currNode) -> currNode.children.size() == 0;

    public PrefixTrie() {
        root = new TrieNode();
    }

    /**
     * Inserts the phrase into the trie
     *
     * @param phrase take a phrase like "ryan" and then recursively inject into the trieNode
     */
    public void insertPrefix(String phrase) {
        insertPrefix(phrase, root, 0);
    }

    private void insertPrefix(String phrase, TrieNode curr, int index) {
        if (phrase.length() == index) {
            curr.EOW = true;
            curr.phrase = phrase;
            curr.weight = 1;
            return;
        }

        char currChar = phrase.charAt(index);
        TrieNode newNode = curr.children.get(currChar);

        if (newNode == null) {
            newNode = new TrieNode();
            curr.children.put(currChar, newNode);
        }

        insertPrefix(phrase, newNode, index + 1);
    }

    /**
     * Find the node that begins the end of the input prefix
     *
     * @param prefix a prefix from which we want to find out all the strings
     * @return Ultimately allows aus to recursively search for all the words that match the given prefix
     * <p>
     * Deapth first search
     */
    public TrieNode findSubtree(String prefix) {
        return findSubtree(root, prefix, 0);
    }

    private TrieNode findSubtree(TrieNode currNode, String prefix, int index) {
        if (prefix.length() == index || prefix.length() == 0) {
            return currNode;
        }
        TrieNode tmpNode = currNode.children.get(prefix.charAt(index));
        if (tmpNode == null) {
            return null;
        }

        return findSubtree(tmpNode, prefix, index + 1);
    }

    /**
     * Modified depth first searrch to find all the terminal nodes that represent prefix matched phrases.
     *
     * @param prefix the prefix from which you want to list all the strings
     * @return all the lists starting with prefix
     */
    public List<String> findMatchingPhrases(String prefix) {
        TrieNode subtreeRoot = findSubtree(prefix);
        if (subtreeRoot != null) {
            return findMatchingPhrases(subtreeRoot, new ArrayList<String>());
        }
        return Collections.emptyList();
    }

    private List<String> findMatchingPhrases(TrieNode node, List<String> result) {

        if (node.EOW) {
            result.add(node.phrase);

            if (isLeaf.test(node)) {
                node.weight += 1;
                return result;
            }
        }
        //@formatter:off
        node.children.keySet().stream().forEach(
                child -> findMatchingPhrases(node.children.get(child), result)
        );
        //@formatter:on
        return result;
    }
}