package com.ljnic.roleplay;

import com.atherys.core.database.mongo.AbstractMongoDatabaseManager;
import com.google.gson.Gson;
import com.ljnic.roleplay.cards.CharacterCard;
import com.ljnic.roleplay.database.RoleplayDatabase;
import com.ljnic.roleplay.util.Json;
import javafx.scene.input.DataFormat;
import org.bson.Document;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.persistence.DataFormats;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

public final class CardManager extends AbstractMongoDatabaseManager<CharacterCard> {

    private Gson gson = new Gson();

    private static CardManager instance = new CardManager();

    protected CardManager(){
        super(Roleplay.getInstance().getLogger(), RoleplayDatabase.getInstance(), "charactercards");
    }

    public CharacterCard createCard(Player player){
        CharacterCard card = new CharacterCard(player, player.getName());
        addCard(card);
        return card;
    }

    public void saveAll(){
        this.saveAll(getCache().values());
    }

    @Override
    public void loadAll(){
        super.loadAll();
    }

    public void addCard(CharacterCard card){
        this.getCache().put(card.getUUID(), card);
        this.save(card);
    }

    public CharacterCard getCard(Player player){
        if(getCache().containsKey(player.getUniqueId())){
            return getCache().get(player.getUniqueId());
        }else{
            return createCard(player);
        }
    }

    public boolean hasCard(Player player){
        return(getCache().containsKey(player.getUniqueId()));
    }

    public static CardManager getInstance(){
        return instance;
    }

    @Override
    protected Optional<Document> toDocument(CharacterCard characterCard) {
        Document document = new Document();
        document.append("uuid", characterCard.getPlayer().toString());
        document.append("playerName", characterCard.getPlayerName());
        document.append("name", characterCard.getName());
        document.append("nation", characterCard.getNationality());
        document.append("age", characterCard.getAge());
        document.append("desc", characterCard.getDescription());
        return Optional.of(document);
    }

    @Override
    protected Optional<CharacterCard> fromDocument(Document document) {
        CharacterCard card = new CharacterCard(UUID.fromString(document.getString("uuid")),
                document.getString("playerName"));
        card.setName(document.getString("name"));
        card.setNationality(document.getString("nation"));
        card.setAge(document.getString("age"));
        card.setDescription(document.getString("nation"));
        return Optional.of(card);
    }
}
