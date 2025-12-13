package cyou.umbra

import java.io.File
import kotlin.math.sqrt

class Day08 {

    data class Point(val x: Int, val y: Int, val z: Int) {
        fun distance(other: Point): Double {
            val dx = (x - other.x).toDouble()
            val dy = (y - other.y).toDouble()
            val dz = (z - other.z).toDouble()
            return sqrt(dx * dx + dy * dy + dz * dz)
        }
    }

    data class PointDistance(val a: Point, val b: Point, val distance: Double) {}

    fun part1(file: String, boxesToConnect: Int): Int {
        val boxes = parseFile(file)
        val pds = getMinDistances(boxes)

        val initialCircuit = listOf(setOf(pds.first().a, pds.first().b))
        return pds.asSequence()
            .take(boxesToConnect)
            .drop(1)
            .fold(initialCircuit) { circuits, pd ->
                circuits.updateCircuits(pd)
            }
            .sortedBy { -it.size }
            .take(3)
            .fold(1) { acc, points -> acc * points.size }
    }

    fun part2(file: String): Int {
        val boxes = parseFile(file)
        val pds = getMinDistances(boxes)

        val initialCircuit = listOf(setOf(pds.first().a, pds.first().b))
        val (_, finalPd) = pds.asSequence()
            .runningFold(initialCircuit) { circuits, pd ->
                circuits.updateCircuits(pd)
            }
            .drop(1)
            .zip(pds.asSequence())
            .first { (circuits, _) -> circuits.first().size == boxes.size }

        return finalPd.a.x * finalPd.b.x
    }

    fun List<Set<Point>>.updateCircuits(pd: PointDistance): List<Set<Point>> {
        val a = pd.a
        val b = pd.b

        val aIdx = this.indexOfFirst { it.contains(a) }
        val bIdx = this.indexOfFirst { it.contains(b) }

        val updatedCircuit = this.map { it.toMutableSet() }.toMutableList()

        when {
            // Neither are in a circuit -> create a new circuit
            (aIdx == -1 && bIdx == -1) -> {
                val newCircuit = mutableSetOf(a, b)
                updatedCircuit.add(newCircuit)
            }

            // a is in a circuit and b is not -> add b to a's circuit
            (aIdx != -1 && bIdx == -1) -> {
                updatedCircuit[aIdx] += b
            }

            // b is in a circuit and a is not -> add a to b's circuit
            (aIdx == -1) -> {
                updatedCircuit[bIdx] += a
            }

            // both a and b are in a circuit, merge circuits
            (aIdx != bIdx) -> {
                updatedCircuit[aIdx].addAll(this[bIdx])
                updatedCircuit.removeAt(bIdx)
            }

            // a and b are in the same circuit -> do nothing

        }

        return updatedCircuit
    }

    fun getMinDistances(boxes: List<Point>): List<PointDistance> {
        return boxes
            .asSequence()
            .flatMapIndexed { i, a ->
                ((i + 1) until boxes.size).asSequence()
                    .map { j ->
                        val b = boxes[j]
                        PointDistance(a, b, a.distance(b))
                    }
            }
            .sortedBy { it.distance }
            .toList()
    }

    fun parseFile(file: String): List<Point> {
        val fp = "./src/main/resources/day08/$file"
        return File(fp).useLines { lines ->
            lines.map { line ->
                val (x, y, z) = line.split(",").map { it.toInt() }
                Point(x, y, z)
            }.toList()
        }
    }

}