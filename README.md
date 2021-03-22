# Fabric Example Mod with BIN Mappings for beta 1.7.3 server + client

## Setup

For setup instructions please see the [fabric wiki page](https://fabricmc.net/wiki/tutorial:setup) that relates to the IDE that you are using.

NOTE: There is no Fabric-API for beta 1.7.3!  
There is [StationLoader](com/modificationstation/StationLoader) and [Cursed-Legacy-API](https://github.com/minecraft-cursed-legacy/Cursed-Legacy-API) though.

Extra steps for better mixin making in IntelliJ:

1. Go to `File > Settings...`
2. Go to `Plugins > The gear in the top middle > Manage Plugin Repositories...`
3. Press `+` and add one of these: (You can check your version in `Help > About`)
    - If using 2019.2: https://raw.githubusercontent.com/Earthcomputer/MinecraftDev/dev_new/updates/updatePlugins-192.xml
    - If using 2019.3: https://raw.githubusercontent.com/Earthcomputer/MinecraftDev/dev_new/updates/updatePlugins-193.xml
    - If using 2020.1: https://raw.githubusercontent.com/Earthcomputer/MinecraftDev/dev_new/updates/updatePlugins-201.xml
4. Refresh the plugin repo (restarting will do the trick).
5. Go back to plugins and install the `Minecraft Development` plugin.
6. Restart again.
7. Profit!

## Using a Mod

You will want to use the [Cursed Fabric MultiMC Instance](https://github.com/calmilamsy/Cursed-Fabric-MultiMC)

## Common Issues

**Gradle says something about not being able to resolve Minecraft or another dependency!**  
Just go into `C:\users\<username>\.gradle\caches` (`~/.gradle/caches` on other OSes), delete `fabric-loom` and refresh Gradle.

**My Minecraft run profile is missing from the top right!**  
Go into `Run Configurations > Edit...` and click the dropdown for applications. Click on minecraft and press OK. Both client and server run profiles should both in the rop right now.

**I moved my project and everything broke!**  
Go into run configurations and double check your `VM Options` and `Working Directory` parameters.

**X mixin isnt working!**  
Double check your target and method parameters and make sure you have filled out your `@At` parameter if required.  
If you are still having issues, join the [ModStation Discord](https://discord.gg/8Qky5XY) and contact someone there.

## License

This template is available under the CC0 license. Feel free to learn from it and incorporate it in your own projects.
