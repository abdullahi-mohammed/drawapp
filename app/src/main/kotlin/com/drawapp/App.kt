package com.drawapp

import com.drawapp.ui.DrawingCanvas
import com.drawapp.ui.Tool
import java.awt.BorderLayout
import java.awt.Color
import javax.swing.JButton
import javax.swing.JComboBox
import javax.swing.JFrame
import javax.swing.JToolBar
import javax.swing.SwingUtilities
import javax.swing.UIManager

/**
 * The main entry point for the Drawing Application.
 * Initializes the Swing UI on the Event Dispatch Thread and sets up the main window frame.
 */
fun main() {
    SwingUtilities.invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

        val frame = JFrame("Basic Drawing App")
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        val canvas = DrawingCanvas()
        frame.layout = BorderLayout()
        frame.add(createToolbar(canvas), BorderLayout.NORTH)
        frame.add(canvas, BorderLayout.CENTER)

        frame.setSize(900, 650)
        frame.setLocationRelativeTo(null)
        frame.isVisible = true
    }
}

/**
 * Creates and configures the JToolBar containing tools for the drawing canvas.
 * This demonstrates:
 * - Functional event listeners
 * - When expressions for state switching
 * - Variable declarations (val)
 * 
 * @param canvas The DrawingCanvas that this toolbar will control.
 * @return A configured JToolBar instance.
 */
private fun createToolbar(canvas: DrawingCanvas): JToolBar {
    val toolbar = JToolBar()
    toolbar.isFloatable = false

    val toolSelector = JComboBox(Tool.entries.toTypedArray())
    toolSelector.selectedItem = Tool.LINE
    toolSelector.addActionListener {
        canvas.selectedTool = toolSelector.selectedItem as Tool
    }

    val widths = arrayOf(1f, 2f, 4f, 6f, 8f)
    val widthSelector = JComboBox(widths)
    widthSelector.selectedItem = 2f
    canvas.strokeWidth = widthSelector.selectedItem as Float
    widthSelector.addActionListener {
        canvas.strokeWidth = widthSelector.selectedItem as Float
    }

    val colorButton = JButton("Color")
    colorButton.background = canvas.strokeColor
    colorButton.addActionListener {
        val next = when (canvas.strokeColor) {
            Color.BLACK -> Color.BLUE
            Color.BLUE -> Color.RED
            Color.RED -> Color.GREEN
            else -> Color.BLACK
        }
        canvas.strokeColor = next
        colorButton.background = next
    }

    val undoButton = JButton("Undo")
    undoButton.addActionListener { canvas.undoLast() }

    val clearButton = JButton("Clear")
    clearButton.addActionListener { canvas.clearAll() }

    toolbar.add(toolSelector)
    toolbar.add(widthSelector)
    toolbar.add(colorButton)
    toolbar.add(undoButton)
    toolbar.add(clearButton)

    return toolbar
}
