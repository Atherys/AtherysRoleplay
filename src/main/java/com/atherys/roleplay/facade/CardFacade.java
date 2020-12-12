package com.atherys.roleplay.facade;

import com.atherys.roleplay.AtherysRoleplayConfig;
import com.atherys.roleplay.entity.CharacterCard;
import com.atherys.roleplay.card.Nation;
import com.atherys.roleplay.menu.Menus;
import com.atherys.roleplay.service.CardService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;

import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class CardFacade {
    @Inject
    CardService cardService;

    @Inject
    RoleplayMessagingFacade messagingFacade;

    private AtherysRoleplayConfig config;

    private Map<String, Nation> choices;

    @Inject
    CardFacade(AtherysRoleplayConfig config) {
        this.config = config;
        choices = config.NATIONS.stream()
                .collect(Collectors.toMap(Nation::getName, nation -> nation));
    }

    public void resetCard(Player source) {
        cardService.resetCard(cardService.getOrCreate(source));
        messagingFacade.info(source, "Card reset.");
    }

    public void showCard(Player source) {
        BookView cardView = cardService.getOrCreate(source).toView();
        source.sendBookView(cardView);
    }

    public void setCardAge(Player source, int age) throws CommandException {
        if (age <= config.MAXIMUM_AGE) {
            cardService.setCardAge(cardService.getOrCreate(source), age);
            messagingFacade.info(source, "Character age set.");
        } else if (age < 1) {
            throw new CommandException(Text.of("Age of " + age + " is less than 1."));
        } else {
            throw new CommandException(Text.of("Age of " + age + " is larger than maximum allowed age of " + config.MAXIMUM_AGE + "."));
        }
    }

    public void setCardDescription(Player source, String description, boolean delete) {
        if (delete) {
            cardService.setCardDescription(cardService.getOrCreate(source), description);
        } else {
            cardService.addToCardDescription(cardService.getOrCreate(source), description);
        }
        messagingFacade.info(source, "Character description updated.");
    }

    public void setCardNation(Player source, Nation nation) {
        cardService.setCardNation(cardService.getOrCreate(source), nation.getName());
        messagingFacade.nationMessage(source, nation);
    }

    public void setCardName(Player source, String name) throws CommandException {
        if (name.length() <= config.MAXIMUM_NAME_LENGTH) {
            cardService.setCardName(cardService.getOrCreate(source), name);
            messagingFacade.info(source, "Character name set.");
        } else {
            throw new CommandException(Text.of("Name " + name + " is longer than maximum name length of " + config.MAXIMUM_NAME_LENGTH + "."));
        }
    }

    public void setCardNickname(Player source, String nick) throws CommandException {
        if (nick.length() <= config.MAXIMUM_NICK_LENGTH) {
            cardService.setCardNickname(cardService.getOrCreate(source), nick);
            messagingFacade.info(source, "Character nickname set.");
        } else {
            throw new CommandException(Text.of("Nickname " + nick + " is longer than maximum nickname length of " + config.MAXIMUM_NICK_LENGTH + "."));
        }
    }

    public Map<String, Nation> getChoices() {
        return choices;
    }

    public void onPlayerShiftRightClick(Player target, Player source) {
        CharacterCard card = cardService.getOrCreate(target);
        source.sendBookView(card.toView());
    }

    public void openCardMenu(Player source) {
        Menus.cardMenu.open(source);
    }
}
