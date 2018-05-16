package com.ljnic.roleplay;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

@Plugin( id = Roleplay.ID, name = Roleplay.NAME, description = Roleplay.DESCRIPTION, version = Roleplay.VERSION )
public class Roleplay {
    static final String ID = "roleplay";
    static final String NAME = "Roleplay";
    static final String DESCRIPTION = "Character cards, dice, and more";
    static final String VERSION = "1.0.0";

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    private static RoleplayConfig config;
    private static Roleplay instance;
    private static boolean init = false;

    private void init () {
        instance = this;

        init = true;
    }

    private void start() {
    }

    private void stop() {
    }

    @Listener
    public void onInit (GameInitializationEvent event) {
        init();
    }

    @Listener
    public void onStart (GameStartingServerEvent event) {
        if ( init ) start();
    }

    @Listener
    public void onStop (GameStoppingServerEvent event) {
        if ( init ) stop();
    }

    public static Roleplay getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return getInstance().logger();
    }

    public static RoleplayConfig getConfig(){
        return config;
    }

    public static Game getGame() {
        return getInstance().game();
    }

    public Logger logger() {
        return logger;
    }

    public Game game() {
        return game;
    }
}
