package ru.hoprik.miroengine.scripts;

import ru.hoprik.miroengine.structure.Root;
import ru.hoprik.miroengine.structure.executers.java.Script;
import ru.hoprik.miroengine.structure.functions.SceneFunctions;

public class S1 extends Script {
    public S1(Root root){
        super(root);
        SceneFunctions.heroMessage("S1", "ЗАПУСКАЮ SCENE 2");
        root.execScript("S2");
    }
}
