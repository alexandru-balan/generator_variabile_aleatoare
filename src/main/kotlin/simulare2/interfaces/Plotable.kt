package simulare2.interfaces

import javafx.application.Platform
import jetbrains.datalore.base.geometry.DoubleVector
import jetbrains.datalore.plot.MonolithicAwt
import jetbrains.datalore.plot.builder.presentation.Style
import jetbrains.datalore.vis.svg.SvgSvgElement
import jetbrains.datalore.vis.swing.SceneMapperJfxPanel
import jetbrains.letsPlot.geom.geom_density
import jetbrains.letsPlot.ggplot
import jetbrains.letsPlot.ggtitle
import jetbrains.letsPlot.intern.toSpec
import javax.swing.JFrame
import javax.swing.SwingUtilities
import javax.swing.WindowConstants

/**
 * @author Alexandru Balan -- last modified : 2020/04/11
 * @since 2020.alpha.1
 *
 * Aceasta clasa este mostenita de toate clasele care pot genera un grafic. Aceasta clasa are o metoda care poate face grafice
 * cu o lista de valori ale unei variabile aleatoare si numele variabilei.
 * Daca o clasa vrea sa schimbe modul in care se genereaza graficul, poate sa suprascrie functia [Plotable.plot] prin cuvantul cheie
 * override
 */
abstract class Plotable {

    // Atribute pentru a construi grafice JFX
    val SVG_COMPONENT_FACTORY_JFX =
        { svg: SvgSvgElement -> SceneMapperJfxPanel(svg, listOf(Style.JFX_PLOT_STYLESHEET)) }
    val EXECUTOR_JFX = { r: () -> Unit ->
        if (Platform.isFxApplicationThread()) {
            r.invoke()
        } else {
            Platform.runLater(r)
        }
    }

    open fun plot(sampleValues : List<Double>, name : String) {
        SwingUtilities.invokeLater {
            val data = mapOf<String, Any>("x" to sampleValues, "legenda" to List(sampleValues.size) {name})

            val geom = geom_density(alpha = 0.3, size = 0.3, color = "green") {
                x = "x"
                fill = "legenda"
            }

            val p = ggplot(data) + geom + ggtitle("$name la ${sampleValues.size} de valori")
            val plotSpec = p.toSpec()
            val plotSize = DoubleVector(1200.00, 600.00)

            val component =
                    MonolithicAwt.buildPlotFromRawSpecs(plotSpec, plotSize, SVG_COMPONENT_FACTORY_JFX, EXECUTOR_JFX) {
                        for (message in it) {
                            println("PLOT MESSAGE: $message")
                        }
                    }

            val frame = JFrame(name)
            frame.contentPane.add(component)
            frame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            frame.pack()
            frame.isVisible = true
        }
    }
}