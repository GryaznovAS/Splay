package app.repository

import app.view.Position
import app.view.ScrollDelta
import binarysearchtrees.BinarySearchTree
import binarysearchtrees.splaytree.SplayTree

class Repository() {
    private lateinit var splayRepo: SplayRepo<Position>

    init {
        try {
            val dirPath = "./BSTRepo"
            splayRepo = SplayRepo(
                dirPath,
                { serializePosition(it) },
                { deserializePosition(it) }
            )
        } catch (e: Exception) {
            throw Exception("SplayRepo Init Exception: " + e.message)
        }
    }

    fun getNames(tree: SplayTree<String, Position>): List<String> {
        return try {
            splayRepo.getNames()
        } catch (e: Exception) {
            throw Exception("RBTRepo GetNames Exception: " + e.message)
        }
    }

    fun save(name: String, tree: SplayTree<String, Position>, scrollDelta: ScrollDelta) {
        val settingsData = serializeScrollDelta(scrollDelta)

        try {
            splayRepo.set(name, tree, settingsData)
        } catch (e: Exception) {
            throw Exception("SplayRepo Save Exception: " + e.message)
        }
    }

    fun get(name: String, tree: SplayTree<String, Position>): Pair<BinarySearchTree<String, Position>, ScrollDelta> {
        val (tree: SplayTree<String, Position>, settingsData: String) =
        try {
            splayRepo.get(name)
        } catch (e: Exception) {
            throw Exception("SplayRepo Get Exception: " + e.message)
        }
        return Pair(tree, deserializeScrollDelta(settingsData))
    }

    fun delete(name: String, tree: SplayTree<String, Position>): Boolean {

        return try {
            splayRepo.remove(name)
        } catch (e: Exception) {
            throw Exception("SplayRepo Delete Exception: " + e.message)
        }

    }

}