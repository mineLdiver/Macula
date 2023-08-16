package net.mine_diver.glsl.gui;

import net.mine_diver.glsl.Shaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.client.gui.widgets.Button;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.resource.language.I18n;

public class ShadersScreen extends ScreenBase {
    private static final int
            SHADERS_FOLDER_BUTTON_ID = "glsl:shaders_folder".hashCode(),
            DONE_BUTTON_ID = "glsl:done".hashCode();

    private final ScreenBase parent;
    private int updateTimer = -1;
    private ScrollableShaders shaderList;

    public ShadersScreen(ScreenBase parent) {
        this.parent = parent;
    }

    @Override
    public void init() {
        super.init();
        final int btnWidth = 120;
        final int btnHeight = 20;
        final int baseY = 30;
        final int shaderListWidth = this.width - btnWidth - 20;
        this.shaderList = new ScrollableShaders(this, shaderListWidth, this.height, baseY, this.height - 50, 16);
        final int btnFolderWidth = Math.min(150, shaderListWidth / 2 - 10);
        final int xFolder = shaderListWidth / 4 - btnFolderWidth / 2;
        final int yFolder = this.height - 25;
        //noinspection unchecked
        buttons.add(new Button(SHADERS_FOLDER_BUTTON_ID, xFolder, yFolder, btnFolderWidth - 22 + 1, btnHeight, "Shaders Folder"));
        //noinspection unchecked
        buttons.add(new Button(DONE_BUTTON_ID, shaderListWidth / 4 * 3 - btnFolderWidth / 2, this.height - 25, btnFolderWidth, btnHeight, I18n.translate("gui.done")));
    }

    @Override
    protected void buttonClicked(Button button) {
        super.buttonClicked(button);
        if (button.id == DONE_BUTTON_ID)
            minecraft.openScreen(parent);
    }

    @Override
    public void render(int i, int j, float f) {
        renderBackground();
        shaderList.render(i, j, f);
        if (this.updateTimer <= 0) {
            this.shaderList.updateList();
            this.updateTimer += 20;
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
