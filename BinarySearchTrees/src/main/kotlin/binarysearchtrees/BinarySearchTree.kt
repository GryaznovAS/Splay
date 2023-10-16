package binarysearchtrees

import binarysearchtrees.splaytree.SplayVertex

interface BinarySearchTree<K : Comparable<K>, V> {
    fun find(key: K): V?

    fun insert(key: K, value: V)

    fun remove(key: K): V?

    fun getRoot(): SplayVertex<K, V>?
}