package net.mine_diver.macula.compat.smoothbeta.shader;

import net.mine_diver.smoothbeta.client.render.gl.GlUniform;

public class MaculaUniform extends GlUniform {
    public MaculaUniform(String name, int dataType, int count) {
        super(name, dataType, count);
    }

    @Override
    public void upload() {}
}
