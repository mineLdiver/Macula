package net.mine_diver.glsl.mixin;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TileRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TileRenderer.class)
public class MixinTileRenderer {

    @Inject(method = "method_46(Lnet/minecraft/block/BlockBase;DDDI)V", at = @At("HEAD"))
    private void onRenderBottomFace(CallbackInfo ci) { Tessellator.INSTANCE.method_1697(0.0F, -1.0F, 0.0F); }

    @Inject(method = "method_55(Lnet/minecraft/block/BlockBase;DDDI)V", at = @At("HEAD"))
    private void onRenderTopFace(CallbackInfo ci) {
        Tessellator.INSTANCE.method_1697(0.0F, 1.0F, 0.0F);
    }

    @Inject(method = "method_61(Lnet/minecraft/block/BlockBase;DDDI)V", at = @At("HEAD"))
    private void onRenderEastFace(CallbackInfo ci) {
        Tessellator.INSTANCE.method_1697(0.0F, 0.0F, -1.0F);
    }

    @Inject(method = "method_65(Lnet/minecraft/block/BlockBase;DDDI)V", at = @At("HEAD"))
    private void onRenderWestFace(CallbackInfo ci) {
        Tessellator.INSTANCE.method_1697(0.0F, 0.0F, 1.0F);
    }

    @Inject(method = "method_67(Lnet/minecraft/block/BlockBase;DDDI)V", at = @At("HEAD"))
    private void onRenderNorthFace(CallbackInfo ci) {
        Tessellator.INSTANCE.method_1697(-1.0F, 0.0F, 0.0F);
    }

    @Inject(method = "method_69(Lnet/minecraft/block/BlockBase;DDDI)V", at = @At("HEAD"))
    private void onRenderSouthFace(CallbackInfo ci) {
        Tessellator.INSTANCE.method_1697(1.0F, 0.0F, 0.0F);
    }
}
