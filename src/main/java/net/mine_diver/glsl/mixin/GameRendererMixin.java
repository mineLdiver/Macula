package net.mine_diver.glsl.mixin;

import net.mine_diver.glsl.Shaders;
import net.minecraft.client.Minecraft;
import net.minecraft.sortme.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
    @Inject(
            method = "delta(FJ)V",
            at = @At("HEAD")
    )
    private void beginRender(float var1, long var2, CallbackInfo ci) {
        Shaders.beginRender(minecraft, var1, var2);
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At("RETURN")
    )
    private void endRender(CallbackInfo ci) {
        Shaders.endRender();
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/sortme/GameRenderer;method_1840(FI)V"
            )
    )
    private void setClearColor(float l, long par2, CallbackInfo ci) {
        Shaders.setClearColor(field_2346, field_2347, field_2348);
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/sortme/GameRenderer;method_1840(FI)V",
                    shift = At.Shift.AFTER
            )
    )
    private void setCamera(float l, long par2, CallbackInfo ci) {
        Shaders.setCamera(l);
    }


    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1548(Lnet/minecraft/entity/Living;ID)I",
                    ordinal = 0
            )
    )
    private void injectTerrainBegin(float l, long par2, CallbackInfo ci) {
        Shaders.beginTerrain();
    }


    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1548(Lnet/minecraft/entity/Living;ID)I",
                    ordinal = 0,
                    shift = At.Shift.AFTER
            )
    )
    private void injectTerrainEnd(float l, long par2, CallbackInfo ci) {
        Shaders.endTerrain();
    }


    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1548(Lnet/minecraft/entity/Living;ID)I",
                    ordinal = 1
            )
    )
    private void injectWaterBegin1(float l, long par2, CallbackInfo ci) {
        Shaders.beginWater();
    }


    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1548(Lnet/minecraft/entity/Living;ID)I",
                    ordinal = 1,
                    shift = At.Shift.AFTER
            )
    )
    private void injectWaterEnd1(float l, long par2, CallbackInfo ci) {
        Shaders.endWater();
    }


    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1548(Lnet/minecraft/entity/Living;ID)I",
                    ordinal = 2
            )
    )
    private void injectWaterBegin2(float l, long par2, CallbackInfo ci) {
        Shaders.beginWater();
    }


    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1548(Lnet/minecraft/entity/Living;ID)I",
                    ordinal = 2,
                    shift = At.Shift.AFTER
            )
    )
    private void injectWaterEnd2(float l, long par2, CallbackInfo ci) {
        Shaders.endWater();
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1540(ID)V"
            )
    )
    private void injectBeginWater3(float l, long par2, CallbackInfo ci) {
        Shaders.beginWater();
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/WorldRenderer;method_1540(ID)V",
                    shift = At.Shift.AFTER
            )
    )
    private void injectEndWater3(float l, long par2, CallbackInfo ci) {
        Shaders.endWater();
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/sortme/GameRenderer;method_1847(F)V"
            )
    )
    private void injectBeginWeather(float l, long par2, CallbackInfo ci) {
        Shaders.beginWeather();
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/sortme/GameRenderer;method_1847(F)V",
                    shift = At.Shift.AFTER
            )
    )
    private void injectEndWeather(float l, long par2, CallbackInfo ci) {
        Shaders.endWeather();
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/sortme/GameRenderer;method_1845(FI)V"
            )
    )
    private void injectBeginHand(float l, long par2, CallbackInfo ci) {
        Shaders.beginHand();
    }

    @Inject(
            method = "delta(FJ)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/sortme/GameRenderer;method_1845(FI)V",
                    shift = At.Shift.AFTER
            )
    )
    private void injectEndHand(float l, long par2, CallbackInfo ci) {
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
