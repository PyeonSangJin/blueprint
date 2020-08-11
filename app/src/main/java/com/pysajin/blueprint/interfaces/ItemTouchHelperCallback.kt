package com.pysajin.blueprint.interfaces

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import android.view.MotionEvent
import com.pysajin.blueprint.R

enum class ButtonState {
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE
}

class ItemTouchHelperCallback(private var listener: ItemTouchHelperListener, private val context: Context) : ItemTouchHelper.Callback() {
    private var swipeBack: Boolean = false
    private var buttonsShowedState: ButtonState = ButtonState.GONE
    private val buttonWidth: Float = 115f
    private var buttonInstance: RectF? = null
    private var currentItemViewHolder: RecyclerView.ViewHolder? = null

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlag: Int = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlag: Int = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listener.onItemSwipe(viewHolder.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        var dx: Float = dX
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (buttonsShowedState != ButtonState.GONE) {
                if (buttonsShowedState == ButtonState.LEFT_VISIBLE) dx = Math.max(dx, buttonWidth)
                if (buttonsShowedState == ButtonState.RIGHT_VISIBLE) dx = Math.min(dx, -buttonWidth)
                super.onChildDraw(c, recyclerView, viewHolder, dx, dY, actionState, isCurrentlyActive)
            } else {
                setTouchListener(c, recyclerView, viewHolder, dx, dY, actionState, isCurrentlyActive)
            }
            if (buttonsShowedState == ButtonState.GONE) {
                super.onChildDraw(c, recyclerView, viewHolder, dx, dY, actionState, isCurrentlyActive)
            }
        }

        currentItemViewHolder = viewHolder
        drawButtons(c, currentItemViewHolder!!)
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        if (swipeBack) {
            swipeBack = false
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithoutPadding = buttonWidth - 10
        val corners = 5f

        val itemView = viewHolder.itemView
        val p = Paint()

        //오른쪽으로 스와이프 했을때 (왼쪽에 버튼이 보여지게 될 경우)
        if (buttonsShowedState == ButtonState.LEFT_VISIBLE) {
            val leftButton = RectF(
                (itemView.left + 10).toFloat(),
                (itemView.top + 10).toFloat(),
                itemView.left + buttonWidthWithoutPadding,
                (itemView.bottom - 10).toFloat()
            )
            p.color = Color.BLUE
            c.drawRoundRect(leftButton, corners, corners, p)
            drawText(context.getString(R.string.update), c, leftButton, p)
            buttonInstance = leftButton
        }

        //왼쪽으로 스와이프 했을때 (오른쪽에 버튼이 보여지게 될 경우)
        else if (buttonsShowedState == ButtonState.RIGHT_VISIBLE) {
            val rightButton = RectF(
                itemView.right - buttonWidthWithoutPadding,
                (itemView.top + 10).toFloat(),
                (itemView.right - 10).toFloat(),
                (itemView.bottom - 10).toFloat()
            )
            p.color = Color.RED
            c.drawRoundRect(rightButton, corners, corners, p)
            drawText(context.getString(R.string.delete), c, rightButton, p)

            buttonInstance = rightButton
        }
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint) {
        val textSize = 25f
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize

        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float, dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) = recyclerView.setOnTouchListener { _, event ->
        swipeBack = event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP
        if (swipeBack) {
            if (dX < -buttonWidth)
                buttonsShowedState = ButtonState.RIGHT_VISIBLE
            else if (dX > buttonWidth)
                buttonsShowedState = ButtonState.LEFT_VISIBLE

            if (buttonsShowedState != ButtonState.GONE) {
                setTouchDownListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive)
                setItemsClickable(recyclerView, false)
            }
        }
        false
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchDownListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                setTouchUpListener(c, recyclerView, viewHolder, dY, actionState, isCurrentlyActive)
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setTouchUpListener(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dY: Float,
        actionState: Int, isCurrentlyActive: Boolean
    ) {
        recyclerView.setOnTouchListener { _, event ->
            super@ItemTouchHelperCallback.onChildDraw(
                c,
                recyclerView,
                viewHolder,
                0f,
                dY,
                actionState,
                isCurrentlyActive
            )
            recyclerView.setOnTouchListener { _, _ -> false }
            setItemsClickable(recyclerView, true)
            swipeBack = false

            if (buttonInstance != null && buttonInstance!!.contains(event.x, event.y)) {
                if (buttonsShowedState == ButtonState.LEFT_VISIBLE) {
                    listener.onLeftClick(viewHolder.adapterPosition, viewHolder)
                } else if (buttonsShowedState == ButtonState.RIGHT_VISIBLE) {
                    listener.onRightClick(viewHolder.adapterPosition, viewHolder)
                }
            }

            buttonsShowedState = ButtonState.GONE
            currentItemViewHolder = null
            false
        }
    }

    private fun setItemsClickable(
        recyclerView: RecyclerView,
        isClickable: Boolean
    ) {
        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }

}