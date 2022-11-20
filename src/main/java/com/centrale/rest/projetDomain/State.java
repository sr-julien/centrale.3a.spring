package com.centrale.rest.projetDomain;

public interface State {
    void start();
    void wish();
    void drop();
    void consume(int episode_watched);
    void finnish();
}
