package antimattaermod.core.Energy.Generator.TileEntity

import antimattaermod.core.Energy.APVoltage
import antimattaermod.core.Energy.IAPGenerator
import net.minecraft.tileentity.TileEntity

/**
 * @author C6H2Cl2
 */
class TileEntityFurnaceGenerator : TileEntity(),IAPGenerator{


    //定数
    private val voltage : APVoltage = APVoltage.HV
    private val energyStorage : Int = voltage.maxEnergy * 20 * 600
    private val maxFuel : Float = 2048f
    //変数
    private var storedEnergy : Int = 0
    private var fuel : Float = 0f
    init {

    }

    public fun addFuel(amount: Float) :Float{
        val value :Float= amount / 1600
        fuel += value
        if (fuel > maxFuel){
            val over = fuel - maxFuel
            fuel = maxFuel
            return over
        }else{
            return 0f
        }
    }



    //Interfaceの実装
    override fun isFuelMax() : Boolean{
        return fuel == maxFuel
    }

    override fun getReceiveVoltage(): APVoltage {
        return APVoltage.ZeroVoltage
    }

    override fun getSendVoltage(): APVoltage {
        return voltage
    }

    override fun canReceiveEnergy(): Boolean {
        return false
    }

    override fun canSendEnergy(): Boolean {
        return true
    }

    override fun getMaxStoreEnergy(): Int {
        return energyStorage
    }

    override fun getCurrentGenerate(): Int {
        return 0
    }

    override fun getFuelValue(): Float {
        return fuel
    }

    override fun getMaxFuelValue(): Float {
        return maxFuel
    }
}
