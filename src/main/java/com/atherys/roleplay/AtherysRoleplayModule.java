package com.atherys.roleplay;

import com.atherys.roleplay.card.CharacterCard;
import com.atherys.roleplay.facade.CardFacade;
import com.atherys.roleplay.facade.RoleplayMessagingFacade;
import com.atherys.roleplay.facade.RollFacade;
import com.atherys.roleplay.persistence.CardRepository;
import com.atherys.roleplay.service.CardService;
import com.atherys.roleplay.service.MenuService;
import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.user.UserStorageService;

public class AtherysRoleplayModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AtherysRoleplayConfig.class);

        bind(CardRepository.class);
        bind(CardService.class);
        bind(MenuService.class);

        bind(CardFacade.class);
        bind(RoleplayMessagingFacade.class);
        bind(RollFacade.class);

        bind(new TypeLiteral<Class<CharacterCard>>() {}).toInstance(CharacterCard.class);

        bind(UserStorageService.class).toProvider(() -> {
            return Sponge.getServiceManager().provide(UserStorageService.class).orElse(null);
        }).in(Scopes.SINGLETON);
    }
}
