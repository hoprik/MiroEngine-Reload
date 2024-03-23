package ru.hoprik.miroengine.structure;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import ru.hoprik.miroengine.MiroEngine;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = MiroEngine.MODID)
public class EventManager {
    public static Root root;

    @SubscribeEvent
    public static void onLoadEvent(WorldEvent.Load event){
        if (!event.getWorld().isClientSide() && event.getWorld().dimensionType().bedWorks()) {
            root = new Root();
            try {
                root.registerScript();
            } catch (Exception e) {
            }
        }
    }

    @SubscribeEvent
    public static void onJoinEvent(EntityJoinWorldEvent event){
        if (!event.getWorld().isClientSide && event.getEntity() instanceof PlayerEntity){
            root.execScript("S1");
        }
    }

//    @SubscribeEvent
//    public static void onGuiCheck(GuiOpenEvent event){
//        MiroEngine.LOGGER.info("Gui open: "+event.getGui().getTitle().getString());
//    }

}
