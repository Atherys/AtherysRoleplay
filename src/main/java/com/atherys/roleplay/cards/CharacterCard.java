package com.atherys.roleplay.cards;

import com.atherys.core.database.api.DBObject;
import com.atherys.core.views.View;
import com.atherys.core.views.Viewable;
import org.spongepowered.api.entity.living.player.Player;

import java.util.UUID;

public class CharacterCard implements DBObject, Viewable {

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

    @Override
    public View createView() {
        return new CardView(this);
    }
}
