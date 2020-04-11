package simulare2

import simulare2.constants.SAMPLE_SIZE
import simulare2.interfaces.Generator
import simulare2.interfaces.Plotable
import kotlin.math.nextDown
import kotlin.math.nextUp
import kotlin.random.Random

/**
 * @author Alexandru Balan -- last modified : 2020/04/11
 * @since 2020.alpha.2
 *
 *
 */
class ExponentialVariable (private var lambda : Double) : Generator, Plotable() {

    var name : String = "Variabila Exponentiala"
    private lateinit var expVariableStandard : ExponentialVariable
    private var sampleValues : MutableList<Double> = MutableList(0) {0.0}

    private fun generateStandardExponential() {
        expVariableStandard = ExponentialVariable(1.0)

        var n = 0

        while (expVariableStandard.sampleValues.size != SAMPLE_SIZE) {
            // generam u0, u1
            var u0 = Random.nextDouble(0.0.nextUp(), 1.0.nextDown())
            var u1 = Random.nextDouble(0.0.nextUp(), 1.0.nextDown())

            // u2 = u0; k = 1
            val u2 = u0
            var k = 1

            while (u0 >= u1) {
                k += 1
                u0 = u1
                u1 = Random.nextDouble(0.0.nextUp(), 1.0.nextDown())
            }

            if (k % 2 == 1) {
                expVariableStandard.sampleValues.add(n + u2)
                n = 0
            } else {
                n += 1
            }
        }
    }

    override fun generate(size: Int) {
        generateStandardExponential()
        sampleValues = expVariableStandard.sampleValues.map { it / lambda } as MutableList<Double>
    }

    fun plot() = super.plot(sampleValues, name)

}