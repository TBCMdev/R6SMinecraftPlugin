# R6SMinecraftPlugin
R6s for minecraft!



### Hi! thanks for checking out our new Plugin for Minecraft. Here is where you can get useful info on what the project is about and help with blank errors or problems.

---------------------
INFO
---------------------

---------------------
COMMANDS
---------------------

all commands for the plugin are listed here!:

`start:
    usage: /<command>
    description: starts the game
  jointeamred:
    usage: </command>
    description: joins red team
  jointeamblue:
    usage: </command>
    description: joins blue team
  ability:
    usage: </command>
    description: gives ability obj
  attkoperator:
    usage: </command> [ash|capitao|finka]
    description: picks attack operator of users choice
  defoperator:
    usage: </command> [doc|rook|aruni]
    description: picks defense operator of users choice
  override:
    usage: </command> [movement] [true|false]
    description: overrides a parameter to a given value
  showPageBlue:
    usage: </command> [default|doc|rook|Aruni] [1|2|3]
  showPageRed:
    usage: </command> [ash|capitao|finka] [1|2|3]
  tbcm-dc:
    usage: </command> [KEEPAMMO|TESTING|error_config|class_reload] [true|false|get|className] [className] [indexer]
    description: a development command used for testing. (IN BETA)`

---------------------
INSTALLATION
---------------------
this will go through the slightly complicated way of setting up this plugin. what you will need:

**_`THE RAINBOW SIX SIEGE HOUSE MAP`_**

[_map download link_](https://www.planetminecraft.com/project/rainbow-six-siege-house-map/)

**_`THE R6SMC PLUGIN`_**

[_link coming soon!_]

**_`MGS GUNS`_**

[MGS guns DataPack](https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbkNHYURGdWpybGZ4b0NDalhJQ1RrbzMwZjY0QXxBQ3Jtc0tscEVZX0RwdGNzdWp0bmlIVUZLZml4ZEVHS3hDTWpHZklIXzZ0NzRpUGZkbUZnNnBMblhvQ2F3S3BieTVyQS1rQmlqWjFrUndJN2h1U2hLZDIzb1NSMlh1VTV2OHBFclVRanhHQ0tRVHVNSnBuS3FWYw&q=https%3A%2F%2Fwww.dropbox.com%2Fs%2F16k2j63xdrqouiz%2FMGS_3.1_datapack.zip%3Fdl%3D1)

**_`MGS TEXTURE PACK`_**

[MGS texture pack](https://www.youtube.com/redirect?event=video_description&redir_token=QUFFLUhqbFpUOXNUOVNnbWN3QnFNWWpFR3pRWU50aHZSd3xBQ3Jtc0trMlE0cFFERTRjT0R0VVFuMXFWRUpwTTJCSWdMY3dNX09RcGlJOERMTGQ5T0FMdFVEMVNBSE9NNDhvdjJWbGdtWldXWUpTUzZFX1FXd0RrZy1YTGc3RVRiX25xc1JKb3JTLWlZWFJvbDZ2YVVTRWpWVQ&q=https%3A%2F%2Fwww.dropbox.com%2Fs%2Fsptnb7jw5h66n79%2FMGS_3.1_resources.zip%3Fdl%3D1)

**_`R6SMC TEXTURE PACK`_**

[_Link coming soon!_]


---------------------
INSTRUCTIONS
--------------------
1. first we need to install the MGS _TEXTURE PACK_. Navigate to your Minecraft folder(after downloading MGS) by entering _%appdata%_ and locating your .Minecraft folder. from there do not unzip the MGS.zip file and place it in the ResourcePack folder.

2. Next install the MGS GUNS file and locate the world you want to place it in. if you want yours in a server(which is intended for R6S MC) find the world folder and MAKE SURE IT IS NAMED `world`. the plugin will not run without this feature but it will be changed in a future release. Then locate the DataPack folder and place the ZIP in there.

3. Next install the R6S MC jar. this file is a plugin and needs to be put in the Plugins folder in your server. locate that folder and place the jar in there. make sure again to not open the jar.

4. now we need to download the Map. download the folder and place it in your server folder. delete your old world or move it if you want to keep the data, and RENAME the House Map to **_`world`_**. see above if you are confused as to why.

5. Finally download the R6S MGS Texture Pack and locate your .Minecraft folder again(type _%appdata%_ into your windows search bar) and place it in the Resource Pack folder. Then go into minecraft and before you test the plugins make sure the server has no errors(if it does, contact me), and make sure to enable both resource packs(MGS GUNS RESOURCE, R6SMC TEXTURE PACK).

---------------------
ERROR IMPLEMENTATION
---------------------
R6S Minecraft has a lot of bugs, so here is what you can do with them all if you get them. there will be a TextDocument with all the necessary errors and what they mean, and if yours isn't there please contact me. 

First of all the plugin is equipped with an error class that spits errors that are custom written. The errors can be customised by command to broadcast to all team members or to just a certain person. if any error is sent to a player playing then the _/reload_ command will restart your game, and all your plugins, but I have made some workarounds. the command _/tbcm-dc error_config reload_ will reload the error class. if the error still persists, here are some options:

* **_`/tbcm-dc class_reload [className]`_**
* **_`/reload`_**
* **_`/tbcm-dc error_config reload`_**
* **_`/tbcm-dc help`_**

due to my limitations in Java and programming, the reload function will just try to reset most of the data stored in it. make sure that your game has ended before these commands are run or you will have to _/reload_ as the game will not function properly. the **_[className]_** parameter with all the class names can be found in the TextDocument below. To change the ErrorClass State for sending errors, enter this command:

**_`/tbcm-dc error_config state [state(int)]`_**


the **_[state(int)]_** is just a number. for example:

**_`/tbcm-dc error_config state 1`_**

* 1 will set it to send errors to the player who runs this command(they must have perms).
* 2 will set it to broadcast to all players on the Red and Blue team.
* 3 will set it to broadcast to everyone on the Blue team.
* 4 will set it to broadcast to everyone on the Red team.
more info is in the text document link below.


**_DISCLAIMER_**

if you run the command to reload a class, and you receive this message:

**_`Reloading class failed. Best course of action is to /reload your server or restart it.`_**`

PLEASE follow the instructions the command gives you.

