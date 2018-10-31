package com.atherys.roleplay.services;

import org.spongepowered.api.entity.living.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class MenuService {
    private Map<UUID, Consumer<String>> sessions = new HashMap<>();

    private static MenuService instance = new MenuService();

    private MenuService() {
    }

    public static MenuService getInstance() {
        return instance;
    }

    public void startSession(Player player, Consumer<String> action) {
        sessions.put(player.getUniqueId(), action);
    }

    public void endSession(Player player, String value) {
        Consumer<String> action = sessions.remove(player.getUniqueId());

        if (action != null) {
            action.accept(value);
        }
    }

    public boolean inSession(Player player) {
        return sessions.containsKey(player.getUniqueId());
    }
}
