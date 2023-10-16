package binarysearchtrees

import binarysearchtrees.splaytree.SplayTree

// initializing functions

fun <K : Comparable<K>, V> binarySearchTreeOf(): BinarySearchTree<K, V> {
    return SplayTree<K, V>()
}

fun <K : Comparable<K>, V> binarySearchTreeOf(
    vararg args: Pair<K, V>
): BinarySearchTree<K, V> {
    val tree = SplayTree<K, V>()
    for (it in args) {
        tree.insert(it.first, it.second)
    }
    return tree
}