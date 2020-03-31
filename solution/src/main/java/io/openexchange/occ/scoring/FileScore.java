package io.openexchange.occ.scoring;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class FileScore implements Score {

    private final Trie trie;

    FileScore(Trie trie) {
        this.trie = Objects.requireNonNull(trie);
    }

    @Override
    public int total() {
        AtomicInteger score = new AtomicInteger(0);
        AtomicInteger order = new AtomicInteger(1);
        preOrderScore(trie.root(), score, order);
        return score.get();
    }

    private void preOrderScore(Trie.TrieNode node, AtomicInteger score, AtomicInteger order) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.children().length; i++) {
            if (node.children()[i] != null) {
                if (node.children()[i].pos() != -1 ){
                    score.set(score.get() + (node.children()[i].score() * order.get()));
                    order.incrementAndGet();
                }
                preOrderScore(node.children()[i], score, order);
            }
        }
    }
}
