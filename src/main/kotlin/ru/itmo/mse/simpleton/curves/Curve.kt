package ru.itmo.mse.simpleton.curves

class CurveDefinitionException(msg: String) : Exception(msg)

class Points(val xs: List<Double>, val ys: List<Double>)

class Curve(val x: ParametricEquation, val y: ParametricEquation, val range: ClosedFloatingPointRange<Double>) {
    fun evalPoints(step: Double = 0.1): Points {
        val xs = (range step step).map { x.eval(it) }
        val ys = (range step step).map { y.eval(it) }
        return Points(xs, ys)
    }
}

class CurveBuilder(var x: ParametricEquation? = null,
                   var y: ParametricEquation? = null,
                   var range: ClosedFloatingPointRange<Double>? = null)
{
    val t = Variable

    fun build(): Curve {
        val x = x ?: throw CurveDefinitionException("Parameter x should be specified.")
        val y = y ?: throw CurveDefinitionException("Parameter y should be specified.")
        val range = range ?: throw CurveDefinitionException("Parameter range should be specified.")
        return Curve(x, y, range)
    }
}

fun curve(init: CurveBuilder.() -> Unit): Curve =
    CurveBuilder().apply(init).build()
