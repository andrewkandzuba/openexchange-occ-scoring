package io.openexchange.occ.scoring;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileScoreFactory implements ScoreFactory {
    @Override
    public Score load(URL data) {
        try {
            return new FileScore(buildTrie(data));
        } catch (IOException ex){
            System.err.println(ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    // @ToDo: Refactor to achieve O(N) runtime.
    private Trie buildTrie(URL data) throws IOException {
        String text = IOUtils.toString(data, Charset.defaultCharset());
        List<String> names = Arrays.stream(text.split(",")).map(s -> s.substring(1, s.length()-1)).collect(Collectors.toList());
        Trie trie = new Trie();
        for (int i = 0; i < names.size(); i++){
            trie.insert(names.get(i), i);
        }
        return trie;
    }
}
