package net.mine_diver.macula.compat.smoothbeta.mixin;

import net.mine_diver.smoothbeta.client.render.RenderRegion;
import net.mine_diver.smoothbeta.client.render.gl.GlUniform;
import net.modificationstation.stationapi.api.util.math.Vec3f;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RenderRegion.class)
public class RenderRegionMixin {
    @Redirect(
            method = "method_1909",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/mine_diver/smoothbeta/client/render/gl/GlUniform;set(FFF)V"
            )
    )
    private void macula_translate(GlUniform instance, float value1, float value2, float value3) {
        GL11.glPushMatrix();
        GL11.glTranslatef(value1, value2, value3);
    }

    @Redirect(
            method = "method_1909",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/mine_diver/smoothbeta/client/render/gl/GlUniform;upload()V"
            )
    )
    private void macula_stopUpload(GlUniform instance) {}

    @Redirect(
            method = "method_1909",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/mine_diver/smoothbeta/client/render/gl/GlUniform;set(Lnet/modificationstation/stationapi/api/util/math/Vec3f;)V"
            )
    )
    private void macula_pop(GlUniform instance, Vec3f vector) {
        GL11.glPopMatrix();
    }
}
