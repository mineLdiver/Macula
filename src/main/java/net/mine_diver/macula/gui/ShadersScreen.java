package net.mine_diver.macula.gui;

import net.mine_diver.macula.Shaders;
import net.mine_diver.macula.option.ShaderOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.resource.language.I18n;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ShadersScreen extends ScreenBase {
    private static final int
            SHADERS_FOLDER_BUTTON_ID = "macula:shaders_folder".hashCode(),
            DONE_BUTTON_ID = "macula:done".hashCode();

    private static final float[] QUALITY_MULTIPLIERS = new float[] {
            0.5F, 0.6F, 0.6666667F, 0.75F, 0.8333333F, 0.9F, 1, 1.1666666F, 1.3333334F, 1.5F, 1.6666666F, 1.8F, 2
    };
    private static final String[] QUALITY_MULTIPLIER_NAMES = new String[] {
            "0.5x", "0.6x", "0.66x", "0.75x", "0.83x", "0.9x", "1x", "1.16x", "1.33x", "1.5x", "1.66x", "1.8x", "2x"
    };
    private static final float QUALITY_MULTIPLIER_DEFAULT = 1;

    public static String toStringValue(float val, float[] values, String[] names) {
        int i = getValueIndex(val, values);
        return names[i];
    }

    private float getNextValue(float val, float[] values, float valDef, boolean forward, boolean reset) {
        if (reset)
            return valDef;
        else {
            int i = getValueIndex(val, values);

            if (forward) {
                ++i;

                if (i >= values.length)
                    i = 0;
            } else {
                --i;

                if (i < 0)
                    i = values.length - 1;
            }
            return values[i];
        }
    }

    public static int getValueIndex(float val, float[] values) {
        for (int i = 0; i < values.length; ++i) {
            float f = values[i];

            if (f >= val) return i;
        }

        return values.length - 1;
    }

    public static String toStringQuality(float val) {
        return toStringValue(val, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_NAMES);
    }

    private final ScreenBase parent;
    private int updateTimer = -1;
    private ScrollableShaders shaderList;
    private boolean rightClick;

    public ShadersScreen(ScreenBase parent) {
        this.parent = parent;
    }

    @Override
    public void init() {
        super.init();
        final int btnWidth = 120;
        final int btnHeight = 20;
        final int btnX = width - btnWidth - 10;
        final int baseY = 30;
        final int stepY = 20;
        final int shaderListWidth = width - btnWidth - 20;
        shaderList = new ScrollableShaders(this, shaderListWidth, height, baseY, height - 50, 16);
        //noinspection unchecked,PointlessArithmeticExpression
        buttons.add(new ShaderOptionButton(ShaderOption.SHADOW_RES_MUL, btnX, 0 * stepY + baseY, btnWidth, btnHeight));
        final int btnFolderWidth = Math.min(150, shaderListWidth / 2 - 10);
        final int xFolder = shaderListWidth / 4 - btnFolderWidth / 2;
        final int yFolder = height - 25;
        //noinspection unchecked
        buttons.add(new Button(SHADERS_FOLDER_BUTTON_ID, xFolder, yFolder, btnFolderWidth - 22 + 1, btnHeight, "Shaders Folder"));
        //noinspection unchecked
        buttons.add(new Button(DONE_BUTTON_ID, shaderListWidth / 4 * 3 - btnFolderWidth / 2, height - 25, btnFolderWidth, btnHeight, I18n.translate("gui.done")));
        updateButtons();
    }

    public void updateButtons() {
        //noinspection unchecked
        for (Button button : (List<Button>) buttons)
            if (button.id != SHADERS_FOLDER_BUTTON_ID && button.id != DONE_BUTTON_ID)
                button.active = Shaders.shaderPackLoaded;
    }

    @Override
    protected void mouseClicked(int i, int j, int action) {
        super.mouseClicked(i, j, action);
        if (action == 1) {
            rightClick = true;
            super.mouseClicked(i, j, 0);
            rightClick = false;
        }
    }

    @Override
    protected void buttonClicked(Button button) {
        super.buttonClicked(button);
        if (!button.active) return;
        if (button instanceof ShaderOptionButton) {
            ShaderOptionButton sob = (ShaderOptionButton) button;
            switch (sob.getEnumShaderOption()) {
                case SHADOW_RES_MUL:
                    Shaders.configShadowResMul = this.getNextValue(Shaders.configShadowResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !rightClick, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT));
                    Shaders.loadShaderPack();
                    break;
            }
            sob.updateButtonText();
        }
        if (button.id == SHADERS_FOLDER_BUTTON_ID) {
            try {
                Desktop.getDesktop().open(Shaders.shaderPacksDir);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (button.id == DONE_BUTTON_ID) {
            Shaders.storeConfig();
            minecraft.openScreen(parent);
        }
    }

    @Override
    public void render(int i, int j, float f) {
        renderBackground();
        shaderList.render(i, j, f);
        if (updateTimer <= 0) {
            shaderList.updateList();
            updateTimer += 20;
        }
        drawTextWithShadowCentred(textManager, "Shaders", width / 2, 15, 0xffffff);
        String debug = "OpenGL: " + Shaders.glVersionString + ", " + Shaders.glVendorString + ", " + Shaders.glRendererString;
        int debugWidth = textManager.getTextWidth(debug);
        if (debugWidth < width - 5)
            drawTextWithShadowCentred(textManager, debug, width / 2, height - 40, 0x808080);
        else drawTextWithShadow(textManager, debug, 5, height - 40, 0x808080);
        super.render(i, j, f);
    }

    @Override
    public void tick() {
        super.tick();
        updateTimer--;
    }

    Minecraft getMc() {
        return minecraft;
    }

    TextRenderer getTextRenderer() {
        return textManager;
    }
}
