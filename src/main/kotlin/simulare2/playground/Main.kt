package simulare2.playground

import simulare2.ExponentialVariable
import simulare2.GammaVariable
import simulare2.constants.SAMPLE_SIZE

fun main() {

    /*val uv = UniformVariable(0.0,1.0)
    uv.generate(GENERATED_NUMBERS)
    uv.plot()*/

    val gammaVariable : GammaVariable = GammaVariable.newInstance(2.0, 3.0, 1.7)!!
    gammaVariable.generate(SAMPLE_SIZE)
    gammaVariable.plot()

    val expVariable = ExponentialVariable(2.5)
    expVariable.generate(SAMPLE_SIZE)
    expVariable.plot()
}