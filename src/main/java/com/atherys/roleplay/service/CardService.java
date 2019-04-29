package com.atherys.roleplay.service;

import com.atherys.roleplay.card.CharacterCard;
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

    public void resetCard(CharacterCard card) {
        card.resetCard();
        cardRepository.saveOne(card);
    }

    public void setCardAge(CharacterCard card, int cardAge) {
        card.setAge(Integer.toString(cardAge));
        cardRepository.saveOne(card);
    }

    public void setCardDescription(CharacterCard card, String description) {
        card.setDescription(description);
        cardRepository.saveOne(card);
    }

    public void addToCardDescription(CharacterCard card, String description) {
        card.addDescription(description);
        cardRepository.saveOne(card);
    }

    public void setCardName(CharacterCard card, String name) {
        card.setName(name);
        cardRepository.saveOne(card);
    }

    public void setCardNickname(CharacterCard card, String nick) {
        card.setNickname(nick);
    }

    public void setCardNation(CharacterCard card, String name) {
        card.setNationality(name);
    }
}
