package com.atherys.roleplay.cards;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.format.TextColors;

@ConfigSerializable
public class Nation {

    @Setting(value="itemRepresentation")
    private ItemType itemType;

    @Setting(value="nationColor")
    private TextColor color;

    @Setting(value="nationName")
    private String name;

    public Nation(String name, TextColor color, ItemType itemType) {
        this.itemType = itemType;
        this.color = color;
        this.name = name;
    }

    // Default constructor for Configurate
    public Nation() {
        this.itemType = ItemTypes.BARRIER;
        this.color = TextColors.BLACK;
        this.name = "";
    }

    public ItemType getItemType() {
        return itemType;
    }

    public TextColor getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
