package playground;

import data.PrefixTrie;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        PrefixTrie prefix = new PrefixTrie();
        prefix.insertPrefix("ryan");
        prefix.insertPrefix("rye");
        prefix.insertPrefix("ryanne");
        prefix.insertPrefix("run");
        List<String> results = prefix.findMatchingPhrases("ry");
        System.out.println("hi");
    }
}
