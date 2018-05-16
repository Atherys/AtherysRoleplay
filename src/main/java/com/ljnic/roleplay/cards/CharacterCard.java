package com.ljnic.roleplay.cards;

import com.atherys.core.database.api.DBObject;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.BookView;
import org.spongepowered.api.text.Text;

import java.util.UUID;

public class CharacterCard implements DBObject {

    private BookView card;
    private Text age;
    private Text name;
    private Text nationality;
    private Text description;
    private UUID player;

    public CharacterCard(Player player){
        this.player = player.getUniqueId();
        this.card = BookView.builder()
                .title(Text.of(player.getName()))
                .build();
    }

    public void show(Player player){
        player.sendBookView(card);
    }

    public void setAge(Text age) {
        this.age = age;
    }

    public void setDescription(Text description) {
        this.description = description;
    }


    @Override
    public UUID getUUID() {
        return player;
    }
}
