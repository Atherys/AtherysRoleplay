package com.atherys.roleplay;

import com.atherys.core.utils.PluginConfig;
import com.atherys.roleplay.cards.Nation;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ConfigSerializable
public class AtherysRoleplayConfig extends PluginConfig {

    @Setting("is_default")
    public boolean IS_DEFAULT = true;

    @Setting("dialog_msg_delay")
    public long DIALOG_MESSAGE_DELAY = 2;

    @Setting("maximum_age")
    public int MAXIMUM_AGE = 150;

    @Setting("maximum_name_length")
    public int MAXIMUM_NAME_LENGTH = 40;

    @Setting("maximum_nickname_length")
    public int MAXIMUM_NICK_LENGTH = 16;

    @Setting("nations")
    public List<Nation> NATIONS = Arrays.asList(
            new Nation("Dalkun-Tir", TextColors.GOLD, ItemTypes.SANDSTONE),
            new Nation("Atvoria", TextColors.DARK_PURPLE, ItemTypes.WRITABLE_BOOK),
            new Nation("Gennaian Isles", TextColors.WHITE, ItemTypes.QUARTZ_STAIRS),
            new Nation("Daidama", TextColors.DARK_GREEN, ItemTypes.VINE),
            new Nation("Kilnholdt", TextColors.DARK_GRAY, ItemTypes.GUNPOWDER)
    );

    protected AtherysRoleplayConfig() throws IOException {
        super("config/" + AtherysRoleplay.ID, "config.conf");
        init();
    }
}
