package com.gsy.gsylearning.view

import android.content.Context
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by gsy on 17/10/9.
 * 自定义闪动的textView
 */
class SplashTextView : TextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, style: Int) : super(context, attrs, style)

    private var mPaint: TextPaint? = null
    private var mLinearGradient: LinearGradient? = null
    private var mViewWidth = 0
    private var mGradientMatrix: Matrix? = null
    private var mTranslate: Int = 0


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5
            if (mTranslate > mViewWidth) mTranslate = -mViewWidth
            mGradientMatrix!!.setTranslate(mTranslate.toFloat(), 0f)
            mLinearGradient?.setLocalMatrix(mGradientMatrix)
            postInvalidateDelayed(100)
        }
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (mViewWidth == 0) {
            mViewWidth = measuredWidth
            if (mViewWidth > 0) {
                mPaint = paint
                mLinearGradient = LinearGradient(0f
                        , 0f
                        , mViewWidth.toFloat()
                        , 0f
                        , intArrayOf(Color.BLUE, Color.RED, Color.BLUE)
                        , null
                        , Shader.TileMode.CLAMP)
                mPaint?.shader = mLinearGradient
                mGradientMatrix = Matrix()
            }
        }
    }

}

