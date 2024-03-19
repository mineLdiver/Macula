package net.mine_diver.macula.mixin;

import net.mine_diver.macula.Shaders;
import net.mine_diver.macula.util.TessellatorAccessor;
import net.minecraft.class_214;
import net.minecraft.client.render.Tessellator;
import org.lwjgl.opengl.ARBVertexProgram;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.nio.ByteBuffer;
import java.nio.ShortBuffer;

@Mixin(Tessellator.class)
public class TessellatorMixin implements TessellatorAccessor {
    @Shadow private int drawingMode;

    @Shadow private static boolean useTriangles;

    @Shadow private int vertexAmount;

    @Shadow private boolean hasNormals;

    @Shadow private int[] bufferArray;

    @Shadow private int field_2068;

    @Inject(
            method = "<init>(I)V",
            at = @At("RETURN")
    )
    private void onCor(int var1, CallbackInfo ci) {
        shadersData = new short[] {-1, 0};
        shadersBuffer = class_214.method_744(var1 / 8 * 4);
        shadersShortBuffer = shadersBuffer.asShortBuffer();
    }

    @Inject(
            method = "draw()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;glDrawArrays(III)V"
            )
    )
    private void onDraw1(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        if (Shaders.entityAttrib >= 0) {
            ARBVertexProgram.glEnableVertexAttribArrayARB(Shaders.entityAttrib);
            ARBVertexProgram.glVertexAttribPointerARB(Shaders.entityAttrib, 2, false, false, 4, (ShortBuffer) shadersShortBuffer.position(0));
        }
    }

    @Inject(
            method = "draw()V",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/lwjgl/opengl/GL11;glDrawArrays(III)V",
                    shift = At.Shift.AFTER
            )
    )
    private void onDraw2(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        if (Shaders.entityAttrib >= 0)
            ARBVertexProgram.glDisableVertexAttribArrayARB(Shaders.entityAttrib);
    }

    @Inject(
            method = "clear()V",
            at = @At(value = "RETURN")
    )
    private void onReset(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        shadersBuffer.clear();
    }

    @Inject(
            method = "addVertex(DDD)V",
            at = @At(value = "HEAD")
    )
    private void onAddVertex(CallbackInfo ci) {
        if (!Shaders.shaderPackLoaded) return;
        if (drawingMode == 7 && useTriangles && (vertexAmount + 1) % 4 == 0 && hasNormals) {
            bufferArray[field_2068 + 6] = bufferArray[(field_2068 - 24) + 6];
            shadersBuffer.putShort(shadersData[0]).putShort(shadersData[1]);
            bufferArray[field_2068 + 8 + 6] = bufferArray[(field_2068 + 8 - 16) + 6];
            shadersBuffer.putShort(shadersData[0]).putShort(shadersData[1]);
        }
        shadersBuffer.putShort(shadersData[0]).putShort(shadersData[1]);
    }

    @Override
    @Unique
    public void setEntity(int id) {
        shadersData[0] = (short) id;
    }

    public ByteBuffer shadersBuffer;
    public ShortBuffer shadersShortBuffer;
    public short[] shadersData;
}
