package antimattermod.core.Energy

import antimattermod.core.Util.BlockPos
import net.minecraft.nbt.NBTTagCompound

/**
 * @author C6H2Cl2
 */
class EnergyNode {
    private var voltage: APVoltage = APVoltage.ZeroVoltage
    private var energyValue : Int = 0
    private var source : BlockPos = BlockPos(0,0,0)
    private var target : BlockPos = BlockPos(0,0,0)

    constructor(voltage : APVoltage, value : Int, sourcePos : BlockPos, targetPos: BlockPos){
        this.voltage = voltage
        this.energyValue = value
        this.source = sourcePos
        this.target = targetPos
    }

    constructor(){

    }

    fun writeToNBT(tagCompound : NBTTagCompound){
        writeToNBT(tagCompound,"EnergyNode")
    }

    fun readFromNBT(tagCompound: NBTTagCompound){
        readFromNBT(tagCompound,"EnergyNode")
    }

    fun writeToNBT(tagCompound: NBTTagCompound, tagName: String){
        val tag = NBTTagCompound()
        voltage.writeToNBT(tag)
        tag.setInteger("energyValue",energyValue)
        source.writeToNBT(tag,"sourcePos")
        target.writeToNBT(tag,"targetPos")
        tagCompound.setTag(tagName,tag)
    }

    fun readFromNBT(tagCompound: NBTTagCompound, tagName: String){
        val tag = tagCompound.getCompoundTag(tagName)
        voltage.readFromNBT(tag)
        energyValue = tag.getInteger("energyValue")
        source.readFromNBT(tag)
        target.readFromNBT(tag)
    }

    fun getVoltage() : APVoltage = voltage
    fun getEnergyValue() : Int = energyValue
    fun getSource() : BlockPos = source
    fun getTargetPos() : BlockPos = target
}