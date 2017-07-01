package uk.co.hexeption.loader;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

/**
 * Created by Hexeption on 16/03/2017.
 */
public class MixinLoader implements IFMLLoadingPlugin {

    public MixinLoader() {

        MixinBootstrap.init();
        Mixins.addConfiguration("mixin.globalcapes.json");
    }

    @Override
    public String[] getASMTransformerClass() {

        return new String[0];
    }

    @Override
    public String getModContainerClass() {

        return null;
    }

    @Override
    public String getSetupClass() {

        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {

        return null;
    }
}
