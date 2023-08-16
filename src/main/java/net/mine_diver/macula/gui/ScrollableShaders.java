package net.mine_diver.macula.gui;

import net.mine_diver.macula.Shaders;
import net.mine_diver.macula.mixin.ScrollableBaseAccessor;
import net.minecraft.client.gui.widgets.ScrollableBase;
import net.minecraft.client.render.Tessellator;

import java.util.List;

class ScrollableShaders extends ScrollableBase {
    private List<String> shaderslist;
    private int selectedIndex;
    private final long lastClicked = Long.MIN_VALUE;
    private long lastClickedCached = 0L;
    final ShadersScreen shadersGui;

    public ScrollableShaders(ShadersScreen par1GuiShaders, int width, int height, int top, int bottom, int slotHeight) {
        super(par1GuiShaders.getMc(), width, height, top, bottom, slotHeight);
        this.shadersGui = par1GuiShaders;
        this.updateList();
    }

    public void updateList() {
        this.shaderslist = Shaders.listOfShaders();
        this.selectedIndex = 0;
        int i = 0;

        for (int j = this.shaderslist.size(); i < j; ++i) {
            if (this.shaderslist.get(i).equals(Shaders.currentShaderName)) {
                this.selectedIndex = i;
                break;
            }
        }
    }

    @Override
    protected int getSize() {
        return this.shaderslist.size();
    }

    @Override
    protected void entryClicked(int index, boolean twice) {
        if (index == this.selectedIndex && this.lastClicked == this.lastClickedCached) return;
        this.selectIndex(index);
    }

    private void selectIndex(int index) {
        this.selectedIndex = index;
        this.lastClickedCached = this.lastClicked;
        Shaders.setShaderPack(this.shaderslist.get(index));
        shadersGui.updateButtons();
    }

    @Override
    protected boolean isEntrySelected(int index) {
        return index == this.selectedIndex;
    }

    protected void renderBackground() {}

    @Override
    protected void renderEntry(int index, int posX, int posY, int contentY, Tessellator tessellator) {
        String s = this.shaderslist.get(index);

        if (s.equals("OFF")) {
            s = "OFF";
        } else if (s.equals("(internal)")) {
            s = "(internal)";
        }

        this.shadersGui.drawTextWithShadowCentred(shadersGui.getTextRenderer(), s, ((ScrollableBaseAccessor) this).macula_getWidth() / 2, posY + 1, 0xe0e0e0);
    }
}
