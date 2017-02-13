package com.wix.interactable;

import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by zachik on 12/02/2017.
 */

public class InteractablePoint {
    public float x;
    public float y;
    public float damping;
    public float tension;
    public float strength;
    public float falloff;
    public InteractableLimit limitX;
    public InteractableLimit limitY;

    public InteractablePoint(float x, float y, float damping, float tension, float strength, float falloff, InteractableLimit limitX, InteractableLimit limitY) {
        this.x = x;
        this.y = y;
        this.damping = damping;
        this.tension = tension;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    public PointF positionWithOrigin() {
        PointF res = new PointF(0,0);
        if (this.x != Float.MAX_VALUE) res.x += this.x;
        if (this.y != Float.MAX_VALUE) res.y += this.y;
        return res;
    }

    public float distanceFromPoint(PointF point) {
        float dx = Float.MAX_VALUE;
        float dy = Float.MAX_VALUE;
        if (this.x != Float.MAX_VALUE) dx = point.x -  this.x;
        if (this.y != Float.MAX_VALUE) dy = point.y - this.y;
        if (dx == Float.MAX_VALUE && dy == Float.MAX_VALUE) return Float.MAX_VALUE;
        if (dx == Float.MAX_VALUE) return Math.abs(dy);
        if (dy == Float.MAX_VALUE) return Math.abs(dx);
        return (float) Math.sqrt(dx*dx + dy*dy);
    }

    public static Point deltaBetweenPointAndOrigin(Point point,Point origin) {
        return new Point(point.x - origin.x, point.y - origin.y);
    }

    public static InteractablePoint findClosestPoint(ArrayList<InteractablePoint> pointArray,
                                                     PointF relativePoint) {
        float minDist = Float.MAX_VALUE;
        InteractablePoint closestPoint = null;
        for (InteractablePoint point : pointArray) {
            Log.d("InteractableView","findClosestPoint point x = " + point.x);

            float curDist = point.distanceFromPoint(relativePoint);
            if (curDist < minDist) {
                minDist = curDist;
                closestPoint = point;
            }
        }
        return closestPoint;

    }


}