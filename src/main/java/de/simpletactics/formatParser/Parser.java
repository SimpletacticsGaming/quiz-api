package de.simpletactics.formatParser;

import de.simpletactics.game.Game;

public interface Parser {

    public void parse(Game game);
    public <T> T getParsedDoc();

}
