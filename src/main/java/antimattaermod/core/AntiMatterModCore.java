package antimattaermod.core;

import antimattaermod.core.Block.CoreBlocks;
import antimattaermod.core.item.CoreItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.versioning.ArtifactVersion;

/** <h1>AntiMatterModCore</h1>
 * AntiMatterModCora MainClass<br>
 * 
 * @author Raiti, C6H2Cl2
 * @version 1.0.0
 */
@Mod(modid = AntiMatterModCore.MOD_ID,useMetadata = true,dependencies = "required-after:Forge@[10.13.4.1558,)")
public class AntiMatterModCore {

	public static final String MOD_ID = "AntiMatterModCore";
	public static final String MOD_NAME = "AntiMatterMod Core";
	public static final String MOD_VERSION = "1.0.0";

	@Mod.Metadata
	public static ModMetadata modMetadata;
	
	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		loadMeta(modMetadata);
		CoreItems.addItem();
		CoreBlocks.addBlock();
		// TODO: アイテム追加など
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		// TODO: GUIハンドラなどの設定
	}
	
	@Mod.EventHandler
	public void posinit(FMLPostInitializationEvent event) {
		// TODO: 知らん
	}

	private void loadMeta(ModMetadata metadata){
		metadata.modId = MOD_ID;
		metadata.name = MOD_NAME;
		metadata.version = MOD_VERSION;
		metadata.authorList.add("C6H2Cl2");
		metadata.authorList.add("Raiti-Chan");
		metadata.authorList.add("Kojin15");
		metadata.authorList.add("WorldOfTheTakumi");
        metadata.authorList.add("Sora-Suke");
		metadata.description = "Make Anti-Matter in Minecraft!";
        metadata.autogenerated = false;
	}
}
