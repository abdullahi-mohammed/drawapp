package com.drawapp.model

/**
 * Manages the current state of the drawing, including the list of shapes.
 * Demonstrates:
 * - Use of mutable collections.
 * - Encapsulation of state.
 */
class DrawingState {
    private val shapes = mutableListOf<Shape>()

    /**
     * Adds a new shape to the collection.
     * @param shape The shape to be added.
     */
    fun add(shape: Shape) {
        shapes += shape
    }

    /**
     * Removes the most recently added shape from the collection (Undo functionality).
     */
    fun undo() {
        if (shapes.isNotEmpty()) {
            shapes.removeLast()
        }
    }

    /**
     * Removes all shapes from the collection (Clear functionality).
     */
    fun clear() {
        shapes.clear()
    }

    /**
     * Returns an immutable view of all shapes currently in the state.
     * @return A list containing all shapes.
     */
    fun all(): List<Shape> = shapes.toList()
}
