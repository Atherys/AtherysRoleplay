package com.atherys.roleplay.persistence;

import com.atherys.core.db.HibernateRepository;
import com.atherys.roleplay.cards.CharacterCard;

import java.util.UUID;

public class CardRepository extends HibernateRepository<CharacterCard, UUID> {

    public CardRepository(Class<CharacterCard> persistable) {
        super(persistable);
    }
}
