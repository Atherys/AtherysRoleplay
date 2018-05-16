package com.ljnic.roleplay.listeners;

import com.ljnic.roleplay.CardManager;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;

public class PlayerListener {

    @Listener
    public void onPlayerShiftClick(InteractEntityEvent.Secondary event, @Root Player player){
        if(!(event.getTargetEntity() instanceof Player)) return;
        if(player.get(Keys.IS_SNEAKING).get()){
            CardManager.getInstance().getCard(((Player) event.getTargetEntity()).getPlayer().get()).show(player);
        }
    }
}
