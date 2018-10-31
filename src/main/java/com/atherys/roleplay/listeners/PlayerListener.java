package com.atherys.roleplay.listeners;

import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.menu.CardMenu;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;

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

    @Listener
    public void onPlayerChat(MessageChannelEvent.Chat event, @Root Player player) {
        if (AtherysRoleplay.getSessionService().inSession(player)) {
            AtherysRoleplay.getSessionService().endSession(player, event.getRawMessage().toPlain());
            CardMenu.show(player);
        }
    }
}
