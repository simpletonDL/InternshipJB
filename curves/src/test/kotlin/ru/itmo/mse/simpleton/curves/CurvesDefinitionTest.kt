package ru.itmo.mse.simpleton.curves

import org.junit.Assert
import org.junit.Test

class CurvesDefinitionTest {
    @Test
    fun test1() {
        val curves =
            curves {
                curve {
                    x = 1.0 + t * t
                    y = 2.0 - t
                    range = 1.0..2.0
                }
                curve {
                    x = t + 2.0
                    y = 2.0 - t
                    range = 5.0..7.0
                }
            }
        val xs1 = curves.curves[0].evalPoints(0.5).xs
        val ys1 = curves.curves[0].evalPoints(0.5).ys
        Assert.assertEquals(listOf(2.0, 3.25, 5.0), xs1)
        Assert.assertEquals(listOf(1.0, 0.5, 0.0), ys1)

        val xs2 = curves.curves[1].evalPoints(0.5).xs
        val ys2 = curves.curves[1].evalPoints(0.5).ys
        Assert.assertEquals(listOf(7.0, 7.5, 8.0, 8.5, 9.0), xs2)
        Assert.assertEquals(listOf(-3.0, -3.5, -4.0, -4.5, -5.0), ys2)
    }
}