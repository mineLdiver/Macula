package net.mine_diver.macula.compat.smoothbeta.shader;

import net.mine_diver.macula.compat.smoothbeta.mixin.GlUniformAccessor;
import net.mine_diver.smoothbeta.client.render.Shader;
import net.mine_diver.smoothbeta.client.render.VertexFormat;
import net.mine_diver.smoothbeta.client.render.gl.GlUniform;
import net.modificationstation.stationapi.api.resource.ResourceFactory;

import java.io.IOException;

public class MaculaShader extends Shader {
    public MaculaShader(ResourceFactory factory, String name, VertexFormat format) throws IOException {
        super(factory, name, format);
    }

    @Override
    public GlUniform getUniform(String name) {
        GlUniformAccessor original = (GlUniformAccessor) super.getUniform(name);
        return new MaculaUniform(name, original.getDataType(), original.getCount());
    }

    @Override
    public void unbind() {}

    @Override
    public void bind() {}
}
