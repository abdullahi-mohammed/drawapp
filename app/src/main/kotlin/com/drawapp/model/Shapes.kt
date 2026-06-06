package com.drawapp.model

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D

/**
 * Represents a drawable geometric shape.
 * Demonstrates:
 * - Sealed interfaces for restricted class hierarchies.
 * - Interface properties.
 */
sealed interface Shape {
    val color: Color
    val strokeWidth: Float

    /**
     * Draws the shape onto the provided Graphics2D context.
     */
    fun draw(g2: Graphics2D)
}

/**
 * A line shape defined by two points.
 * Demonstrates:
 * - Data classes for immutable data storage.
 */
data class LineShape(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
    override val color: Color,
    override val strokeWidth: Float,
) : Shape {
    /**
     * Draws the line using AWT drawLine.
     */
    override fun draw(g2: Graphics2D) {
        g2.color = color
        g2.stroke = BasicStroke(strokeWidth)
        g2.drawLine(x1, y1, x2, y2)
    }
}

/**
 * A rectangle shape defined by top-left coordinates and dimensions.
 * Demonstrates:
 * - Data classes.
 */
data class RectangleShape(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    override val color: Color,
    override val strokeWidth: Float,
) : Shape {
    /**
     * Draws the rectangle using AWT drawRect.
     */
    override fun draw(g2: Graphics2D) {
        g2.color = color
        g2.stroke = BasicStroke(strokeWidth)
        g2.drawRect(x, y, width, height)
    }
}

/**
 * A circle (oval) shape defined by top-left bounding box and diameter.
 * Demonstrates:
 * - Data classes.
 */
data class CircleShape(
    val x: Int,
    val y: Int,
    val diameter: Int,
    override val color: Color,
    override val strokeWidth: Float,
) : Shape {
    /**
     * Draws the oval using AWT drawOval.
     */
    override fun draw(g2: Graphics2D) {
        g2.color = color
        g2.stroke = BasicStroke(strokeWidth)
        g2.drawOval(x, y, diameter, diameter)
    }
}
