package io.openexchange.occ.scoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTrie {

    @Test
    void indexInsert() {
        var index = new Trie();
        index.insert("ABC", 0);
        index.insert("ABB", 1);
        index.insert("DBS", 2);
        index.insert("AAA", 3);
        index.insert("ZZZ", 4);

        Assertions.assertNotNull(index);
    }

    @Test
    void findNode() {
        String[] arr = {"MARY", "PATRICIA", "LINDA", "BARBARA", "VINCENZO", "SHON", "LYNWOOD", "JERE", "HAI"};

        var index = new Trie();
        for (int i = 0; i < arr.length; i++) {
            index.insert(arr[i], i);
        }

        var n = index.findNode("LINDA");

        Assertions.assertNotNull(n);
        Assertions.assertEquals(40, n.score());
        Assertions.assertEquals(2, n.pos());
        Assertions.assertNotNull(n.children());
    }

    @Test
    void search() {
        var index = new Trie();
        index.insert("ABC", 0);
        index.insert("ABB", 1);
        index.insert("DBS", 2);
        index.insert("AAA", 3);
        index.insert("ZZZ", 4);

        Assertions.assertTrue(index.search("ABC"));
        Assertions.assertTrue(index.search("ZZZ"));
        Assertions.assertFalse(index.search("AA"));
        Assertions.assertFalse(index.search("AB"));
        Assertions.assertFalse(index.search("ABCDE"));
    }

    @Test
    void searchPrefix() {
        var index = new Trie();
        index.insert("ABC", 0);
        index.insert("ABB", 1);
        index.insert("DBS", 2);
        index.insert("AAA", 3);
        index.insert("ZZZ", 4);

        Assertions.assertTrue(index.searchPrefix("ABC"));
        Assertions.assertTrue(index.searchPrefix("ZZZ"));
        Assertions.assertTrue(index.searchPrefix("AA"));
        Assertions.assertTrue(index.searchPrefix("AB"));
        Assertions.assertFalse(index.searchPrefix("ABCDE"));
        Assertions.assertFalse(index.searchPrefix("AE"));
    }
}
