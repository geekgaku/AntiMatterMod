package antimattaermod.core.Energy

import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
abstract class IAPMachine :IAPReceiver(){
    init {

    }

    override fun writeToNBT(tagCompound: NBTTagCompound) {
        super.writeToNBT(tagCompound)
    }

    override fun readFromNBT(tagCompound: NBTTagCompound) {
        super.readFromNBT(tagCompound)
    }
}