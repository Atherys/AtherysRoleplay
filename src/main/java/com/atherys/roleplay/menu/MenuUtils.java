package com.atherys.roleplay.menu;

import com.atherys.roleplay.AtherysRoleplay;
import com.mcsimonflash.sponge.teslalibs.inventory.Action;
import com.mcsimonflash.sponge.teslalibs.inventory.Element;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import java.util.function.Consumer;

public class MenuUtils {
    public static void switchTo(Player player, View view) {
        Task.builder().execute(() -> view.open(player)).delayTicks(1).submit(AtherysRoleplay.getContainer());
    }

    public static void closeInventory(Action action) {
        Task.builder().delayTicks(1).execute(() -> action.getPlayer().closeInventory()).submit(AtherysRoleplay.getContainer());
    }

    public static void startSession(Player player, Consumer<String> submission) {
        AtherysRoleplay.getInstance().getMenuService().startSession(player, submission);
    }

    public static Element backButton(View previous) {
        ItemStack backItem = ItemStack.builder()
                .itemType(ItemTypes.BARRIER)
                .add(Keys.DISPLAY_NAME, Text.of("Go back"))
                .build();

        return Element.of(backItem, action -> switchTo(action.getPlayer(), previous));
    }
}
