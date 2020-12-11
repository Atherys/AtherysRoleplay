package com.atherys.roleplay.entity;

import com.atherys.core.db.Identifiable;
import com.atherys.roleplay.card.CardView;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(schema = "atherysroleplay", name = "CharacterCard")
public class CharacterCard implements Identifiable<UUID> {

    private String age;
    private String description;
    private String name;
    private String nationality;
    private String playerName;
    private String nickname;

    @Id
    private UUID player;

    public void resetCard() {
        this.age = "";
        this.description = "";
        this.name = "";
        this.nationality = "";
        this.nickname = "";
    }

    public CharacterCard(UUID player, String playerName) {
        resetCard();
        this.player = player;
        this.playerName = playerName;
    }

    public CharacterCard(Player player, String playerName) {
        resetCard();
        this.player = player.getUniqueId();
        this.playerName = playerName;
    }

    CharacterCard() {
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDescription() {
        return description;
    }

    public BookView toView() {
        return new CardView(this).toBookView();
    }

    @Override
    public UUID getId() {
        return player;
    }
}
