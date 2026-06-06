package com.drawapp

import com.drawapp.model.DrawingState
import com.drawapp.model.LineShape
import com.drawapp.model.RectangleShape
import java.awt.Color
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AppTest {
    @Test
    fun storesShapesInInsertionOrder() {
        val state = DrawingState()
        val line = LineShape(0, 0, 10, 10, Color.BLACK, 2f)
        val rectangle = RectangleShape(5, 5, 15, 20, Color.BLUE, 4f)

        state.add(line)
        state.add(rectangle)

        assertEquals(listOf(line, rectangle), state.all())
    }

    @Test
    fun undoRemovesOnlyLatestShape() {
        val state = DrawingState()
        val line = LineShape(0, 0, 10, 10, Color.BLACK, 2f)
        val rectangle = RectangleShape(5, 5, 15, 20, Color.BLUE, 4f)

        state.add(line)
        state.add(rectangle)
        state.undo()

        assertEquals(listOf(line), state.all())
    }

    @Test
    fun undoAndClearOnEmptyStateAreSafe() {
        val state = DrawingState()

        state.undo()
        state.clear()

        assertEquals(emptyList<Any>(), state.all())
    }
}
