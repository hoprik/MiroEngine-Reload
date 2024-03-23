package ru.hoprik.miroengine.scripts;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import ru.hoprik.miroengine.structure.Root;
import ru.hoprik.miroengine.structure.executers.java.Script;
import ru.hoprik.miroengine.structure.functions.MobController;
import ru.hoprik.miroengine.structure.functions.SceneFunctions;
import ru.hoprik.miroengine.structure.functions.WorldWrapper;

public class S2 extends Script {
    public S2(Root root){
        super(root);
        this.waitTime(1000);
        SceneFunctions.heroMessage("Тест", "Тестрирование создание блоков");
        this.waitTime(1000);
        WorldWrapper worldWrapper = new WorldWrapper();
        PlayerEntity player = SceneFunctions.getFirstPlayer();
        BlockPos pos = player.blockPosition();
        worldWrapper.setBlock(pos.south(5), Blocks.DIAMOND_BLOCK);
        this.waitTime(500);
        worldWrapper.setBlock(pos.above(1).south(5), Blocks.DIAMOND_BLOCK);
        this.waitTime(500);
        worldWrapper.setBlock(pos.above(2).south(5), Blocks.DIAMOND_BLOCK);
        this.waitTime(500);
        worldWrapper.setBlock(pos.east(1).south(5), Blocks.DIAMOND_BLOCK);
        this.waitTime(500);
        worldWrapper.setBlock(pos.west(1).south(5), Blocks.DIAMOND_BLOCK);
        SceneFunctions.heroMessage("Тест", "Тестрирование звукового движка....");
        SceneFunctions.playSoundGlobal(SoundEvents.DROWNED_DEATH, 100, 0);
        this.waitTime(1000);
        SceneFunctions.heroMessage("Тест", "Тестрирование создание моба и передвижение");
        MobController controller = new MobController(worldWrapper.spawnEntity(pos.east(4).south(2), EntityType.SHEEP));
        SceneFunctions.heroMessage("Тест", "Я иду на координаты: "+ pos.west(4));
        controller.moveTo(pos.west(4), 0.7f);
    }
}
