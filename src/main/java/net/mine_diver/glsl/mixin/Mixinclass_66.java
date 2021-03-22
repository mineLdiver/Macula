package net.mine_diver.glsl.mixin;

import net.mine_diver.glsl.Shaders;
import net.mine_diver.glsl.util.TessellatorAccessor;
import net.minecraft.block.BlockBase;
import net.minecraft.class_66;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TileRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_66.class)
public class Mixinclass_66 {

    @Redirect(method = "method_296()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/TileRenderer;method_57(Lnet/minecraft/block/BlockBase;III)Z"))
    private boolean onRenderBlockByRenderType(TileRenderer tileRenderer, BlockBase var24, int var17, int var15, int var16) {
        if (Shaders.entityAttrib >= 0)
            ((TessellatorAccessor) Tessellator.INSTANCE).setEntity(var24.id);
        return tileRenderer.method_57(var24, var17, var15, var16);
    }

    @Inject(method = "method_296()V", at = @At(value = "RETURN"))
    private void onUpdateRenderer(CallbackInfo ci) {
        if (Shaders.entityAttrib >= 0)
            ((TessellatorAccessor) Tessellator.INSTANCE).setEntity(-1);
    }
}
