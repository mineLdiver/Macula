package net.mine_diver.glsl.mixin;

import net.mine_diver.glsl.Shaders;
import net.mine_diver.glsl.util.TessellatorAccessor;
import net.minecraft.block.BlockBase;
import net.minecraft.class_66;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderer;
import net.minecraft.level.WorldPopulationRegion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashSet;

@Mixin(class_66.class)
public class ChunkBuilderMixin {
    @Inject(
            method = "method_296()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/block/BlockRenderer;render(Lnet/minecraft/block/BlockBase;III)Z"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void onRenderBlockByRenderType(CallbackInfo ci, int var1 , int var2, int  var3, int  var4, int  var5, int  var6, HashSet var7, int  var8, WorldPopulationRegion var9, BlockRenderer  var10, int  var11, int  var12, int  var13, int  var14, int  var15, int  var16, int  var17, int  var18, BlockBase  var19) {
        if (!Shaders.shaderPackLoaded) return;
        if (Shaders.entityAttrib >= 0)
            ((TessellatorAccessor) Tessellator.INSTANCE).setEntity(var19.id);
    }

    @Inject(method = "method_296()V", at = @At(value = "RETURN"))
    private void onUpdateRenderer(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        if (Shaders.entityAttrib >= 0)
            ((TessellatorAccessor) Tessellator.INSTANCE).setEntity(-1);
    }
}
