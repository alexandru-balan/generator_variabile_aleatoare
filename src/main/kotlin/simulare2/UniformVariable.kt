package simulare2

import simulare2.interfaces.Generator
import simulare2.interfaces.Plotable
import kotlin.math.nextDown
import kotlin.math.nextUp
import kotlin.random.Random


/**
 * @author Alexandru Balan -- last modified : 2020/04/11
 * @since 2020.alpha.1
 *
 * @param low : [Double] Reprezinta mariginea inferioara a intervalului de distributie
 * @param high : [Double] Reprezinta mariginea superioara a intervalului de distributie
 */
class UniformVariable (low: Double, high : Double) : Generator, Plotable() {
    var sampleValues : List<Double> = emptyList()
    var name : String = "Variabila uniforma"
    private val high : Double = high.nextDown()
    private val low : Double = low.nextUp()

    override fun generate(size: Int){
        sampleValues = List(size) { Random.nextDouble(low,high) }
    }

    fun plot() {
        super.plot(sampleValues, name)
    }
}