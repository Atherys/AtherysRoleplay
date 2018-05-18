package com.ljnic.roleplay.listeners;

import com.ljnic.roleplay.CardManager;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;
import org.spongepowered.api.text.channel.AbstractMutableMessageChannel;
import org.spongepowered.api.text.channel.impl.SimpleMutableMessageChannel;

import java.util.Collection;

public class PlayerListener {

    @Listener
    public void onPlayerShiftClick(InteractEntityEvent.Secondary event, @Root Player player){
        if(!(event.getTargetEntity() instanceof Player)) return;
        if(player.get(Keys.IS_SNEAKING).get()){
            CardManager.getInstance().getCard(((Player) event.getTargetEntity()).getPlayer().get()).show(player);
        }
    }

    @Listener
    public void onChat(MessageChannelEvent.Chat e, @Root Player player){
        AbstractMutableMessageChannel localChannel = new SimpleMutableMessageChannel();
        localChannel.addMember(player);
        Collection<Entity> nearby = player.getNearbyEntities(15);
        nearby.forEach(entity -> {
            if(entity instanceof Player){
                localChannel.addMember((Player)entity);
            }
        });

        localChannel.send(e.getOriginalMessage());
    }
}
