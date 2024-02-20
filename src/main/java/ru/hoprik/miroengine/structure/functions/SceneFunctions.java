package ru.hoprik.miroengine.structure.functions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import ru.hoprik.miroengine.MiroEngine;

public class SceneFunctions {
    private static final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

    public static void heroMessage(String hero, String text){
        IFormattableTextComponent message = new StringTextComponent("");
        IFormattableTextComponent hero_comp = new StringTextComponent("["+hero+"] ").withStyle(style -> style.withColor(Color.parseColor(MiroEngine.HEROID)));
        IFormattableTextComponent text_comp = new StringTextComponent(text).withStyle(style -> style.withColor(TextFormatting.WHITE));

        message.append(hero_comp);
        message.append(text_comp);

        sendMessageEveryone(message);
    }

    public static void heroMessageLocal(String hero, String text, PlayerEntity player){
        IFormattableTextComponent component = new StringTextComponent("");
        component.append(String.format("[%s]", hero)).withStyle(style -> style.withColor(Color.parseColor(MiroEngine.HEROID)));
        component.append(text).withStyle(style -> style.withColor(TextFormatting.WHITE));
        sendMessageLocal(component, player);
    }

    public static void sendMessageEveryone(String text){
        sendMessageEveryone(new StringTextComponent(text));
    }

    public static void sendMessageEveryone(ITextComponent text){
        server.getPlayerList().getPlayers().forEach(serverPlayerEntity -> sendMessageLocal(text, serverPlayerEntity));

    }

    public static void sendMessageLocal(ITextComponent component, PlayerEntity player){
        player.sendMessage(component, player.getUUID());
    }

    public static PlayerEntity getFirstPlayer(){
        return server.getPlayerList().getPlayers().get(0);
    }
}
