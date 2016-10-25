package antimattermod.core.Energy

import antimattermod.core.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.WorldSavedData
import java.util.*

/**
 * @author C6H2Cl2
 */
class EnergyNetworkGlobal : WorldSavedData(DATA_NAME) {

    private val energyNetworkList = LinkedList<EnergyNetwork>()

    override fun writeToNBT(nbtTagCompound: NBTTagCompound) {
        val tagCompound = NBTTagCompound()
        val tagList = NBTTagList()
        var tag = NBTTagCompound()
        for (network in energyNetworkList){
            network.writeToNBT(tag)
            tagList.appendTag(tag)
            tag = NBTTagCompound()
        }
        tagCompound.setTag("energyNetworkList",tagList)
        tagCompound.setInteger("networkNum",tagList.tagCount())
        nbtTagCompound.setTag(DATA_NAME,tagCompound)
    }

    override fun readFromNBT(nbtTagCompound: NBTTagCompound) {
        val tagCompound = nbtTagCompound.getCompoundTag(DATA_NAME)
        val tagList = nbtTagCompound.getTagList("energyNetworkList",tagCompound.getInteger("networkNum"))
        for (i in 0..tagList.tagCount()-1){
            val network = EnergyNetwork()
            network.readFromNBT(tagList.getCompoundTagAt(i))
            energyNetworkList.add(network)
        }
    }

    companion object{
        val DATA_NAME = "AMMGlobalEnergyNetwork"
    }

    public fun getEnergyNetwork(blockPos: BlockPos):EnergyNetwork?{
        var energyNetwork :EnergyNetwork? = null
        for (network in energyNetworkList){
            if(network.isContains(blockPos)){
                energyNetwork = network
                break
            }
        }
        return energyNetwork
    }

    public fun getEnergyNetwork(tileEntity: TileEntity):EnergyNetwork?{
        return getEnergyNetwork(BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord))
    }

    public fun registerTileEntity(tileEntity: TileEntity):Unit{
        val tilePos = BlockPos(tileEntity.xCoord,tileEntity.yCoord,tileEntity.zCoord)


        var network :EnergyNetwork? = getEnergyNetwork(tileEntity)
        if(network == null){
            network = EnergyNetwork()
            energyNetworkList.add(network)
        }

    }

}