package com.atherys.roleplay;

import com.atherys.roleplay.facade.CardFacade;
import com.atherys.roleplay.service.CardService;
import com.atherys.roleplay.service.MenuService;
import com.google.inject.AbstractModule;

public class AtherysRoleplayModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(CardFacade.class);
        bind(CardService.class);
        bind(MenuService.class);
    }
}
