package net.mine_diver.glsl.mixin;

import net.mine_diver.glsl.Shaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Living;
import net.minecraft.sortme.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class MixinGameRenderer {

    @Shadow protected abstract void method_1845(float f, int i);

    @Shadow protected abstract void method_1847(float f);

    @Shadow protected abstract void method_1840(float f, int i);

    @Inject(method = "method_1841(FJ)V", at = @At("HEAD"))
    private void beginRender(float var1, long var2, CallbackInfo ci) {
        Shaders.beginRender(minecraft, var1, var2);
    }

    @Inject(method = "method_1841(FJ)V", at = @At("RETURN"))
    private void endRender(CallbackInfo ci) {
        Shaders.endRender();
    }

    @Redirect(method = "method_1841(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/sortme/GameRenderer;method_1840(FI)V"))
    private void setClearColor(GameRenderer gameRenderer, float var1, int var18) {
        Shaders.setClearColor(field_2346, field_2347, field_2348);
        method_1840(var1, var18);
        Shaders.setCamera(var1);
    }

    @Redirect(method = "method_1841(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;method_1548(Lnet/minecraft/entity/Living;ID)I"))
    private int beginTerrain(WorldRenderer worldRenderer, Living var4, int i, double var1) {
        int ret;
        if (i == 0) {
            Shaders.beginTerrain();
            ret = worldRenderer.method_1548(var4, i, var1);
            Shaders.endTerrain();
        } else if (i == 1) {
            Shaders.beginWater();
            ret = worldRenderer.method_1548(var4, i, var1);
            Shaders.endWater();
        } else
            ret = worldRenderer.method_1548(var4, i, var1);
        return ret;
    }

    @Redirect(method = "method_1841(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/WorldRenderer;method_1540(ID)V"))
    private void beginWater(WorldRenderer worldRenderer, int i, double var1) {
        Shaders.beginWater();
        worldRenderer.method_1540(i, var1);
        Shaders.endWater();
    }

    @Redirect(method = "method_1841(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/sortme/GameRenderer;method_1847(F)V"))
    private void beginWeather(GameRenderer gameRenderer, float var1) {
        Shaders.beginWeather();
        method_1847(var1);
        Shaders.endWeather();
    }

    @Redirect(method = "method_1841(FJ)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/sortme/GameRenderer;method_1845(FI)V"))
    private void beginHand(GameRenderer gameRenderer, float var1, int var18) {
        Shaders.beginHand();
        method_1845(var1, var18);
        Shaders.endHand();
    }

    @Shadow
    private Minecraft minecraft;
    @Shadow
    float
            field_2346,
            field_2347,
            field_2348;
}
