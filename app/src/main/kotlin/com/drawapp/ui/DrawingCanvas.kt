package com.drawapp.ui

import com.drawapp.model.CircleShape
import com.drawapp.model.DrawingState
import com.drawapp.model.LineShape
import com.drawapp.model.RectangleShape
import com.drawapp.model.Shape
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Point
import java.awt.RenderingHints
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JPanel

enum class Tool {
    LINE,
    RECTANGLE,
    CIRCLE,
}

class DrawingCanvas(
    private val state: DrawingState = DrawingState(),
) : JPanel() {
    var selectedTool: Tool = Tool.LINE
    var strokeColor: Color = Color.BLACK
    var strokeWidth: Float = 2f

    private var dragStart: Point? = null
    private var dragCurrent: Point? = null

    init {
        background = Color.WHITE
        preferredSize = Dimension(900, 600)

        val mouseHandler = object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                dragStart = e.point
                dragCurrent = e.point
                repaint()
            }

            override fun mouseDragged(e: MouseEvent) {
                dragCurrent = e.point
                repaint()
            }

            override fun mouseReleased(e: MouseEvent) {
                val start = dragStart ?: return
                val end = e.point
                createShape(start, end)?.let(state::add)
                dragStart = null
                dragCurrent = null
                repaint()
            }
        }

        addMouseListener(mouseHandler)
        addMouseMotionListener(mouseHandler)
    }

    /**
     * Renders the canvas content including all saved shapes and the current preview shape.
     * Demonstrates:
     * - Traditional 'for' loops for iterating through collections.
     * - Type casting (Graphics to Graphics2D).
     */
    override fun paintComponent(graphics: Graphics) {
        super.paintComponent(graphics)
        val g2 = graphics as Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        // Using a traditional for loop to meet module requirements
        val allShapes = state.all()
        for (shape in allShapes) {
            shape.draw(g2)
        }

        val preview = previewShape()
        if (preview != null) {
            preview.draw(g2)
        }
    }

    /**
     * Removes the last drawn shape from the drawing state and refreshes the UI.
     */
    fun undoLast() {
        state.undo()
        repaint()
    }

    /**
     * Removes all shapes from the drawing state and refreshes the UI.
     */
    fun clearAll() {
        state.clear()
        repaint()
    }

    /**
     * Calculates the shape that would be created based on the current mouse drag position.
     * @return A Shape object for previewing, or null if no drag is in progress.
     */
    private fun previewShape(): Shape? {
        val start = dragStart ?: return null
        val current = dragCurrent ?: return null
        return createShape(start, current)
    }

    /**
     * Factory method to create a Shape object based on the currently selected tool.
     * Demonstrates:
     * - 'When' expressions
     * - Conditional logic (if/else)
     * - Coordinate calculations
     * 
     * @param start The starting point of the shape (mouse press).
     * @param end The ending point of the shape (current mouse position or release).
     * @return The constructed Shape object.
     */
    private fun createShape(start: Point, end: Point): Shape? {
        if (start == end) {
            return null
        }

        return when (selectedTool) {
            Tool.LINE -> LineShape(start.x, start.y, end.x, end.y, strokeColor, strokeWidth)
            Tool.RECTANGLE -> {
                val x = minOf(start.x, end.x)
                val y = minOf(start.y, end.y)
                val width = kotlin.math.abs(end.x - start.x)
                val height = kotlin.math.abs(end.y - start.y)
                RectangleShape(x, y, width, height, strokeColor, strokeWidth)
            }

            Tool.CIRCLE -> {
                val diameter = minOf(kotlin.math.abs(end.x - start.x), kotlin.math.abs(end.y - start.y))
                if (diameter == 0) {
                    null
                } else {
                    val x = if (end.x >= start.x) start.x else start.x - diameter
                    val y = if (end.y >= start.y) start.y else start.y - diameter
                    CircleShape(x, y, diameter, strokeColor, strokeWidth)
                }
            }
        }
    }
}
