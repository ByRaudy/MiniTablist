package dev.jxnnik.minitablist.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Config {

    private final File file;
    private final Gson gson;
    private final ExecutorService pool;
    private JsonObject json;

    public Config() {
        this.file = new File("plugins/MiniTablist", "config.json");
        this.gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        this.pool = Executors.newFixedThreadPool(2);
        this.initFile();
    }

    private void initFile() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try (final PrintWriter writer = new PrintWriter(file)) {
                createConfig();
                writer.print(gson.toJson(json));
            } catch (FileNotFoundException ignored) { }
        } else {
            try {
                json = new JsonParser().parse(new FileReader(file)).getAsJsonObject();
            } catch (FileNotFoundException ignored) { }
        }
    }

    public void save() {
        pool.execute(() -> {
            try (OutputStreamWriter writer = new OutputStreamWriter(Files.newOutputStream(file.toPath()), StandardCharsets.UTF_8)) {
                gson.toJson(json, writer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public JsonObject getJson() {
        return json;
    }

    private void createConfig() {
        json = new JsonObject();
        JsonObject settings = new JsonObject();
        JsonObject tablist = new JsonObject();

        settings.addProperty("enabled", true);

        json.add("settings", settings);

        tablist.addProperty("header", "\n <rainbow>MiniTablist - v1.0</rainbow> \n <gradient:#d67624:#fed13f>Online players: %online_players%/%max_players%</gradient> \n");
        tablist.addProperty("footer", "\n <gradient:#ec5fed:#b32090>Plugin by Jxnnik(.dev)</gradient> \n <animation:#fed13f:#d20606:0>This is a animation</animation>\n");

        json.add("tablist", tablist);
    }
}