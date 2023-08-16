package net.mine_diver.macula.gui;

import net.mine_diver.macula.Shaders;
import net.mine_diver.macula.option.EnumShaderOption;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.resource.language.I18n;

public class ShaderOptionButton extends Button {
    private final EnumShaderOption enumShaderOption;
    
    public ShaderOptionButton(final EnumShaderOption enumShaderOption, final int x, final int y, final int widthIn, final int heightIn) {
        super(enumShaderOption.ordinal(), x, y, widthIn, heightIn, getButtonText(enumShaderOption));
        this.enumShaderOption = enumShaderOption;
    }
    
    public EnumShaderOption getEnumShaderOption() {
        return this.enumShaderOption;
    }
    
    private static String getButtonText(final EnumShaderOption eso) {
        final String nameText = I18n.translate(eso.getResourceKey()) + ": ";
        return switch (eso) {
            case SHADOW_RES_MUL -> nameText + ShadersScreen.toStringQuality(Shaders.configShadowResMul);
        };
    }
    
    public void updateButtonText() {
        this.text = getButtonText(this.enumShaderOption);
    }
}
