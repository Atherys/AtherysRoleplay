package com.ljnic.roleplay;

import com.atherys.core.database.mongo.AbstractMongoDatabaseManager;
import com.ljnic.roleplay.cards.CharacterCard;
import com.ljnic.roleplay.database.RoleplayDatabase;
import org.bson.Document;
import org.spongepowered.api.entity.living.player.Player;

import java.util.Optional;

public final class CardManager extends AbstractMongoDatabaseManager<CharacterCard> {

    private static CardManager instance = new CardManager();

    protected CardManager(){
        super(Roleplay.getInstance().getLogger(), RoleplayDatabase.getInstance(), "charactercards");
    }

    public CharacterCard createCard(Player player){
        CharacterCard card = new CharacterCard(player);
        addCard(card);
        return card;
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

    public static CardManager getInstance(){
        return instance;
    }

    @Override
    protected Optional<Document> toDocument(CharacterCard characterCard) {
        return Optional.empty();
    }

    @Override
    protected Optional<CharacterCard> fromDocument(Document document) {
        return Optional.empty();
    }
}
