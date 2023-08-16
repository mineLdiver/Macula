package net.mine_diver.glsl.mixin;

import net.mine_diver.glsl.Shaders;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Inject(
            method = "renderSky(F)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/level/Level;method_286(F)F",
                    shift = At.Shift.AFTER
            )
    )
    private void onGetStarBrightness(float par1, CallbackInfo ci) {
        Shaders.setCelestialPosition();
    }

    @Redirect(
            method = "*",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V"
            )
    )
    private void onGlEnable(int i) {
        Shaders.glEnableWrapper(i);
    }

    @Redirect(
            method = "*",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;glDisable(I)V"
            )
    )
    private void onGlDisable(int i) {
        Shaders.glDisableWrapper(i);
    }
}
