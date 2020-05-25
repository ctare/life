package ctare.mod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctare on 2020/05/23.
 */
public abstract class ModLoader {
    public abstract void initialize();
    public abstract void stateRegister();
    public abstract void nodeRegister();
    public abstract void statesRegister();
    private static List<ModLoader> mods = new ArrayList<>();

    public static void load() {
        mods.add(new ctare.mod.deadsystem.ModMain());
        mods.add(new ctare.mod.healthsystem.ModMain());
        mods.add(new ctare.mod.worksystem.ModMain());
        mods.add(new ctare.mod.bagsystem.ModMain());

        mods.forEach(ModLoader::initialize);
        mods.forEach(ModLoader::stateRegister);
        mods.forEach(ModLoader::nodeRegister);
        mods.forEach(ModLoader::statesRegister);
        mods = null;
    }
}
