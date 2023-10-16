package splaytree

import binarysearchtrees.splaytree.SplayTree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.random.Random

class SplayTreeTests {

    private val randomizer = Random(100)
    private val elementsCount = 1000
    private val values = Array(elementsCount) { Pair(randomizer.nextInt(), randomizer.nextInt()) }
    private lateinit var tree: SplayTree<Int, Int>

    @BeforeEach
    fun init() {
        tree = SplayTree()
    }

    @Test
    fun `Function remove deletes the existing element correctly`() {
        values.forEach { tree.insert(it.first, it.second) }

        val elements = mutableListOf<Pair<Int, Int>>()
        values.reversed().distinctBy { it.first }.forEach { elements.add(it) }
        elements.shuffle()
        for (i in 0 until elements.size step 20) {
            val key = elements[i].first
            val value = elements[i].second
            val size = tree.size - 1
            assertEquals(value, tree.remove(key))
            assertEquals(null, tree.find(key))
            assertEquals(size, tree.size)
            assertTrue(isSplayTree(tree))
        }
    }

}