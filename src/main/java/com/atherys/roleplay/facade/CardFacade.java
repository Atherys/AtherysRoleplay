package com.atherys.roleplay.facade;

import com.atherys.roleplay.service.CardService;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CardFacade {
    @Inject
    CardService cardService;

    CardFacade() {
    }
}
