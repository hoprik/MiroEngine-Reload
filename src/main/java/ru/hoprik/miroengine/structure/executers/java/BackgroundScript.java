package ru.hoprik.miroengine.structure.executers.java;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.hoprik.miroengine.structure.Root;

public class BackgroundScript {
    public final Logger LOGGER = LogManager.getLogger("MiroEngine-BackgroundScript");
    public final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    public final Root root;
    public BackgroundScript(Root root){
        this.root = root;
    }


}
