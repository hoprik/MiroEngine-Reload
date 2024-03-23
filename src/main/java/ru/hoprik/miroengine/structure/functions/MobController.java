package ru.hoprik.miroengine.structure.functions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import ru.hoprik.miroengine.MiroEngine;
import ru.hoprik.miroengine.mod.goal.MoveGoal;
import ru.hoprik.miroengine.structure.executers.java.Script;
import ru.hoprik.miroengine.structure.pojo.WalkTask;

public class MobController {
    private final MobEntity entity;
    private Goal moveGoal;
    public MobController(Entity entity){
        this.entity = (MobEntity) entity;
    }

    public WalkTask moveTo(BlockPos pos, float speed){
        this.moveGoal = new MoveGoal(entity, pos, speed);
        this.entity.goalSelector.getRunningGoals().forEach(prioritizedGoal -> {
            this.entity.goalSelector.removeGoal(prioritizedGoal.getGoal());
        });
        this.entity.goalSelector.addGoal(0, this.moveGoal);
        return new WalkTask(pos, this.entity, this);
    }

    public MobController stopMove(){
        this.entity.goalSelector.removeGoal(this.moveGoal);
        return this;
    }


}
