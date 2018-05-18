package com.atherys.roleplay.cards;

import com.atherys.core.database.api.DBObject;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.UUID;

public class CharacterCard implements DBObject {

    private String age;
    private String description;
    private String name;
    private String nationality;
    private String playerName;
    private String nickname;
    private UUID player;

    private CharacterCard(){
        this.age = "";
        this.description = "";
        this.name = "";
        this.nationality = "";
        this.nickname = "";
    }

    public CharacterCard(UUID player, String playerName){
        this();
        this.player = player;
        this.playerName = playerName;
    }

    public CharacterCard(Player player, String playerName){
        this();
        this.player = player.getUniqueId();
        this.playerName = playerName;
    }

    public void show(Player player){
        Text playerText = Text.builder()
                .append(Text.of(TextColors.GOLD, "Player: "))
                .append(Text.of(TextColors.DARK_GREEN, playerName))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text nameText = (name.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Character: "))
                .append(Text.of(TextColors.DARK_GREEN, name))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text ageText = (age.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Age: "))
                .append(Text.of(TextColors.DARK_GREEN, age))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text nationText = (nationality.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Nation: "))
                .append(Text.of(TextColors.DARK_GREEN, nationality))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text descText = (description.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Description: "))
                .append(Text.of(TextColors.DARK_GREEN, description))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text nickText = (nickname.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Nickname: "))
                .append(Text.of(TextColors.DARK_GREEN, nickname))
                .append(Text.NEW_LINE)
                .append(Text.NEW_LINE)
                .build();
        Text bookText = Text.builder()
                .append(playerText, nameText, nickText, ageText, nationText)
                .build();
        BookView bookView = BookView.builder()
                .addPage(bookText)
                .addPage(descText)
                .build();
        player.sendBookView(bookView);
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void addDescription(String description) {
        this.description = this.description + description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getName(){
        return name;
    }

    public String getNickname(){
        return nickname;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public UUID getUUID() {
        return player;
    }
}
