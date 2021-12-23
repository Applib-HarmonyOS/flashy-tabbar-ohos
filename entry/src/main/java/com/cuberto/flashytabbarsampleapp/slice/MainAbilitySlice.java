/*
 * Copyright (c) 2020 Huawei Device Co., Ltd.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cuberto.flashytabbarsampleapp.slice;

import com.cuberto.flashytabbarohos.TabFlashyAnimator;
import com.cuberto.flashytabbarohos.TabLayoutv2;
import com.cuberto.flashytabbarohos.util.ResUtil;
import com.cuberto.flashytabbarsampleapp.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.app.Context;
import java.util.ArrayList;
import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    TabLayoutv2 tabLayout;
    private String[] titles = new String[]{"Events", "Highlights", "Search", "Settings"};
    private int[] colors = new int[]{ResourceTable.Color_home, ResourceTable.Color_clock, ResourceTable.Color_folder, ResourceTable.Color_menu};
    private Context context;
    private ComponentContainer rootLayout;
    private PageSlider mPager;
    private PageViewAdapter pageViewAdapter;
    private TabFlashyAnimator tabFlashyAnimator;
    private List<PageInfo> mPageViews;

    @Override
    protected void onStart(Intent intent) {

        super.onStart(intent);
        rootLayout = (ComponentContainer)LayoutScatter.getInstance(this)
                .parse(ResourceTable.Layout_ability_main, null, false);

        tabLayout = (TabLayoutv2)rootLayout.findComponentById(ResourceTable.Id_tablayout);
        mPager = (PageSlider) ResUtil.findComponentById(rootLayout, ResourceTable.Id_slider).get();

        ShapeElement shapeElement = new ShapeElement();
        shapeElement.setRgbColor(RgbColor.fromArgbInt(ResUtil.getColor(getApplicationContext(), ResourceTable.Color_white) ));
        shapeElement.setShape(ShapeElement.RECTANGLE);
        shapeElement.setCornerRadius(10);
        shapeElement.setStroke(3, RgbColor.fromArgbInt(ResUtil.getColor(getApplicationContext(), ResourceTable.Color_black) ));
        mPager.setBackground( shapeElement);

        initPageView( Color.WHITE);
        tabLayout.setupWithPageSlider( mPager);
        tabFlashyAnimator = new TabFlashyAnimator(tabLayout);
        tabFlashyAnimator.addTabItem(titles[0], ResourceTable.Media_ic_events, colors[0]);
        tabFlashyAnimator.addTabItem(titles[1], ResourceTable.Media_ic_highlights,colors[1]);
        tabFlashyAnimator.addTabItem(titles[2], ResourceTable.Media_ic_search, colors[2]);
        tabFlashyAnimator.addTabItem(titles[3], ResourceTable.Media_ic_settings, colors[3]);
        tabFlashyAnimator.highLightTab(0); 
        mPager.addPageChangedListener( tabFlashyAnimator);

        tabFlashyAnimator.Start();
        setUIContent( rootLayout);
    }

    private void initPageView(Color color) {
        mPageViews = new ArrayList();
        mPageViews.add(new TabComponent(this, titles[0], colors[0]));
        mPageViews.add(new TabComponent(this, titles[1], colors[1]));
        mPageViews.add(new TabComponent(this, titles[2], colors[2]));
        mPageViews.add(new TabComponent(this, titles[3], colors[3]));

        pageViewAdapter = new PageViewAdapter(this, mPageViews);
        mPager.setProvider(pageViewAdapter);
        mPager.setOrientation(Component.HORIZONTAL);
        mPager.setSlidingPossible(true);
    }

    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);

    }
}
