package ru.hoprik.miroengine.structure.executers.java;

import net.minecraft.entity.MobEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.hoprik.miroengine.MiroEngine;
import ru.hoprik.miroengine.structure.Root;
import ru.hoprik.miroengine.structure.functions.MobController;
import ru.hoprik.miroengine.structure.pojo.WalkTask;

public class Script{
    public final Logger LOGGER = LogManager.getLogger("MiroEngine-Script");
    public final MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
    public final Root root;
    public Script(Root root){
        this.root = root;
    }

    public synchronized void waitTime(int time) {
        try {
            wait(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        notifyAll();
    }

    public synchronized void waitZero(){
        try {
            wait();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void waitWalkEnd(WalkTask task){
        BlockPos pos = task.getPos();
        MobEntity entity = task.getEntity();
        MobController controller = task.getController();
        while (entity.getNavigation().isDone()) {
            MiroEngine.LOGGER.info(
                    pos.getX()+":"+entity.getX()+"\n"+
                            pos.getY()+":"+entity.getY()+"\n"+
                            pos.getZ()+":"+entity.getZ()+"\n"
            );
            waitZero();
        }
        notifyAll();
        controller.stopMove();
    }


}
