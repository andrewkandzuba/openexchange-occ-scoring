package io.openexchange.occ.scoring;

import java.net.URL;

public interface ScoreFactory {
    Score load(URL data);
}
