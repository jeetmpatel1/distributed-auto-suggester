package playground;

import data.PrefixTrie;

public class Driver {
    public static void main(String[] args) {
        PrefixTrie prefix = new PrefixTrie();
        prefix.insertPrefix("ryan");
        prefix.insertPrefix("rye");

        prefix.findSubtree("ry");
    }
}
