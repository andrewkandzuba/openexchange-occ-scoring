package io.openexchange.occ.scoring;

public interface Score {
    int total();
    int name(String name) throws NameNotFoundException;
}
