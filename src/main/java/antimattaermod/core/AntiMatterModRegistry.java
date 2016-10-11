package antimattaermod.core;

import antimattaermod.core.Block.Ores.BedrockOreBlock;
import antimattaermod.core.Energy.Machine.BlockAlloySmelter;
import antimattaermod.core.Energy.Machine.TileAlloySmelter;
import antimattaermod.core.Energy.Transfer.BlockCable;
import antimattaermod.core.Energy.Transfer.TileEntityCable;
import antimattaermod.core.Item.ItemBlock.CableItemBlock;
import antimattaermod.core.Item.ItemBlock.MetaItemBlock;
import antimattaermod.core.Block.Ores.CrystalOreBlock;
import antimattaermod.core.Block.Ores.OreBlock;
import antimattaermod.core.Energy.Generator.Block.BlockFurnaceGenerator;
import antimattaermod.core.Energy.Generator.TileEntity.TileEntityFurnaceGenerator;
import antimattaermod.core.Item.StatesChecker;
import antimattaermod.core.Util.AddInformationfunction;
import antimattaermod.core.Util.ItemUtil;
import antimattaermod.core.World.OreGenerator;
import antimattaermod.core.crafting.RecipeRemover;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static antimattaermod.core.AntiMatterModCore.proxy;

/**
 * @author C6H2Cl2
 */
public class AntiMatterModRegistry {
	
    //CreativeTab ======================================================================================================
    //鉱石
    public static final CreativeTabs tabOreBlock = new CreativeTabs("tabOreBlock") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.diamond_ore);
        }
    };
    //素材
    public final static CreativeTabs tabMaterials = new CreativeTabs("ammaterials") {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return ingot_01;
        }
    };
    //機械・発電機
    public final static CreativeTabs tabMachines = new CreativeTabs("ammachines") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(Blocks.furnace);
        }
    };


    //==================================================================================================================
	
    //Item  ============================================================================================================
    //素材
	public static Item ingot_01 = ItemUtil.CreateItem("ingot_01","ingot/ingot_01",17,AntiMatterModRegistry.tabMaterials, AddInformationfunction::IngotInformation);
    public static Item crystal_01 = ItemUtil.CreateItem("crystal_01","crystal/crystal_01",7,AntiMatterModRegistry.tabMaterials);
    public static Item wire = ItemUtil.CreateItem("wire_01","wire/wire_01",1,AntiMatterModRegistry.tabMaterials);

    
    //ツール類
    public static final Item statesChecker = new StatesChecker();
    //==================================================================================================================

    //Block  ===========================================================================================================
    //鉱石
    public static Block crystalOreBlock_1 = new CrystalOreBlock(Material.rock, "crystalOreBlock_01", "crystalore/crystaloreblock_01", AntiMatterModRegistry.tabOreBlock, 2, new float[]{5.0F,5.0F}, new byte[]{3,3}, crystal_01, new int[]{0,1});
    public static Block getBedrockCrystalOreBlock_1 = new BedrockOreBlock("bedrockCrystalOreBlock_01",crystalOreBlock_1);
    
    public static Block oreBlock_1 = new OreBlock(Material.rock, "oreBlock_01", "ore/oreblock_01", AntiMatterModRegistry.tabOreBlock, 16, new float[]{5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F,5.0F}, new byte[]{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3});
    public static Block bedrockOreBlock_1 = new BedrockOreBlock("bedrockOreBlock_01",oreBlock_1);

    
    //発電機
    public static final Block furnaceGenerator = new BlockFurnaceGenerator();
    public static final Block cable = new BlockCable(Material.rock);

    //機械
    public static final Block[] alloySmelter = {new BlockAlloySmelter(1),new BlockAlloySmelter(2),new BlockAlloySmelter(3)};
    //==================================================================================================================


    //preinitで行う登録処理
    static void registerPreInit(FMLPreInitializationEvent event){
        //Itemの登録
        GameRegistry.registerItem(crystal_01, "material");
        GameRegistry.registerItem(ingot_01,"ingot_01");
        GameRegistry.registerItem(wire,"wire");
        GameRegistry.registerItem(statesChecker,"statesCheckerAP");
        //Blockの登録
        GameRegistry.registerBlock(crystalOreBlock_1, MetaItemBlock.class, "crystalOreBlock_01");
        GameRegistry.registerBlock(getBedrockCrystalOreBlock_1, MetaItemBlock.class, "bedrockCrystalOreBlock_01");
        GameRegistry.registerBlock(oreBlock_1, MetaItemBlock.class, "oreBlock_01");
        GameRegistry.registerBlock(bedrockOreBlock_1, MetaItemBlock.class, "bedrockOreBlock_01");
        GameRegistry.registerBlock(furnaceGenerator,"furnaceGeneratorAP");
        GameRegistry.registerBlock(cable, CableItemBlock.class,"Cable");
        for(Block block : alloySmelter){
            GameRegistry.registerBlock(block,block.getUnlocalizedName());
        }
        //Renderの登録
        proxy.registerRenderThings();
    }
    //initで行う登録処理
    static void registerInit(FMLInitializationEvent event){
        //Recipe削除
        RecipeRemover.removeRecipe(Items.stick);
        //レシピの登録
        GameRegistry.addRecipe(new ItemStack(AntiMatterModRegistry.wire,1,0),"S","S",'S',new ItemStack(AntiMatterModRegistry.ingot_01,1,3));
        //TileEntityの登録
        GameRegistry.registerTileEntity(TileEntityFurnaceGenerator.class,"tileFurnaceGeneratorAP");
        GameRegistry.registerTileEntity(TileEntityCable.class,"tileCableAP");
        GameRegistry.registerTileEntity(TileAlloySmelter.class,"tileAlloySmelter");
        //WorldGeneratorの登録
        GameRegistry.registerWorldGenerator(new OreGenerator(),2);
        NetworkRegistry.INSTANCE.registerGuiHandler(AntiMatterModCore.INSTANCE,new AMMGuiHandler());
        
    }
    //postinitで行う処理
    static void registerPostInit(FMLPostInitializationEvent event){
        //他mod関連の操作
    }
}
