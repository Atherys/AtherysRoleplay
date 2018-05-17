package com.ljnic.roleplay.cards;

import com.atherys.core.database.api.DBObject;
import com.ljnic.roleplay.Roleplay;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.util.UUID;

public class CharacterCard implements DBObject {

    private static final String title = "   Character Card   ";
    private String age;
    private String description;
    private String name;
    private String nationality;
    private String playerName;
    private UUID player;

    private CharacterCard(){
        this.age = "";
        this.description = "";
        this.name = "";
        this.nationality = "";
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
                .build();
        Text nameText = (name.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Character: "))
                .append(Text.of(TextColors.DARK_GREEN, name + "   "))
                .build();
        Text ageText = (age.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Age: "))
                .append(Text.of(TextColors.DARK_GREEN, age + "   "))
                .build();
        Text nationText = (nationality.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Nation: "))
                .append(Text.of(TextColors.DARK_GREEN, nationality + "   "))
                .build();
        Text descText = (description.equals("")) ? Text.EMPTY : Text.builder()
                .append(Text.of(TextColors.GOLD, "Description: "))
                .append(Text.of(TextColors.DARK_GREEN, description + "   "))
                .build();
        Text bookText = Text.builder()
                .append(playerText, Text.NEW_LINE, nameText, Text.NEW_LINE, ageText, Text.NEW_LINE, nationText, Text.NEW_LINE, descText)
                .build();
        BookView bookView = BookView.builder()
                .addPage(bookText)
                .build();
        player.sendBookView(bookView);
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addDescription(String append){
        this.description = description.concat(append);
    }

    public void setName(String name) {
        this.name = name;
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

    public String getNationality() {
        return nationality;
    }

    public String getDescription() {
        return description;
    }

    public UUID getPlayer() {
        return player;
    }

    @Override
    public UUID getUUID() {
        return player;
    }
}
