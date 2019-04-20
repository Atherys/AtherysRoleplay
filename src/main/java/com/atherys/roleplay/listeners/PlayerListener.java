package com.atherys.roleplay.listeners;

import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.menu.Menus;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.message.MessageChannelEvent;

public class PlayerListener {

    @Listener
    public void onPlayerShiftClick(InteractEntityEvent.Secondary event, @Root Player player) {
        if (!(event.getTargetEntity() instanceof Player)) return;

        Player target = (Player) event.getTargetEntity();
        player.get(Keys.IS_SNEAKING).ifPresent(key -> {
            if (key) {
                AtherysRoleplay.getInstance().getCardFacade().onPlayerShiftRightClick(player, target);
            }
        });
    }

    @Listener
    public void onPlayerChat(MessageChannelEvent.Chat event, @Root Player player) {
        if (AtherysRoleplay.getInstance().getMenuService().inSession(player)) {
            event.setCancelled(true);
            AtherysRoleplay.getInstance().getMenuService().endSession(player, event.getRawMessage().toPlain());
            Menus.cardMenu.open(player);
        }
    }

    @Listener
    public void onPlayerMove(MoveEntityEvent event, @Root Player player) {
        if (AtherysRoleplay.getInstance().getMenuService().isViewingBook(player)) {
            Menus.cardMenu.open(player);
            AtherysRoleplay.getInstance().getMenuService().endBookView(player);
        }
    }
}
