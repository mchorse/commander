![Commander](https://i.imgur.com/IFxwMOd.png)

[Planet Minecraft page](http://www.planetminecraft.com/mod/blockbuster-machinima-mod/) – [CurseForge page](https://minecraft.curseforge.com/projects/commander) – [Source code](https://github.com/mchorse/commander) 

**Commander** is a Minecraft mod which allows enhances command usage experience. Currently, this mods adds only one command to execute commands in a loop. 

I don't recommend using this mod on a server, since it's very easy to get the server lag, freeze or crash with the loop command. **If you're going to use it on the public server, use it at your own risk**.

## Install

Install [Minecraft Forge](http://files.minecraftforge.net/), download the latest stable version of jar file for available minecraft version. Put it in minecraft's `mods` folder, and launch the game.

After that, Commander mod should be installed and will appear in Minecraft's mods menu. If Commander didn't appear in the mods menu, then something went wrong. 

## How to use

At the moment, commander provides `/forin` command which allows executing commands in a loop. `/forin` command's syntax is following:

    /forin <start> <end> <command>

Where `<start>` is first index, and `<end>` is the value that you want `/forin` to iterate between. Both of those arguments must be integers, and the step value is `1`. Also, you can nest `/forin` command only **three** times.

`<command>` argument can be any valid commands, however, besides that, `<command>` accepts variables through `@{...}` construction. For example, if you want to spawn 5 creepers in a row across X axis, you can use this `/forin` like this:

    /forin 0 4 summon Creeper ~@{i} ~ ~
    # Or for 1.11.2+
    /forin 0 4 summon minecraft:creeper ~@{i} ~ ~

This command will evaluate into this:

    /summon Creeper ~0 ~ ~
    /summon Creeper ~1 ~ ~
    /summon Creeper ~2 ~ ~
    /summon Creeper ~3 ~ ~
    /summon Creeper ~4 ~ ~

As you can see, `@{i}` construction was replaced with the index. Within `@{...}` following variables are present when using `/forin` command:

|Name|Description|
|-|-|
|`i`|Index of the `/forin` loop|
|`j`|Index of the `/forin` loop (second nesting)|
|`k`|Index of the `/forin` loop (third nesting)|
|`i_c`, `j_c` and `k_c`|Count of iteration for given iteration|
|`i_s`, `j_s` and `k_s`|`<start>` index you passed into `/forin` command for given iteration|
|`x`|X coordinate of command sender in the world|
|`y`|Y coordinate of command sender in the world|
|`z`|Z coordinate of command sender in the world|

Besides supporting variables, you can also use mathematical expressions within `@{...}` construct. For example, if you want to spawn 10 creepers around you in the circle (within `10` block radius), you can use this command:

    /forin 0 9 summon Creeper ~@{cos(i/i_c*PI*2)*10} ~ ~@{sin(i/i_c*PI*2)*10}

See [this page](https://github.com/mchorse/aperture/wiki/Math-Expressions) for more information. 

And finally, Commander mod supports multiple commands executed in one line by separating commands using `||` symbols:

    /particle explode ~ ~ ~ 0.1 0.1 0.1 0.1 10 || setblock ~ ~ ~ minecraft:stone

## Video

Here is also a short showcase video of some of the usages of this mod:

<a href="https://youtu.be/R_Nnu-iLj1c"><img src="https://img.youtube.com/vi/R_Nnu-iLj1c/0.jpg"></a> 

## For mod reviewers and reposters

When reposting my mod on your own website or reviewing it, please consider following (if you want to support me and my mod):

* Don't distort the mod name. It's the *Commander* mod.
* Make sure that information and description of my mod is legit. Misleading information, like Minecraft version support or non-existent features, is your responsibility.
* By uploading a custom build of this mod, the build becomes your responsibility.
* Provide the source link, please. [CurseForge](https://minecraft.curseforge.com/projects/commander) page is preferable.
* Provide a link to my [YouTube channel](https://www.youtube.com/channel/UCWVDjAcecHHa8UrEWMRGI8w), please. This will be really appreciated! 
* You can use Commander [banner](https://i.imgur.com/IFxwMOd.png) or [cover](https://i.imgur.com/rzHbVzC.png) for your repost page. Don't apply the watermark, though, that's just rude.

If you're interested in this project, you might as well follow me on any of social media accounts listed below:

[![YouTube](http://i.imgur.com/yA4qam9.png)](https://www.youtube.com/channel/UCWVDjAcecHHa8UrEWMRGI8w) [![Discord](http://i.imgur.com/gI6JEpJ.png)](https://discord.gg/qfxrqUF) [![Twitter](http://i.imgur.com/6b8vHcX.png)](https://twitter.com/McHorsy) [![GitHub](http://i.imgur.com/DmTn1f1.png)](https://github.com/mchorse) 

Also, I would really appreciate if you will support me on Patreon!

[![Become my Patron](https://i.imgur.com/4pQZ2xW.png)](https://www.patreon.com/McHorse) 

## Bug reports

If you found a bug, or this mod crashed your game. I'll appreciate if you could report a bug or a crash to me either on [issue tracker](https://github.com/mchorse/commander/issues/), on PM or on [Twitter](https://twitter.com/McHorsy). Please, make sure to attach a crash log ([pastebin](http://pastebin.com) please) and description of a bug or crash and the way to reproduce it. Thanks!