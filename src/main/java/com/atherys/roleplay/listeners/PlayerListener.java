package com.atherys.roleplay.listeners;

import com.atherys.roleplay.AtherysRoleplay;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;

public class PlayerListener {

    @Listener
    public void onPlayerShiftClick(InteractEntityEvent.Secondary event, @Root Player player){
        if(!(event.getTargetEntity() instanceof Player)) return;

        Player target = (Player) event.getTargetEntity();
        player.get(Keys.IS_SNEAKING).ifPresent(key -> {
            if (key) {
                AtherysRoleplay.getCardManager().getCard(target).createView().show(player);
            }
        });
    }
}
