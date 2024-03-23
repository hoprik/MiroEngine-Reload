package ru.hoprik.miroengine.structure.functions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldWrapper {
    private final World world;
    public WorldWrapper(World world){
        this.world = world;
    }

    public WorldWrapper(String world){
        this.world = SceneFunctions.getWorld(world);
    }

    public WorldWrapper(){
        this.world = SceneFunctions.getWorld();
    }

    public WorldWrapper setBlock(BlockPos pos, Block block){
        this.world.setBlockAndUpdate(pos, block.defaultBlockState());
        return this;
    }

    public WorldWrapper setBlock(BlockPos pos, BlockState state){
        this.world.setBlockAndUpdate(pos, state);
        return this;
    }

    public BlockState getState(BlockPos pos){
        return this.world.getBlockState(pos);
    }

    public Entity spawnEntity(BlockPos pos, EntityType<?> type){
        Entity entity = type.create(this.world);
        assert entity != null;
        entity.setPos(pos.getX(), pos.getY(), pos.getZ());
        this.world.addFreshEntity(entity);
        return entity;
    }
}
