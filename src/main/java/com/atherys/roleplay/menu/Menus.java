package com.atherys.roleplay.menu;

import com.atherys.roleplay.AtherysRoleplay;
import com.atherys.roleplay.card.Nation;
import com.mcsimonflash.sponge.teslalibs.inventory.Element;
import com.mcsimonflash.sponge.teslalibs.inventory.Layout;
import com.mcsimonflash.sponge.teslalibs.inventory.View;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.Text;

import java.util.Collections;

public class Menus {

    public static final View cardMenu = buildCardMenu();
    private static final View nationMenu = buildNationMenu();

    private static View buildCardMenu() {
        ItemStack viewItem = ItemStack.builder()
                .itemType(ItemTypes.ENCHANTED_BOOK)
                .add(Keys.DISPLAY_NAME, Text.of("View your card"))
                .build();

        Element view = Element.of(
                viewItem,
                action -> {
                    AtherysRoleplay.getInstance().getCardFacade().showCard(action.getPlayer());
                    AtherysRoleplay.getInstance().getMenuService().startBookView(action.getPlayer());
                }
        );

        ItemStack nameItem = ItemStack.builder()
                .itemType(ItemTypes.SKULL)
                .add(Keys.DISPLAY_NAME, Text.of("Name"))
                .add(Keys.ITEM_LORE, Collections.singletonList(Text.of("Set your name")))
                .build();

        Element name = Element.of(
                nameItem,
                action -> {
                    MenuUtils.closeInventory(action);
                    AtherysRoleplay.getInstance().getMessagingFacade().info(action.getPlayer(), "Enter your character's name.");
                    MenuUtils.startSession(
                            action.getPlayer(),
                            s -> Sponge.getCommandManager().process(action.getPlayer(), "card name " + s)
                    );
                }
        );

        ItemStack ageItem = ItemStack.builder()
                .itemType(ItemTypes.BONE)
                .add(Keys.DISPLAY_NAME, Text.of("Age"))
                .add(Keys.ITEM_LORE, Collections.singletonList(Text.of("Set your age")))
                .build();

        Element age = Element.of(
                ageItem,
                action -> {
                    MenuUtils.closeInventory(action);
                    AtherysRoleplay.getInstance().getMessagingFacade().info(action.getPlayer(), "Enter your character's age.");
                    MenuUtils.startSession(
                            action.getPlayer(),
                            s -> Sponge.getCommandManager().process(action.getPlayer(), "card age " + s)
                    );
                }
        );

        ItemStack nationItem = ItemStack.builder()
                .itemType(ItemTypes.BANNER)
                .add(Keys.DISPLAY_NAME, Text.of("Nation"))
                .add(Keys.ITEM_LORE, Collections.singletonList(Text.of("Set your nation")))
                .build();

        Element nation = Element.of(
                nationItem,
                action -> MenuUtils.switchTo(action.getPlayer(), nationMenu)
        );

        ItemStack descriptionItem = ItemStack.builder()
                .itemType(ItemTypes.WRITABLE_BOOK)
                .add(Keys.DISPLAY_NAME, Text.of("Description"))
                .add(Keys.ITEM_LORE, Collections.singletonList(Text.of("Add to your description")))
                .build();

        Element description = Element.of(
                descriptionItem,
                action -> {
                    MenuUtils.closeInventory(action);
                    AtherysRoleplay.getInstance().getMessagingFacade().info(action.getPlayer(), "Enter additions to your description.");
                    MenuUtils.startSession(
                            action.getPlayer(),
                            s -> AtherysRoleplay.getInstance().getCardFacade().addToCardDescription(action.getPlayer(), s)
                    );
                }
        );

        ItemStack resetDescriptionItem = ItemStack.builder()
                .itemType(ItemTypes.BOOK)
                .add(Keys.DISPLAY_NAME, Text.of("Reset your description"))
                .build();

        Element resetDescription = Element.of(
                resetDescriptionItem,
                action -> AtherysRoleplay.getInstance().getCardFacade().setCardDescription(action.getPlayer(), "")
        );

        ItemStack resetItem = ItemStack.builder()
                .itemType(ItemTypes.TNT)
                .add(Keys.DISPLAY_NAME, Text.of("Reset your card"))
                .build();

        Element reset = Element.of(
                resetItem,
                action -> AtherysRoleplay.getInstance().getCardFacade().resetCard(action.getPlayer())
        );

        InventoryArchetype cardInventory = InventoryArchetype.builder()
                .title(Text.of("Character Card Creation"))
                .with(InventoryArchetypes.MENU_ROW)
                .build("atherys:card", "Character Card");

        return View.of(cardInventory, AtherysRoleplay.getContainer())
                .define(Layout.builder()
                        .set(view, 0)
                        .set(name, 2)
                        .set(age, 3)
                        .set(nation, 4)
                        .set(description, 5)
                        .set(resetDescription, 6)
                        .set(reset, 8)
                        .build());
    }

    private static View buildNationMenu() {
        Layout.Builder layout = Layout.builder();
        int index = 0;

        for (Nation nation : AtherysRoleplay.getInstance().getConfig().NATIONS) {
            ItemStack nationItem = ItemStack.builder()
                    .itemType(nation.getItemType())
                    .add(Keys.DISPLAY_NAME, Text.of(nation.getColor(), nation.getName()))
                    .build();

            layout.set(
                    Element.of(
                            nationItem,
                            action -> AtherysRoleplay.getInstance().getCardFacade().setCardNation(action.getPlayer(), nation)),
                    index
            );
            index++;
        }

        layout.set(MenuUtils.backButton(cardMenu), 8);

        InventoryArchetype nationInventory = InventoryArchetype.builder()
                .title(Text.of("Choose your Nation"))
                .with(InventoryArchetypes.MENU_ROW)
                .build("atherys:nation", "Nation Menu");

        return View.of(nationInventory, AtherysRoleplay.getContainer()).define(layout.build());
    }
}
