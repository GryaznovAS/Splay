package app

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import app.view.MainWindow
import app.view.Position
import app.view.defaultVertexSize
import app.view.setTreePositions
import binarysearchtrees.splaytree.SplayTree

fun main() {
    application {
        MaterialTheme(
            colorScheme = MaterialTheme.colorScheme.copy(
                primary = Color(6, 182, 0),
                secondary = Color(6, 182, 0),
                tertiary = Color(6, 182, 0),
                background = Color(240, 240, 240)
            )
        ) {
            //
            val tree = SplayTree<String, Position>()
            tree.insert("1", Position(0.dp, 0.dp))
            tree.insert("2", Position(0.dp, 0.dp))
            tree.insert("3", Position(0.dp, 0.dp))
            tree.insert("4", Position(0.dp, 0.dp))
            tree.insert("5", Position(0.dp, 0.dp))
            //
            setTreePositions(tree, defaultVertexSize, DpOffset(10.dp, 10.dp))
            //
            MainWindow(tree, "Tree", ::exitApplication)
        }
    }
}