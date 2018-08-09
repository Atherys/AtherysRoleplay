package com.atherys.roleplay;

import com.atherys.core.database.mongo.AbstractMongoDatabaseManager;
import com.atherys.roleplay.cards.CharacterCard;
import com.atherys.roleplay.database.AtherysRoleplayDatabase;
import com.google.gson.Gson;
import org.bson.Document;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;
import java.util.UUID;

public final class CardManager extends AbstractMongoDatabaseManager<CharacterCard> {

    private Gson gson = new Gson();

    private static CardManager instance = new CardManager();

    protected CardManager(){
        super(AtherysRoleplay.getInstance().getLogger(), AtherysRoleplayDatabase.getInstance(), CharacterCard.class);
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
        document.append("uuid", characterCard.getUUID().toString());
        document.append("playerName", characterCard.getPlayerName());
        document.append("name", characterCard.getName());
        document.append("nick", characterCard.getNickname());
        document.append("nation", characterCard.getNationality());
        document.append("age", characterCard.getAge());
        document.append("desc", characterCard.getDescription());
        return Optional.of(document);
    }

    @Override
    protected Optional<CharacterCard> fromDocument(Document document) {
        CharacterCard card = new CharacterCard(
                UUID.fromString(document.getString("uuid")),
                document.getString("playerName")
        );
        card.setName(document.getString("name"));
        card.setNickname(document.getString("nick"));
        card.setNationality(document.getString("nation"));
        card.setAge(document.getString("age"));
        card.addDescription(document.getString("desc"));
        return Optional.of(card);
    }
}
