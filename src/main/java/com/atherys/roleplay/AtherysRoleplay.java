package com.atherys.roleplay;

import com.atherys.core.command.CommandService;
import com.atherys.roleplay.commands.card.MasterCardCommand;
import com.atherys.roleplay.commands.misc.RollCommand;
import com.atherys.roleplay.listeners.PlayerListener;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;

import java.io.IOException;

@Plugin( id = AtherysRoleplay.ID, name = AtherysRoleplay.NAME, description = AtherysRoleplay.DESCRIPTION, version = AtherysRoleplay.VERSION )
public class AtherysRoleplay {
    static final String ID = "atherysroleplay";
    static final String NAME = "A'therys Roleplay";
    static final String DESCRIPTION = "A roleplay plugin written for the A'therys Horizons server.";
    static final String VERSION = "1.0.0";

    @Inject
    private Logger logger;

    @Inject
    private Game game;

    private static AtherysRoleplayConfig config;
    private static AtherysRoleplay instance;
    private static boolean init = false;

    private CardManager cardManager;

    private void init () {
        instance = this;
        try{
            config = new AtherysRoleplayConfig(getDirectory(), "config.conf");
            config.init();
        }catch(IOException e){
            init = false;
            e.printStackTrace();
            return;
        }

        if(config.IS_DEFAULT){
            logger.error("The AtherysRoleplay config is set to default. Edit the default config settings and change 'isDefault' to false.");
        }

        init = true;
    }

    private void start() {
        this.cardManager = CardManager.getInstance();
        cardManager.loadAll();

        Sponge.getEventManager().registerListeners(this, new PlayerListener());

        try{
            CommandService.getInstance().register(new MasterCardCommand(), this);
            CommandService.getInstance().register(new RollCommand(), this);
        }catch(CommandService.AnnotatedCommandException e){
            e.printStackTrace();
        }
    }


    private void stop() {
        CardManager.getInstance().saveAll();
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

    public static AtherysRoleplay getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return getInstance().logger();
    }

    public static AtherysRoleplayConfig getConfig(){
        return config;
    }

    public static CardManager getCardManager(){
        return getInstance().cardManager;
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

    public String getDirectory(){
        return "config/" + ID;
    }
}
