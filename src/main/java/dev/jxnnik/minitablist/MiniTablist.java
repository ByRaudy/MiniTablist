package dev.jxnnik.minitablist;

import dev.jxnnik.minitablist.config.Config;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class MiniTablist {

    private Config pluginConfig;

    public MiniTablist() {
        this.pluginConfig = new Config();
    }

    public String getHeader() {
        return pluginConfig.getJson().get("tablist").getAsJsonObject().get("header").getAsString();
    }

    public String getFooter() {
        return pluginConfig.getJson().get("tablist").getAsJsonObject().get("footer").getAsString();
    }
}