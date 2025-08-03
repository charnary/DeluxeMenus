package com.extendedclip.deluxemenus.utils;

import com.extendedclip.deluxemenus.DeluxeMenus;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public final class AdventureUtils {
    private final static GsonComponentSerializer gson = GsonComponentSerializer.gson();
    private final static MiniMessage miniMessage = MiniMessage.miniMessage();

    private AdventureUtils() {
        throw new AssertionError("Util classes should not be initialized");
    }

    public static void sendJson(@NotNull final DeluxeMenus plugin, CommandSender sender, String json) {
        sender.sendMessage(fromJson(json));
    }

    public static Component fromJson(String json) {
        return gson.deserialize(json);
    }

    @NotNull
    public static Component parseMiniMessage(@NotNull String input) {
        String converted = StringUtils.convertLegacyToMiniMessage(StringUtils.convertHexToMiniMessage(input));
        return miniMessage.deserialize("<!italic>" + converted);
    }

    @NotNull
    public static List<Component> parseMiniMessageList(@NotNull List<String> input) {
        return input.stream()
                .map(AdventureUtils::parseMiniMessage)
                .collect(Collectors.toList());
    }
}