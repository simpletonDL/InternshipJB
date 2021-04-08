import kscience.plotly.*
import ru.itmo.mse.simpleton.curves.*

fun main() {
    Plotly.page {
        plot {
            curve {
                range = 1.0..7.0
                x = t * 0.0
                y = t
            }
            curve {
                range = -1.0..1.0
                x = 3.0 * (1.0 - t * t)
                y = 4.0 + (t + 1.0) * 1.5
            }
            curve {
                range = -1.0..1.0
                x = 3.0 * (1.0 - t * t)
                y = 1.0 + (t + 1.0) * 1.5
            }
            layout {
                xaxis {
                    range = 0.0..4.0
                }
                yaxis {
                    range = 1.0..8.0
                }
            }
        }
    }.makeFile()
}