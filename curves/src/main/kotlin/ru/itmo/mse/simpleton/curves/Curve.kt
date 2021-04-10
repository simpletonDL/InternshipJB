package ru.itmo.mse.simpleton.curves

class CurveDefinitionException(msg: String) : Exception(msg)

@DslMarker
annotation class CurvesMarker

class Points(val xs: List<Double>, val ys: List<Double>)

class Curves (val curves: MutableList<Curve>)

class Curve(val x: ParametricEquation, val y: ParametricEquation, val range: ClosedFloatingPointRange<Double>) {
    fun evalPoints(step: Double = 0.1): Points {
        val xs = (range step step).map { x.eval(it) }
        val ys = (range step step).map { y.eval(it) }
        return Points(xs, ys)
    }
}

@CurvesMarker
class CurvesBuilder {
    private val curves: MutableList<Curve> = mutableListOf()

    internal fun build() =
        Curves(curves)

    fun curve(init: CurveBuilder.() -> Unit) {
        curves.add(CurveBuilder().apply(init).build())
    }
}

@CurvesMarker
class CurveBuilder(var x: ParametricEquation? = null,
                   var y: ParametricEquation? = null,
                   var range: ClosedFloatingPointRange<Double>? = null)
{
    val t = Variable

    internal fun build(): Curve {
        val x = x ?: throw CurveDefinitionException("Parameter x should be specified.")
        val y = y ?: throw CurveDefinitionException("Parameter y should be specified.")
        val range = range ?: throw CurveDefinitionException("Parameter range should be specified.")
        return Curve(x, y, range)
    }
}

fun curves(init: CurvesBuilder.() -> Unit): Curves =
    CurvesBuilder().apply(init).build()
