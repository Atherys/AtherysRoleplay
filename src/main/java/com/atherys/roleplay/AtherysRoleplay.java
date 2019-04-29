package com.atherys.roleplay;

import com.atherys.core.command.CommandService;
import com.atherys.core.event.AtherysHibernateConfigurationEvent;
import com.atherys.core.event.AtherysHibernateInitializedEvent;
import com.atherys.roleplay.card.CharacterCard;
import com.atherys.roleplay.command.card.MasterCardCommand;
import com.atherys.roleplay.command.misc.RollCommand;
import com.atherys.roleplay.facade.CardFacade;
import com.atherys.roleplay.facade.RoleplayMessagingFacade;
import com.atherys.roleplay.facade.RollFacade;
import com.atherys.roleplay.listener.PlayerListener;
import com.atherys.roleplay.persistence.CardRepository;
import com.atherys.roleplay.service.CardService;
import com.atherys.roleplay.service.MenuService;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.event.game.state.GameStoppingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

@Plugin(id = AtherysRoleplay.ID, name = AtherysRoleplay.NAME, description = AtherysRoleplay.DESCRIPTION, version = AtherysRoleplay.VERSION)
public class AtherysRoleplay {
    static final String ID = "atherysroleplay";
    static final String NAME = "A'therys Roleplay";
    static final String DESCRIPTION = "A roleplay plugin written for the A'therys Horizons server.";
    static final String VERSION = "1.0.0";

    @Inject
    private Logger logger;

    @Inject
    private PluginContainer container;

    @Inject
    private Injector spongeInjector;

    private Components components;

    private Injector townsInjector;

    private static AtherysRoleplay instance;
    private static boolean init = false;

    private void init() {
        instance = this;

        components = new Components();
        townsInjector = spongeInjector.createChildInjector(new AtherysRoleplayModule());
        townsInjector.injectMembers(components);
        getCardRepository().initCache();
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
        getCardRepository().flushCache();
    }

    @Listener
    public void onHibernateInit(AtherysHibernateInitializedEvent event) {
        init();
    }

    @Listener
    public void onHibernateConfiguration(AtherysHibernateConfigurationEvent event) {
        event.registerEntity(CharacterCard.class);
    }


    @Listener
    public void onInit(GameInitializationEvent event) {
        init();
    }

    @Listener
    public void onStart(GameStartingServerEvent event) {
        if (init) start();
    }

    @Listener
    public void onStop(GameStoppingServerEvent event) {
        if (init) stop();
    }

    public static AtherysRoleplay getInstance() {
        return instance;
    }

    public Logger logger() {
        return logger;
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

    public RoleplayMessagingFacade getMessagingFacade() {
        return components.messagingFacade;
    }

    public String getDirectory(){
        return "config/" + ID;
    }

    public static PluginContainer getContainer() {
        return getInstance().container;
    }

    public RollFacade getRollFacade() {
        return components.rollFacade;
    }

    public CardRepository getCardRepository() {
        return components.cardRepository;
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

        @Inject
        private RoleplayMessagingFacade messagingFacade;

        @Inject
        private RollFacade rollFacade;

        @Inject
        private CardRepository cardRepository;
    }
}
