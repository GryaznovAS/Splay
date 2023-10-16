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

    @ParameterizedTest
    @ValueSource(ints = [9, 20, 32, 81, 77, 94, -10, -15])
    fun `Function remove deletes the some element correctly`(key: Int) {
        values.forEach { tree.insert(it.first, it.second) }

        var value = key * 198
        tree.insert(key, value)
        var size = tree.size - 1
        assertEquals(value, tree.remove(key))
        assertEquals(null, tree.remove(key))
        assertEquals(null, tree.find(key))
        assertEquals(size, tree.size)
        assertTrue(isSplayTree(tree))

        value = key * 95
        tree.insert(key, value)
        size = tree.size - 1
        assertFalse(tree.remove(key, value + 10))
        assertTrue(tree.remove(key, value))
        assertFalse(tree.remove(key, value))
        assertEquals(null, tree.find(key))
        assertEquals(size, tree.size)
        assertTrue(isSplayTree(tree))
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

    @Test
    fun `Function remove deletes the root element correctly`() {
        values.forEach { tree.insert(it.first, it.second) }

        val value = 45
        var oldKey = tree.getRoot()?.let {
            it.value
            it.key
        } ?: -25
        var size = tree.size - 1
        assertEquals(value, tree.remove(oldKey))
        assertNotEquals(oldKey, tree.getRoot()?.key)
        assertEquals(size, tree.size)
        assertTrue(isSplayTree(tree))

        oldKey = tree.getRoot()?.let {
            it.value
            it.key
        } ?: -25
        size = tree.size - 1
        assertTrue(tree.remove(oldKey, value))
        assertNotEquals(oldKey, tree.getRoot()?.key)
        assertEquals(size, tree.size)
        assertTrue(isSplayTree(tree))
    }

}