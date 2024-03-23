package ru.hoprik.miroengine.structure.pojo;

import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.BlockPos;
import ru.hoprik.miroengine.structure.functions.MobController;

public class WalkTask {
    private final BlockPos pos;
    private final MobEntity entity;
    private final MobController controller;
    public WalkTask(BlockPos pos, MobEntity entity, MobController controller){
        this.pos = pos;
        this.entity = entity;
        this.controller = controller;
    }

    public BlockPos getPos() {
        return pos;
    }

    public MobEntity getEntity() {
        return entity;
    }

    public MobController getController() {
        return controller;
    }
}
