package binarysearchtrees.splaytree

class SplayVertex<K : Comparable<K>, V>(val key: K, var value: V) {
    var left: SplayVertex<K, V>? = null
    var right: SplayVertex<K, V>? = null
    var parent: SplayVertex<K, V>? = null
}