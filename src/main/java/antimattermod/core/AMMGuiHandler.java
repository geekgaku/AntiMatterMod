package antimattermod.core;

import antimattermod.core.Energy.GUI.AlloySmelterContainer;
import antimattermod.core.Energy.GUI.AlloySmelterGuiContainer;
import antimattermod.core.Energy.TileEntity.Machine.TileAlloySmelter;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * @author C6H2Cl2
 */
public class AMMGuiHandler implements IGuiHandler {
    public static final int GuiID_AlloySmelter = 334;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GuiID_AlloySmelter: return new AlloySmelterContainer(x, y, z,(TileAlloySmelter)(world.getTileEntity(x,y,z)),player.inventory);
            default:return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID){
            case GuiID_AlloySmelter: return new AlloySmelterGuiContainer(x, y, z,(TileAlloySmelter)(world.getTileEntity(x,y,z)),player.inventory);
            default:return null;
        }
    }
}
