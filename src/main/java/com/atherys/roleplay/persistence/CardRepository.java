package com.atherys.roleplay.persistence;

import com.atherys.core.db.CachedHibernateRepository;
import com.atherys.roleplay.entity.CharacterCard;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.UUID;

@Singleton
public class CardRepository extends CachedHibernateRepository<CharacterCard, UUID> {
    @Inject
    public CardRepository(Class<CharacterCard> persistable) {
        super(persistable);
    }
}
