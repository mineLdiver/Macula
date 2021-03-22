package net.mine_diver.glsl.mixin;

import net.mine_diver.glsl.Shaders;
import net.mine_diver.glsl.util.TessellatorAccessor;
import net.minecraft.class_214;
import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.ARBVertexProgram;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

@Mixin(Tessellator.class)
public class MixinTessellator implements TessellatorAccessor {

    @Shadow private int field_2071;

    @Shadow private static boolean field_2055;

    @Shadow private int field_2069;

    @Shadow private boolean field_2067;

    @Shadow private int[] field_2060;

    @Shadow private int field_2068;

    @Inject(method = "<init>(I)V", at = @At("RETURN"))
    private void onCor(int var1, CallbackInfo ci) {
        shadersData = new short[] {-1, 0};
        shadersBuffer = class_214.method_744(var1 / 8 * 4);
        shadersShortBuffer = shadersBuffer.asShortBuffer();
    }

    @Redirect(method = "draw()V", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glDrawArrays(III)V"))
    private void onDraw(int mode, int first, int vertexCount) {
        if (Shaders.entityAttrib >= 0) {
            ARBVertexProgram.glEnableVertexAttribArrayARB(Shaders.entityAttrib);
            ARBVertexProgram.glVertexAttribPointerARB(Shaders.entityAttrib, 2, false, false, 4, (ShortBuffer) shadersShortBuffer.position(0));
        }
        GL11.glDrawArrays(mode, first, vertexCount);
        if (Shaders.entityAttrib >= 0)
            ARBVertexProgram.glDisableVertexAttribArrayARB(Shaders.entityAttrib);
    }

    @Inject(method = "method_1701()V", at = @At(value = "RETURN"))
    private void onReset(CallbackInfo ci) {
        shadersBuffer.clear();
    }

    @Inject(method = "pos(DDD)V", at = @At(value = "HEAD"))
    private void onAddVertex(CallbackInfo ci) {
        if (field_2071 == 7 && field_2055 && (field_2069 + 1) % 4 == 0 && field_2067) {
            field_2060[field_2068 + 6] = field_2060[(field_2068 - 24) + 6];
            shadersBuffer.putShort(shadersData[0]).putShort(shadersData[1]);
            field_2060[field_2068 + 8 + 6] = field_2060[(field_2068 + 8 - 16) + 6];
            shadersBuffer.putShort(shadersData[0]).putShort(shadersData[1]);
        }
        shadersBuffer.putShort(shadersData[0]).putShort(shadersData[1]);
    }

    @Override
    public void setEntity(int id) {
        shadersData[0] = (short) id;
    }

    public ByteBuffer shadersBuffer;
    public ShortBuffer shadersShortBuffer;
    public short[] shadersData;
}
