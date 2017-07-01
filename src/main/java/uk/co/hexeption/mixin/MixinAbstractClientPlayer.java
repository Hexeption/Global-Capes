package uk.co.hexeption.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.capeapi.CapesAPI;

import javax.annotation.Nullable;

/**
 * Created by Hexeption on 16/03/2017.
 */
@SideOnly(Side.CLIENT)
@Mixin(AbstractClientPlayer.class)
public abstract class MixinAbstractClientPlayer {

    @Shadow
    @Nullable
    protected abstract NetworkPlayerInfo getPlayerInfo();

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    public void AbstractClientPlayer(World worldIn, GameProfile playerProfile, CallbackInfo callbackInfo) {

        CapesAPI.loadCape(playerProfile.getId());
    }

    /**
     * @author Hexeption
     */
    @Overwrite
    @Nullable
    public ResourceLocation getLocationCape() {

        if (CapesAPI.hasCape(getPlayerInfo().getGameProfile().getId())) {
            return CapesAPI.getCape(getPlayerInfo().getGameProfile().getId());
        } else {
            NetworkPlayerInfo networkPlayerInfo = getPlayerInfo();
            return networkPlayerInfo == null ? null : networkPlayerInfo.getLocationCape();
        }
    }
}
