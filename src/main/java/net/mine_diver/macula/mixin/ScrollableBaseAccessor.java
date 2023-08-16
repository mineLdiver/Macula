package net.mine_diver.macula.mixin;

import net.minecraft.client.gui.widgets.ScrollableBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ScrollableBase.class)
public interface ScrollableBaseAccessor {
    @Accessor("x")
    int macula_getWidth();
}
