![bstats Metrics](assets/MagmaKT.png)
![bStats Servers](https://img.shields.io/bstats/servers/16417?style=for-the-badge)
![bStats Players](https://img.shields.io/bstats/players/16417?style=for-the-badge)
![GitHub](https://img.shields.io/github/license/JaLuMu/MagmaKT?style=for-the-badge)
![GitHub contributors](https://img.shields.io/github/contributors/JaLuMu/MagmaKT?style=for-the-badge)

**MagmaKT is still in development and currently not suitable for productive servers!**

## What is MagmaKT?
MagmaKT is a framework for easy Minecraft plugin development. It allows running a plugin with a single codebase on multiple Minecraft servers and proxies. It can be easily extended with internal, but also external modules. Most modules can be used on multiple platforms without changing the plugin's code.

## Main Features
- Runs on Bukkit and Bungeecord (Velocity and Sponge support will be added later)
- One API, Multiple Platforms Supported
- Can be easily integrated into existing plugins
- Can be easily extended with modules

## Add MagmaKT to your project
### Add MagmaKT via Jitpack
Add the Jitpack maven repository

	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
Add the dependency to your project

	dependencies {
	        implementation 'de.jalumu:MagmaKT:main-SNAPSHOT'
	}

## MagmaKT Statistics
![bstats Metrics](https://bstats.org/signatures/bukkit/MagmaKt-Bukkit.svg)