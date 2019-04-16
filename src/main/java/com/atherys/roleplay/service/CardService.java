package com.atherys.roleplay.service;

import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.cards.CharacterCard;
import com.atherys.roleplay.persistence.CardRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.service.user.UserStorageService;

import java.util.Optional;
import java.util.UUID;

@Singleton
public class CardService {

    @Inject
    CardRepository cardRepository;

    @Inject
    UserStorageService userStorageService;

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

    public CharacterCard getOrCreate(User user) {
        return getOrCreate(user.getUniqueId(), user.getName());
    }
}
