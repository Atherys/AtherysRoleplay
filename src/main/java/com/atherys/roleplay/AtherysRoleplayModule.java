package com.atherys.roleplay;

import com.atherys.roleplay.facade.CardFacade;
import com.atherys.roleplay.persistence.RoleplayCache;
import com.atherys.roleplay.service.CardService;
import com.atherys.roleplay.service.MenuService;
import com.atherys.roleplay.persistence.CardRepository;
import com.google.inject.AbstractModule;

public class AtherysRoleplayModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RoleplayCache.class);

        bind(CardRepository.class);
        bind(CardService.class);
        bind(CardFacade.class);
        bind(MenuService.class);
    }
}
