package com.atherys.roleplay.persistence;

import com.atherys.core.db.cache.Cache;
import com.atherys.core.db.cache.SimpleCache;
import com.atherys.roleplay.cards.CharacterCard;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.UUID;

@Singleton
public class RoleplayCache {

    @Inject
    private CardRepository cardRepository;

    private Cache<CharacterCard, UUID> cardCache = new SimpleCache<>();

    public RoleplayCache() {
    }

    public void initCache() {
        cardRepository.initCache();
    }

    public void flushCache() {
        cardRepository.flushCache();
    }

    public Cache<CharacterCard, UUID> getCardCache() {
        return cardCache;
    }
}
