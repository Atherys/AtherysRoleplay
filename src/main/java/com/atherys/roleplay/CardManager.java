package com.atherys.roleplay;

import com.atherys.core.database.mongo.MorphiaDatabaseManager;
import com.atherys.roleplay.cards.CharacterCard;
import com.atherys.roleplay.database.AtherysRoleplayDatabase;
import org.spongepowered.api.entity.living.player.Player;

public final class CardManager extends MorphiaDatabaseManager<CharacterCard> {

    private static CardManager instance = new CardManager();

    protected CardManager(){
        super(AtherysRoleplayDatabase.getInstance(), AtherysRoleplay.getLogger(), CharacterCard.class);
    }

    public CharacterCard createCard(Player player){
        CharacterCard card = new CharacterCard(player, player.getName());
        addCard(card);
        return card;
    }

    public void saveAll(){
        this.saveAll(getCache().values());
    }

    public void addCard(CharacterCard card){
        this.getCache().put(card.getUUID(), card);
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

}
