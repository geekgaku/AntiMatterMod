package antimattaermod.core.Block;

import antimattaermod.core.AntiMatterModCore;
import antimattaermod.core.AntiMatterModRegistry;
import antimattaermod.core.Block.TileEntity.TileEntityClayCrucible;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by Raiti on 2016/10/15.
 */
public class ClayCrucible extends BlockContainer {
	
	public ClayCrucible() {
		super(Material.rock);
		this.setBlockName("claycrucible");
		this.setBlockTextureName(AntiMatterModCore.MOD_ID+":another/claycrucible");
		this.setCreativeTab(AntiMatterModRegistry.tabMachines);
		this.setResistance(5F);
		this.setHardness(10F);
		this.setHarvestLevel("pickaxe",1);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityClayCrucible();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		//手持ちアイテムの判定
		ItemStack heldItem;
		if((heldItem = player.getHeldItem()) == null) return false;
		//タイルエンチチーのしゅとく
		TileEntityClayCrucible tile = (TileEntityClayCrucible)world.getTileEntity(x,y,z);
		int addSize = tile.addOres(player.getHeldItem());
		if(addSize != 0){
			heldItem.stackSize -= addSize;
			tile.markDirty();
			player.inventory.markDirty();
			world.markBlockForUpdate(x,y,z);
		}
		return addSize > 0;
	}
	
	@Override
	public int getRenderType() {
		return -1;
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		
	}
	
	@Override
	public String getItemIconName() {
		return AntiMatterModCore.MOD_ID+":another/claycrucible";
	}
	
	@Override
	public boolean isNormalCube() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	public class ClayCrucibleItem extends ItemBlock{
		
		public ClayCrucibleItem(Block p_i45328_1_) {
			super(p_i45328_1_);
		}
	}
}
