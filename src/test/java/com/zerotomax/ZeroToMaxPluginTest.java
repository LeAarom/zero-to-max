package com.zerotomax;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

/** Run this class's main() from your IDE to launch RuneLite with the plugin loaded. */
public class ZeroToMaxPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(ZeroToMaxPlugin.class);
		RuneLite.main(args);
	}
}
