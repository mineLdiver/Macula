package net.mine_diver.glsl.mixin.gui;

import net.mine_diver.glsl.gui.ShadersScreen;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.screen.menu.VideoSettings;
import net.minecraft.client.gui.widgets.Button;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VideoSettings.class)
public class VideoSettingsScreenMixin extends ScreenBase {
    @Unique
    private static final int GLSL$SHADERS_BUTTON_ID = "glsl:shaders".hashCode();

    @ModifyVariable(
            method = "init",
            at = @At(
                    value = "CONSTANT",
                    args = "stringValue=gui.done"
            ),
            index = 2
    )
    private int glsl_addShadersButton(int y) {
        //noinspection unchecked
        buttons.add(new Button(GLSL$SHADERS_BUTTON_ID, width / 2 - 155 + y % 2 * 160, height / 6 + 24 * (y >> 1), 150, 20, "Shaders..."));
        return y + 1;
    }

    @Inject(
            method = "buttonClicked",
            at = @At("HEAD"),
            cancellable = true
    )
    private void glsl_shadersButtonClicked(Button button, CallbackInfo ci) {
        if (button.id == GLSL$SHADERS_BUTTON_ID) {
            minecraft.options.saveOptions();
            minecraft.openScreen(new ShadersScreen(this));
            ci.cancel();
        }
    }
}
