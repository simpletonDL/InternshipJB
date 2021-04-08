package ru.itmo.mse.simpleton.curves

import kscience.plotly.Plot
import kscience.plotly.models.Scatter
import kscience.plotly.scatter

fun Plot.curve(block: CurveBuilder.() -> Unit): Scatter {
    val curve = CurveBuilder().apply(block).build()
    val points = curve.evalPoints()

    return scatter {
        x.set(points.xs)
        y.set(points.ys)
    }
}
