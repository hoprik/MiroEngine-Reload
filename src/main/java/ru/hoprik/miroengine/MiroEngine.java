package ru.hoprik.miroengine;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MiroEngine.MODID)
public class MiroEngine {
    public static final Logger LOGGER = LogManager.getLogger("MiroEngine-Main");
    public static final String MODID = "miroengine";
    public static final String HEROID = "#7f3bc";
    public MiroEngine() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
    }

}
