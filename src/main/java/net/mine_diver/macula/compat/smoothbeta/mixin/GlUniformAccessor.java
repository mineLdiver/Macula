package net.mine_diver.macula.compat.smoothbeta.mixin;

import net.mine_diver.smoothbeta.client.render.gl.GlUniform;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GlUniform.class)
public interface GlUniformAccessor {
    @Accessor
    int getDataType();

    @Accessor
    int getCount();
}
