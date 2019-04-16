package com.atherys.roleplay.facade;

import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.AtherysRoleplayConfig;
import com.atherys.roleplay.cards.Nation;
import com.atherys.roleplay.service.CardService;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.util.Map;
import java.util.stream.Collectors;

@Singleton
public class CardFacade {
    @Inject
    CardService cardService;

    @Inject
    AtherysRoleplayConfig config;

    @Inject
    RoleplayMessagingFacade messagingFacade;

    private Map<String, Nation> choices = config.NATIONS.stream()
            .collect(Collectors.toMap(Nation::getName, nation -> nation));

    CardFacade() {
    }

    public void resetCard(Player source) {
        cardService.resetCard(cardService.getOrCreate(source));
    }

    public void showCard(Player source) {
        source.sendBookView(cardService.getOrCreate(source).toView());
    }

    public void setCardAge(Player source, int age) throws CommandException {
        if (age <= config.MAXIMUM_AGE) {
            cardService.setCardAge(cardService.getOrCreate(source), age);
            AtherysRoleplay.getInstance().getMessagingFacade().info(source, "Character age set.");
        } else if (age < 1) {
            throw new CommandException(Text.of("Age of " + age + " is less than 1."));
        } else {
            throw new CommandException(Text.of("Age of " + age + " is larger than maximum allowed age of " + config.MAXIMUM_AGE + "."));
        }
    }

    public void setCardDescription(Player source, String description) {
        cardService.setCardDescription(cardService.getOrCreate(source), description);
    }

    public void addToCardDescription(Player source, String description) {
        cardService.addToCardDescription(cardService.getOrCreate(source), description);
    }

    public void setCardNation(Player source, Nation nation) {
        cardService.setCardNation(cardService.getOrCreate(source), nation.getName());
        messagingFacade.nationMessage(source, nation);
    }

    public void setCardName(Player source, String name) throws CommandException {
        if (name.length() <= config.MAXIMUM_NAME_LENGTH) {
            cardService.setCardName(cardService.getOrCreate(source), name);
        } else {
            throw new CommandException(Text.of("Name " + name + " is longer than maximum name length of " + config.MAXIMUM_NAME_LENGTH + "."));
        }
    }

    public void setCardNickname(Player source, String nick) throws CommandException {
        if (nick.length() <= config.MAXIMUM_NICK_LENGTH) {
            cardService.setCardNickname(cardService.getOrCreate(source), nick);
        } else {
            throw new CommandException(Text.of("Nickname " + nick + " is longer than maximum nickname length of " + config.MAXIMUM_NICK_LENGTH+ "."));
        }
    }

    public Map<String, Nation> getChoices() {
        return choices;
    }
}
