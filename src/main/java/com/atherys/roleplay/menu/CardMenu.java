package com.atherys.roleplay.menu;

import com.atherys.roleplay.AtherysRoleplay;
import com.mcsimonflash.sponge.teslalibs.inventory.Element;
import com.mcsimonflash.sponge.teslalibs.inventory.Layout;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CardMenu {
    private static View cardMenu;

    private static Map<String, Consumer<String>> actions = new HashMap<>();

    static {
        Element name = Element.of(
                ItemStack.of(ItemTypes.SKULL),
                action -> {
                    AtherysRoleplay.getSessionService().startSession(
                            action.getPlayer(),
                            s -> {
                                Sponge.getCommandManager().process(action.getPlayer(), "card set name " + s);
                            }
                    );
                }
        );

        Element age = Element.of(
                ItemStack.of(ItemTypes.BONE),
                action -> {
                    AtherysRoleplay.getSessionService().startSession(
                            action.getPlayer(),
                            s -> {
                                Sponge.getCommandManager().process(action.getPlayer(), "card set age " + s);
                            }
                    );
                }
        );

        cardMenu = View.of(InventoryArchetypes.MENU_ROW, AtherysRoleplay.getContainer())
                .define(Layout.builder()
                    .set(name, 0)
                    .set(age, 1)
                    .build());
    }

    public static void show(Player player) {
        cardMenu.open(player);
    }

}
