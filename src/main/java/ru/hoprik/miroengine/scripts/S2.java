package ru.hoprik.miroengine.scripts;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import ru.hoprik.miroengine.structure.Root;
import ru.hoprik.miroengine.structure.executers.java.Script;
import ru.hoprik.miroengine.structure.functions.SceneFunctions;

public class S2 extends Script {
    public S2(Root root){
        super(root);
        this.waitTest();
        SceneFunctions.heroMessage("Алекс", "Даров народов!");
        this.waitTest();
        SceneFunctions.heroMessage("Алекс", "Даров народов!");

    }
}
