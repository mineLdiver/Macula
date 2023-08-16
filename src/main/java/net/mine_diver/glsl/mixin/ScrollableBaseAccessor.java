package net.mine_diver.glsl.mixin;

import net.minecraft.client.gui.widgets.ScrollableBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ScrollableBase.class)
public interface ScrollableBaseAccessor {
    @Accessor("x")
    int glsl_getWidth();
}
