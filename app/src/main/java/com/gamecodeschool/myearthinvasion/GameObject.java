package com.gamecodeschool.myearthinvasion;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

class GameObject {
    private float size;
    private float rotation;
    private RectF mCollider;
    private PointF mLocation;
    private boolean isActive = false;
    private String mTag;




    void updateCollider(){
        // Pull the borders in a bit (10%)
        mCollider.top = mLocation.y + (size / 10);
        mCollider.left = mLocation.x + (size /10);
        mCollider.bottom = (mCollider.top + size)
                - size/10;

        mCollider.right = (mCollider.left + size)
                -  size/10;
    }

    void setLocation(float horizontal, float vertical){
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }

    PointF getLocation() {

        return mLocation;
    }

    RectF getCollider(){
        return mCollider;
    }

    PointF getSize(){
        return new PointF((int)size,
                (int)size);
    }

    boolean checkActive() {
        return isActive;
    }

    void draw(Canvas canvas, Paint paint) {
        graphicsComponent.draw(canvas, paint, mTransform);
    }
}
