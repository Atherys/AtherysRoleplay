package com.atherys.roleplay.card;

import com.atherys.roleplay.entity.CharacterCard;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class CardView {
    private CharacterCard card;

    public CardView(CharacterCard card) {
        this.card = card;
    }

    public BookView toBookView() {
        Text playerText = Text.builder()
                .append(Text.of(TextColors.BLACK, "Player: "))
                .append(Text.of(TextColors.DARK_GRAY, card.getPlayerName()))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text nameText = (card.getName().equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.BLACK, "Character: "))
                .append(Text.of(TextColors.DARK_GRAY, card.getName()))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text ageText = (card.getAge().equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.BLACK, "Age: "))
                .append(Text.of(TextColors.DARK_GRAY, card.getAge()))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text nationText = (card.getNationality().equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.BLACK, "Nation: "))
                .append(Text.of(TextColors.DARK_GRAY, card.getNationality()))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text descText = (card.getDescription().equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.BLACK, "Description: "))
                .append(Text.of(TextColors.DARK_GRAY, card.getDescription()))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text nickText = (card.getNickname().equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.BLACK, "Nickname: "))
                .append(Text.of(TextColors.DARK_GRAY, card.getNickname()))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text bookText = Text.builder()
                .append(playerText, nameText, nickText, ageText, nationText)
                .build();
        return BookView.builder()
                .addPage(bookText)
                .addPage(descText)
                .build();
    }

    public void show(Player player) {
        player.sendBookView(toBookView());
    }
}
