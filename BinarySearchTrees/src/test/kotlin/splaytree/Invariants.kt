package splaytree

import binarysearchtrees.splaytree.SplayTree
import binarysearchtrees.splaytree.SplayVertex

fun <K : Comparable<K>, V> isSplayTree(tree: SplayTree<K, V>): Boolean {
    fun checkSplayInvariant(vertex: SplayVertex<K, V>): Boolean {
        return vertex.left?.let { vertex.key > it.key && checkSplayInvariant(it) } ?: true
                && vertex.right?.let { vertex.key < it.key && checkSplayInvariant(it) } ?: true
    }
    return tree.getRoot()?.let { checkSplayInvariant(it) } ?: true
}