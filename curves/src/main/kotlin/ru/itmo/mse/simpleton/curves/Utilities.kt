package ru.itmo.mse.simpleton.curves

infix fun ClosedRange<Double>.step(step: Double): Iterable<Double> {
    val sequence = generateSequence(start) { previous ->
        val next = previous + step
        if (next > endInclusive) null else next
    }
    return sequence.asIterable()
}
