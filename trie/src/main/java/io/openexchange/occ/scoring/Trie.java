package io.openexchange.occ.scoring;

public class Trie {

    private static final int MAX_CHAR = 26;
    private static final char START_CHAR = 'A';

    private TrieNode root = new TrieNode();

    public void insert(String str, int pos) {
        var children = root.children;
        var totalScore = 0;

        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - START_CHAR;
            totalScore += c + 1;
            var n = children[c];
            if (n == null) {
                n = new TrieNode();
                children[c] = n;
            }
            children = n.children;
            if (i == str.length() - 1) {
                if (n.pos == -1) {
                    n.pos = pos;
                    n.score = totalScore;
                }
            }
        }
    }

    public boolean search(String word){
        var n = findNode(word);
        if (n ==null){
            return false;
        }
        return n.pos != -1;
    }

    public boolean searchPrefix(String prefix) {
        var n = findNode(prefix);
        return n != null;
    }

    TrieNode findNode(String str) {
        var t = root;
        for (int i = 0; i < str.length(); i++) {
            int c = str.charAt(i) - START_CHAR;
            if (t.children[c] != null) {
                t = t.children[c];
            } else {
                return null;
            }
        }
        if (t == root) {
            return null;
        }
        return t;
    }

    public static class TrieNode {
        private TrieNode[] children = new TrieNode[MAX_CHAR];
        private int pos = -1;
        private int score = 0;

        public int score() {
            return score;
        }
    }

}
