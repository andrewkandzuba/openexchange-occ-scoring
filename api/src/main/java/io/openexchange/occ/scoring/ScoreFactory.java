package io.openexchange.occ.scoring;

import java.net.URI;

public interface ScoreFactory {
    Score load(URI data);
}
