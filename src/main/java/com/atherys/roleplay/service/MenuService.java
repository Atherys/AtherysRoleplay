package com.atherys.roleplay.service;

import com.google.inject.Singleton;
import org.spongepowered.api.entity.living.player.Player;

import java.util.*;
import java.util.function.Consumer;

@Singleton
public class MenuService {
    private Map<UUID, Consumer<String>> sessions = new HashMap<>();
    private List<UUID> bookViewers = new ArrayList<>();

    private static MenuService instance = new MenuService();

    MenuService() {
    }

    public static MenuService getInstance() {
        return instance;
    }

    public void startBookView(Player player) {
        bookViewers.add(player.getUniqueId());
    }

    public boolean isViewingBook(Player player) {
        return bookViewers.contains(player.getUniqueId());
    }

    public void endBookView(Player player) {
        bookViewers.remove(player.getUniqueId());
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
