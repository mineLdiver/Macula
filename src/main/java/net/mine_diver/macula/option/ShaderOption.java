package net.mine_diver.macula.option;

public enum ShaderOption {
    SHADOW_RES_MUL("Shadow Quality", "shadowResMul", "1.0"),
    SHADER_PACK("options.macula:shaderPack", "shaderPack", "OFF");

    private final String resourceKey;
    private final String propertyKey;
    private final String valueDefault;
    
    ShaderOption(final String resourceKey, final String propertyKey, final String valueDefault) {
        this.resourceKey = resourceKey;
        this.propertyKey = propertyKey;
        this.valueDefault = valueDefault;
    }
    
    public String getResourceKey() {
        return this.resourceKey;
    }
    
    public String getPropertyKey() {
        return this.propertyKey;
    }
    
    public String getValueDefault() {
        return this.valueDefault;
    }
}
