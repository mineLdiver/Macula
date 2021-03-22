package net.mine_diver.glsl.mixin;

import net.mine_diver.glsl.Shaders;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class MixinWorldRenderer {

    @Redirect(method = "renderSky(F)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/level/Level;method_286(F)F"))
    private float onGetStarBrightness(Level world, float f) {
        float ret = world.method_286(f);
        Shaders.setCelestialPosition();
        return ret;
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"))
    private void onGlEnable(int i) {
        Shaders.glEnableWrapper(i);
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(method = "*", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"))
    private void onGlDisable(int i) {
        Shaders.glDisableWrapper(i);
    }
}
