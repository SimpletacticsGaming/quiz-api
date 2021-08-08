package de.simpletactics.formatParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.simpletactics.game.Game;
import de.simpletactics.game.JsonGame;

public class JsonParser implements Parser{

    @Override
    public void parse(Game game) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(game));
    }

    public void parse(JsonGame game) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(game));
    }

    @Override
    public <T> T getParsedDoc() {
        return null;
    }
}
