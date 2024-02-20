package ru.hoprik.miroengine.scripts;

import ru.hoprik.miroengine.structure.Root;
import ru.hoprik.miroengine.structure.executers.java.Script;

public class S1 extends Script {
    public S1(Root root){
        super(root);
        LOGGER.info("1234");
        root.execScript("S2");
    }
}
