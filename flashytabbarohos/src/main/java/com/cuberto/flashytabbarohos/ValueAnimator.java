/*
 * Copyright (C) 2021 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cuberto.flashytabbarohos;

import ohos.agp.animation.AnimatorValue;

/**
 * ValueAnimator.
 */
public class ValueAnimator extends AnimatorValue {
    private float start = 0;
    private float end = 1;
    private ValueUpdateListener myValueUpdateListener;

    private ValueAnimator() {
        super();
    }

    /**
     * * ValueAnimator
     *
     * @param start float
     * @param end   float
     * @return SlidingUpBuilder
     */
    public static ValueAnimator ofFloat(float start, float end) {
        ValueAnimator myValueAnimator = new ValueAnimator();
        myValueAnimator.start = start;
        myValueAnimator.end = end;
        return myValueAnimator;
    }

    private ValueUpdateListener valueUpdateListener = new ValueUpdateListener() {
        @Override
        public void onUpdate(AnimatorValue animatorValue, float value) {
            float val = value;
            val = val * (end - start) + start;
            if (myValueUpdateListener != null) {
                myValueUpdateListener.onUpdate(animatorValue, val);
            }
        }
    };

    /**
     * * Constructor
     *
     * @param startValue float
     * @param endValue   float
     */
    public void setFloatValues(float startValue, float endValue) {
        this.start = startValue;
        this.end = endValue;
    }

    @Override
    public void setValueUpdateListener(ValueUpdateListener listener) {
        this.myValueUpdateListener = listener;
        super.setValueUpdateListener(valueUpdateListener);
    }

    /**
     * * setFloatValues
     *
     * @param interpolator integer
     */
    public void setInterpolator(int interpolator) {
    }

    /**
     * * ofFloat
     *
     * @return SlidingUpBuilder
     */
    public static ValueAnimator ofFloat() {
        ValueAnimator myValueAnimator = new ValueAnimator();
        return myValueAnimator;
    }

    public float getStart() {
        return start;
    }

    public float getEnd() {
        return end;
    }
}