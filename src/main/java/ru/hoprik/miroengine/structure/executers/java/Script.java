package ru.hoprik.miroengine.structure.executers.java;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.hoprik.miroengine.structure.Root;

public class Script{
    public final Logger LOGGER = LogManager.getLogger("MiroEngine-Script");
    public final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    public final Root root;
    public Script(Root root){
        this.root = root;
    }

    public static void waitTest() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
