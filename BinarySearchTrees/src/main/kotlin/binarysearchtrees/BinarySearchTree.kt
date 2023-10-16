package binarysearchtrees

import binarysearchtrees.splaytree.SplayVertex

interface BinarySearchTree<K : Comparable<K>, V> {
    val size: Int

    fun isEmpty(): Boolean

    fun clear()
    fun find(key: K): V?

    fun insert(key: K, value: V)

    fun remove(key: K): V?

    fun getRoot(): SplayVertex<K, V>?

    operator fun iterator(): Iterator<SplayVertex<K, V>>
}