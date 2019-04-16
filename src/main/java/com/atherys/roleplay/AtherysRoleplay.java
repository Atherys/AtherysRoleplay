package com.atherys.roleplay;

import com.atherys.core.command.CommandService;
import com.atherys.roleplay.command.card.MasterCardCommand;
import com.atherys.roleplay.command.misc.RollCommand;
import com.atherys.roleplay.facade.CardFacade;
import com.atherys.roleplay.listeners.PlayerListener;
import com.atherys.roleplay.service.CardService;
import com.atherys.roleplay.service.MenuService;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

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

    @Inject
    private PluginContainer container;

    @Inject
    private Injector spongeInjector;

    private Components components;

    private Injector townsInjector;

    private static AtherysRoleplay instance;
    private static boolean init = false;

    private void init () {
        instance = this;

        components = new Components();
        townsInjector = spongeInjector.createChildInjector(new AtherysRoleplayModule());
        townsInjector.injectMembers(components);

        init = true;
    }

    private void start() {
        Sponge.getEventManager().registerListeners(this, new PlayerListener());
        try {
            CommandService.getInstance().register(new MasterCardCommand(), this);
            CommandService.getInstance().register(new RollCommand(), this);
        } catch (CommandService.AnnotatedCommandException e) {
            e.printStackTrace();
        }
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

    public static AtherysRoleplay getInstance() {
        return instance;
    }

    public static Logger getLogger() {
        return getInstance().logger();
    }

    public static Game getGame() {
        return getInstance().game;
    }

    public Logger logger() {
        return logger;
    }

    public static PluginContainer getContainer() {
        return getInstance().container;
    }

    public AtherysRoleplayConfig getConfig() {
        return components.config;
    }

    public MenuService getMenuService() {
        return components.menuService;
    }

    public CardService getCardService() {
        return components.cardService;
    }

    public CardFacade getCardFacade() {
        return components.cardFacade;
    }

    private static class Components {
        @Inject
        private AtherysRoleplayConfig config;

        @Inject
        private MenuService menuService;

        @Inject
        private CardService cardService;

        @Inject
        private CardFacade cardFacade;
    }
}
