package de.simpletactics.formatParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.simpletactics.game.Game;
import de.simpletactics.game.JsonGame;

public class JsonParser implements Parser{

    public String parse(JsonGame game) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(game);
    }

    @Override
    public <T> T getParsedDoc() {
        return null;
    }
}
