package com.atherys.roleplay.service;

import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.cards.CharacterCard;
import com.atherys.roleplay.persistence.CardRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class CardService {

    @Inject
    CardRepository cardRepository;

    CardService() {
    }

    protected CharacterCard getOrCreate(UUID player, String playerName) {
        Optional<CharacterCard> card = cardRepository.findById(player);

        if (card.isPresent()) {
            return card.get();
        } else {
            CharacterCard newCharacterCard = new CharacterCard(player, playerName);
            newCharacterCard.setName(playerName);
            cardRepository.saveOne(newCharacterCard);

            return newCharacterCard;
        }
    }

    public void println() {
        AtherysRoleplay.getLogger().info("Heh");
    }
}
