package simulare2

import simulare2.constants.SAMPLE_SIZE
import simulare2.interfaces.Generator
import simulare2.interfaces.Plotable
import kotlin.math.*
import kotlin.random.Random

/**
 * @author Alexandru Balan -- last modified : 2020/04/11
 * @since 2020.alpha.1
 *
 * Utilizati [GammaVariable.newInstance] pentru a crea o instanta a clasei deoarece comportamentul in cazul in care niu < 1
 * nu este implementat, fiind necesari alti algoritmi.
 *
 * @param alpha : [Double]
 * @param lambda : [Double]
 * @param niu : [Double]
 */
class GammaVariable private constructor(
    private val alpha : Double, 
    private val lambda : Double, 
    private val niu : Double) : Generator, Plotable() {

    // Alte atribute ale clasei
    private val b : Double = niu - 1
    private val c : Double = niu + b
    private val s : Double = sqrt(2*niu - 1)
    private lateinit var gammaStandard : GammaVariable
    private var sampleValues : MutableList<Double> = MutableList(0) {0.0}
    var name : String = "Variabila Gama"

    // Constructorul clasei
    init {
        if (alpha != 0.0 && lambda != 1.0) {
            // Atunci aceasta variabila gama nu este standard si o generam noi
            gammaStandard = newInstance(0.0, 1.0, niu)!!.apply { name = "Variabila Gama Standard" }
            generateStandardGamma()
        }
    }

    private fun generateStandardGamma() {
        while (gammaStandard.sampleValues.size != SAMPLE_SIZE) {

            val u = Random.nextDouble(0.0.nextUp(), 1.0.nextDown())
            val t = gammaStandard.s * tan(PI * (u - 0.5))
            val y = gammaStandard.b + t

            if (y > 0) {
                val u1 = Random.nextDouble(0.0.nextUp(), 1.0.nextDown())

                if (u1 <= exp((gammaStandard.b * log10(y / gammaStandard.b)) - t + log10(1 + (t.pow(2) / gammaStandard.c)))) {
                    gammaStandard.sampleValues.add(y)
                }
            }
        }
    }

    override fun generate(size: Int) {
        sampleValues = gammaStandard.sampleValues.map { it / lambda }.map { it + alpha } as MutableList<Double>
    }

    fun plot() {
        super.plot(sampleValues, name)
    }

    companion object {
        fun newInstance (alpha: Double, lambda: Double, niu: Double) : GammaVariable? {
            return if (niu < 1) {
                println("This is not implemented yet!")
                null
            } else GammaVariable(alpha,lambda,niu)
        }
    }
}