package com.atherys.roleplay;

import com.atherys.roleplay.facade.CardFacade;
import com.atherys.roleplay.facade.RoleplayMessagingFacade;
import com.atherys.roleplay.persistence.RoleplayCache;
import com.atherys.roleplay.service.CardService;
import com.atherys.roleplay.service.MenuService;
import com.atherys.roleplay.persistence.CardRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.user.UserStorageService;

public class AtherysRoleplayModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AtherysRoleplayConfig.class);

        bind(RoleplayCache.class);

        bind(CardRepository.class);
        bind(CardService.class);
        bind(MenuService.class);
        bind(CardFacade.class);
        bind(RoleplayMessagingFacade.class);

        bind(UserStorageService.class).toProvider(() -> {
            return Sponge.getServiceManager().provide(UserStorageService.class).orElse(null);
        }).in(Scopes.SINGLETON);
    }
}
