package net.mine_diver.macula.compat.smoothbeta.mixin;

import net.mine_diver.macula.compat.smoothbeta.shader.MaculaShader;
import net.mine_diver.smoothbeta.client.render.Shader;
import net.mine_diver.smoothbeta.client.render.Shaders;
import net.mine_diver.smoothbeta.client.render.VertexFormat;
import net.modificationstation.stationapi.api.resource.ResourceFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;

@Mixin(Shaders.class)
public class ShadersMixin {
    @Redirect(
            method = "lambda$loadShaders$0",
            at = @At(
                    value = "NEW",
                    target = "(Lnet/modificationstation/stationapi/api/resource/ResourceFactory;Ljava/lang/String;Lnet/mine_diver/smoothbeta/client/render/VertexFormat;)Lnet/mine_diver/smoothbeta/client/render/Shader;"
            ),
            remap = false
    )
    private static Shader macula_replaceShader(ResourceFactory exception, String jsonElement, VertexFormat i) throws IOException {
        return new MaculaShader(exception, jsonElement, i);
    }
}
