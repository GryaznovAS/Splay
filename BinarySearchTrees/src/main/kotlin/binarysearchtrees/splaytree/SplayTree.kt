package binarysearchtrees.splaytree

import binarysearchtrees.BinarySearchTree

class SplayTree<K : Comparable<K>, V> : BinarySearchTree<K, V> {
    private var root: SplayVertex<K, V>? = null
    final override var size: Int = 0
        protected set
    protected var modCount: Int = 0

    override fun isEmpty(): Boolean = (root == null)

    override fun clear() {
        size = 0
        root = null
        ++modCount
    }

    override fun getRoot(): SplayVertex<K, V>? = root

    override fun iterator(): Iterator<SplayVertex<K, V>> {
        return SplayTreeIterator(getRoot()) { modCount }
    }

    private fun rotateLeft(vertex: SplayVertex<K, V>): SplayVertex<K, V> {
        val parent = vertex.parent
        val child = vertex.right
            ?: throw IllegalArgumentException("Node to rotateLeft must have a right child")

        vertex.right = child.left
        vertex.right?.parent = vertex

        child.left = vertex
        child.left?.parent = child

        child.parent = parent

        if (parent != null) {
            if (parent.left == vertex) parent.left = child
            else parent.right = child
        }

        return child
    }

    private fun rotateRight(vertex: SplayVertex<K, V>): SplayVertex<K, V> {
        val parent = vertex.parent
        val child = vertex.left
            ?: throw IllegalArgumentException("Node to rotateRight must have a left child")

        vertex.left = child.right
        vertex.left?.parent = vertex

        child.right = vertex
        child.right?.parent = child

        child.parent = parent

        if (parent != null) {
            if (parent.left == vertex) parent.left = child
            else parent.right = child
        }

        return child
    }

    private fun splay(vertex: SplayVertex<K, V>): SplayVertex<K, V> {
        var currentVertex = vertex

        while (currentVertex.parent != null) {
            val parent = currentVertex.parent
                ?: throw IllegalStateException("Parent can not be null inside the while loop")
            val grandParent = parent.parent
            if (grandParent != null) {
                if (parent.left == currentVertex) {
                    currentVertex = if (grandParent.left == parent) {
                        rotateRight(rotateRight(grandParent))
                    } else {
                        rotateRight(parent)
                        rotateLeft(grandParent)
                    }
                } else {
                    currentVertex = if (grandParent.right == parent) {
                        rotateLeft(rotateLeft(grandParent))
                    } else {
                        rotateLeft(parent)
                        rotateRight(grandParent)
                    }
                }
            } else {
                currentVertex = if (parent.left == currentVertex) {
                    rotateRight(parent)
                } else {
                    rotateLeft(parent)
                }
            }
        }
        return currentVertex
    }

    override fun find(key: K): V? {
        var currentVertex = root
        while (currentVertex != null && currentVertex.key != key) {
            currentVertex = if (currentVertex.key > key) {
                currentVertex.left
            } else {
                currentVertex.right
            }
        }
        val result = currentVertex?.value
        if (currentVertex != null) {
            root = splay(currentVertex)
        }
        return result
    }

    override fun insert(key: K, value: V) {
        if (root == null) {
            root = SplayVertex(key, value)
            return
        }

        var currentVertex = root
        var parent: SplayVertex<K, V>? = null
        var isLastChildLeft = false
        while (currentVertex != null && currentVertex.key != key) {
            parent = currentVertex
            currentVertex = if (currentVertex.key > key) {
                isLastChildLeft = true
                currentVertex.left
            } else {
                isLastChildLeft = false
                currentVertex.right
            }
        }
        if (currentVertex != null) {
            currentVertex.value = value
            ++size
            ++modCount
        } else {
            val newVertex = SplayVertex(key, value)
            newVertex.parent = parent
            if (isLastChildLeft) parent?.left = newVertex
            else parent?.right = newVertex
            currentVertex = newVertex
        }
        root = splay(currentVertex)
    }

    private fun merge(leftTree: SplayVertex<K, V>?, rightTree: SplayVertex<K, V>?): SplayVertex<K, V>? {
        if (leftTree == null) return rightTree
        if (rightTree == null) return leftTree

        var maxVertex = leftTree
        while (maxVertex?.right != null) {
            maxVertex = maxVertex.right
        }

        maxVertex ?: throw IllegalStateException("Merge: max in subtree can not be null")
        val newRoot = splay(maxVertex)
        newRoot.right = rightTree
        rightTree.parent = newRoot
        return newRoot
    }

    override fun remove(key: K): V? {
        val result = find(key)
        if (result != null) {
            root?.left?.parent = null
            root?.right?.parent = null
            root = merge(root?.left, root?.right)
            --size
            ++modCount
        }
        return result
    }

    protected class SplayTreeIterator<K : Comparable<K>, V>(
        root: SplayVertex<K, V>?,
        private val getModCount: () -> Int
    ) : Iterator<SplayVertex<K, V>> {
        private val stack: MutableList<SplayVertex<K, V>> = mutableListOf()
        private val expectedModCount: Int = getModCount()

        init {
            var vertex = root
            while (vertex != null) {
                stack.add(vertex)
                vertex = vertex.left
            }
        }

        override fun hasNext(): Boolean {
            if (expectedModCount != getModCount()) {
                throw ConcurrentModificationException()
            } else {
                return stack.isNotEmpty()
            }
        }

        override fun next(): SplayVertex<K, V> {
            if (expectedModCount != getModCount()) {
                throw ConcurrentModificationException()
            } else {
                val vertex = stack.removeLast()
                var nextVertex = vertex.right
                while (nextVertex != null) {
                    stack.add(nextVertex)
                    nextVertex = nextVertex.left
                }
                return vertex
            }
        }
    }

}