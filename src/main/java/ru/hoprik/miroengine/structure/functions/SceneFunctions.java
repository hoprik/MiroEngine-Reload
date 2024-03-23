package ru.hoprik.miroengine.structure.functions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.SPlaySoundPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import ru.hoprik.miroengine.MiroEngine;

import java.util.List;
import java.util.Objects;

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

    public static List<ServerPlayerEntity> getPlayers(){
        return server.getPlayerList().getPlayers();
    }

    public static World getWorld(){
        return server.overworld();
    }

    public static World getWorld(String type){
        if (Objects.equals(type, "overworld")){
            return server.getLevel(World.OVERWORLD);
        }
        if (Objects.equals(type, "nether")){
            return server.getLevel(World.NETHER);
        }
        if (Objects.equals(type, "end")){
            return server.getLevel(World.END);
        }
        return server.overworld();
    }

    public static void playSoundGlobal(SoundEvent event, float volume, float pitch){
        for (ServerPlayerEntity entity: SceneFunctions.getPlayers()){
            entity.connection.send(new SPlaySoundPacket(event.getLocation(), SoundCategory.MASTER, entity.position(), volume, pitch));
        }
    }

    public static void playSoundGlobal(SoundEvent event, SoundCategory category, float volume, float pitch){
        for (ServerPlayerEntity entity: SceneFunctions.getPlayers()){
            entity.connection.send(new SPlaySoundPacket(event.getLocation(), category, entity.position(), volume, pitch));
        }
    }
}
