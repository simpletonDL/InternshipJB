package ru.itmo.mse.simpleton.curves

interface ParametricEquation {
    fun eval(x: Double): Double
}

class Const(val value: Double) : ParametricEquation {
    override fun eval(x: Double) = value
}

object Variable: ParametricEquation {
    override fun eval(x: Double): Double {
        return x
    }
}

abstract class BinOp(val left: ParametricEquation, val right: ParametricEquation): ParametricEquation {
    abstract fun evalOp(l: Double, r: Double): Double

    override fun eval(x: Double): Double =
        evalOp(left.eval(x), right.eval(x))
}

class PlusOp(left: ParametricEquation, right: ParametricEquation): BinOp(left, right) {
    override fun evalOp(l: Double, r: Double) = l + r
}

class MulOp(left: ParametricEquation, right: ParametricEquation): BinOp(left, right) {
    override fun evalOp(l: Double, r: Double) = l * r
}

class MinusOp(left: ParametricEquation, right: ParametricEquation): BinOp(left, right) {
    override fun evalOp(l: Double, r: Double) = l - r
}

operator fun Double.plus(x: ParametricEquation): ParametricEquation =
    PlusOp(Const(this), x)

operator fun ParametricEquation.plus(x: Double): ParametricEquation =
    PlusOp(this, Const(x))

operator fun ParametricEquation.plus(x: ParametricEquation): ParametricEquation =
    PlusOp(this, x)

operator fun Double.times(x: ParametricEquation): ParametricEquation =
    MulOp(Const(this), x)

operator fun ParametricEquation.times(x: Double): ParametricEquation =
    MulOp(this, Const(x))

operator fun ParametricEquation.times(x: ParametricEquation): ParametricEquation =
    MulOp(this, x)

operator fun Double.minus(x: ParametricEquation): ParametricEquation =
    MinusOp(Const(this), x)

operator fun ParametricEquation.minus(x: Double): ParametricEquation =
    MinusOp(this, Const(x))

operator fun ParametricEquation.minus(x: ParametricEquation): ParametricEquation =
    MinusOp(this, x)
