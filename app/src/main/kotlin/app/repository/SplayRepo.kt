package app.repository

import binarysearchtrees.splaytree.SplayTree
import binarysearchtrees.splaytree.SplayVertex
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

class SplayRepo<ValueType>(
    private val dirPath: String,
    private val serializeValue: (ValueType) -> String,
    private val deserializeValue: (String) -> ValueType
) {
    init {
        File(dirPath).mkdirs()
    }

    fun getNames(): List<String> {
        return File(dirPath).listFiles { it ->
            it.name.endsWith(".json")
        }?.map { it.name.dropLast(5) } ?: listOf()
    }

    fun get(name: String): Pair<SplayTree<String, ValueType>, String> {
        val jsonTree = Json.decodeFromString<JsonTree>(File(dirPath, "$name.json").readText())
        return Splay<ValueType>().apply {
            jsonTree.root?.let { buildTree(it, deserializeValue) }
        } to jsonTree.settingsData
    }

    fun set(name: String, tree: SplayTree<String, ValueType>, settingsData: String) {
        val file = File(dirPath, "$name.json")
        file.createNewFile()
        file.writeText(Json.encodeToString(JsonTree(settingsData, tree.getRoot()?.toJsonNode(serializeValue))))
    }

    fun remove(name: String): Boolean = File(dirPath, "$name.json").delete()

    private fun SplayVertex<String, ValueType>.toJsonNode(serializeValue: (ValueType) -> String): JsonVertex {
        return JsonVertex(
            key,
            serializeValue(value),
            left?.toJsonNode(serializeValue),
            right?.toJsonNode(serializeValue)
        )
    }
}

@Serializable
data class JsonTree(
    val settingsData: String,
    val root: JsonVertex?
)

@Serializable
data class JsonVertex(
    val key: String,
    val value: String,
    val left: JsonVertex?,
    val right: JsonVertex?,
)

private class Splay<ValueType> : SplayTree<String, ValueType>() {
    fun buildTree(jsonVertex: JsonVertex, deserializeValue: (String) -> ValueType) {
        root = jsonVertex.toVertex(deserializeValue)
    }

    private fun JsonVertex.toVertex(deserializeValue: (String) -> ValueType): SplayVertex<String, ValueType> {
        val vertex = SplayVertex(key, deserializeValue(value))
        ++size
        ++modCount
        vertex.left = left?.toVertex(deserializeValue)
        vertex.right = right?.toVertex(deserializeValue)
        return vertex
    }
}
