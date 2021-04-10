package ru.itmo.mse.simpleton.curves

import kscience.plotly.Plot
import kscience.plotly.models.Scatter
import kscience.plotly.scatter

fun Plot.curves(block: CurvesBuilder.() -> Unit): List<Scatter> {
    val curves = CurvesBuilder().apply(block).build()
    return curves.curves.map {
        val points = it.evalPoints()
        scatter {
            x.set(points.xs)
            y.set(points.ys)
        }
    }
}
