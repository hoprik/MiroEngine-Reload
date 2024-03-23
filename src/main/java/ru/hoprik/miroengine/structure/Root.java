package ru.hoprik.miroengine.structure;

import com.google.common.reflect.ClassPath;
import net.minecraft.world.World;
import net.minecraft.world.storage.FolderName;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.hoprik.miroengine.MiroEngine;
import ru.hoprik.miroengine.structure.executers.ScriptType;
import ru.hoprik.miroengine.structure.executers.ScriptWrapper;
import ru.hoprik.miroengine.structure.executers.java.Script;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Root{
    public static final Logger LOGGER = LogManager.getLogger("MiroEngine-Root");
    private final File world;
    private final Map<String, ScriptWrapper> scripts = new HashMap<>();
    private final Map<String, ScriptWrapper> backgroundScripts = new HashMap<>();
    private String task = "";
    private Thread thread;
    private final AtomicBoolean runningScripts = new AtomicBoolean(false);
    public Root() {
        this.world = ServerLifecycleHooks.getCurrentServer().getWorldPath(FolderName.ROOT).toFile();
        this.executerActivatorScripts();
    }

    public void registerScript() throws Exception {
        ClassPath classPath = ClassPath.from(ClassLoader.getSystemClassLoader());
        for (ClassPath.ClassInfo classInfo : classPath.getAllClasses()) {
            try {
                Class<?> clazz = classInfo.load();
                Class<?> superClass = clazz.getSuperclass();
                Class<?> script = ru.hoprik.miroengine.structure.executers.java.Script.class;
                Class<?> backgroundScript = ru.hoprik.miroengine.structure.executers.java.BackgroundScript.class;
                if (superClass.toString().equals(script.toString()) && scripts.get(clazz.getSimpleName().toLowerCase()) == null) {
                    ScriptWrapper wrapper = new ScriptWrapper(ScriptType.JAVA, clazz.getName());
                    scripts.put(clazz.getSimpleName().toLowerCase(), wrapper);
                    LOGGER.info("Register: "+clazz.getSimpleName().toLowerCase()+", type: "+ wrapper.getType()+", type script: script");
                }
                if (superClass.toString().equals(backgroundScript.toString()) && backgroundScripts.get(clazz.getSimpleName().toLowerCase()) == null) {
                    ScriptWrapper wrapper = new ScriptWrapper(ScriptType.JAVA, clazz.getName());
                    backgroundScripts.put(clazz.getSimpleName().toLowerCase(), wrapper);
                    LOGGER.info("Register: "+clazz.getSimpleName().toLowerCase()+", type: "+ wrapper.getType()+", type script: background script");
                }
            }
            catch (NoClassDefFoundError ignored){

            }
        }
    }

    public void execScript(String name){
        task = name.toLowerCase();
    }

    public void execBackgroundScript(String name){
        try {
            ScriptWrapper clazz = backgroundScripts.get(name.toLowerCase());
            Class[] parms = new Class[]{Root.class};
            Object[] args = new Object[]{this};
            Class.forName(clazz.getClazz()).getConstructor(parms).newInstance(args);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                 InvocationTargetException e) {
            LOGGER.error("error: "+e.getCause());
        }
    }

    public Map<String, ScriptWrapper> getScripts(){
        return scripts;
    }

    private void executerActivatorScripts(){
        runningScripts.set(true);
        thread = new Thread(()->{
            while (runningScripts.get()){
                if (!task.isEmpty()){
                    try {
                        ScriptWrapper clazz = scripts.get(task);
                        task = "";
                        Class[] parms = new Class[]{Root.class};
                        Object[] args = new Object[]{this};
                        Class.forName(clazz.getClazz()).getConstructor(parms).newInstance(args);
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | NoSuchMethodException |
                             InvocationTargetException e) {
                        LOGGER.error("error: "+e.getCause());
                    }
                }
            }
        });
        thread.setName("MiroEngine-thread-scripts");
        thread.start();
    }

}
