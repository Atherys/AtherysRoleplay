package com.atherys.roleplay.persistence;

import com.atherys.core.db.CachedHibernateRepository;
import com.atherys.roleplay.cards.CharacterCard;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.UUID;

@Singleton
public class CardRepository extends CachedHibernateRepository<CharacterCard, UUID> {

    private RoleplayCache cache;

    @Inject
    protected CardRepository(RoleplayCache cache) {
        super(CharacterCard.class);
        super.cache = cache.getCardCache();
        this.cache = cache;
    }
}
