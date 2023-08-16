package net.mine_diver.macula.mixin;

import net.mine_diver.macula.Shaders;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockRenderer.class)
public class BlockRenderManagerMixin {
    @Inject(
            method = "renderBottomFace(Lnet/minecraft/block/BlockBase;DDDI)V",
            at = @At("HEAD")
    )
    private void onRenderBottomFace(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        Tessellator.INSTANCE.setNormal(0.0F, -1.0F, 0.0F);
    }

    @Inject(
            method = "renderTopFace(Lnet/minecraft/block/BlockBase;DDDI)V",
            at = @At("HEAD")
    )
    private void onRenderTopFace(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        Tessellator.INSTANCE.setNormal(0.0F, 1.0F, 0.0F);
    }

    @Inject(
            method = "renderEastFace(Lnet/minecraft/block/BlockBase;DDDI)V",
            at = @At("HEAD")
    )
    private void onRenderEastFace(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        Tessellator.INSTANCE.setNormal(0.0F, 0.0F, -1.0F);
    }

    @Inject(
            method = "renderWestFace(Lnet/minecraft/block/BlockBase;DDDI)V",
            at = @At("HEAD")
    )
    private void onRenderWestFace(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        Tessellator.INSTANCE.setNormal(0.0F, 0.0F, 1.0F);
    }

    @Inject(
            method = "renderNorthFace(Lnet/minecraft/block/BlockBase;DDDI)V",
            at = @At("HEAD")
    )
    private void onRenderNorthFace(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        Tessellator.INSTANCE.setNormal(-1.0F, 0.0F, 0.0F);
    }

    @Inject(
            method = "renderSouthFace(Lnet/minecraft/block/BlockBase;DDDI)V",
            at = @At("HEAD")
    )
    private void onRenderSouthFace(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        Tessellator.INSTANCE.setNormal(1.0F, 0.0F, 0.0F);
    }
}
