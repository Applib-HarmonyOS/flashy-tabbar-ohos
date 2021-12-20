/*
 *  * Copyright (C) 2021 Huawei Device Co., Ltd.
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */

package com.cuberto.flashytabbarsampleapp.slice;

import com.cuberto.flashytabbarohos.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.PageSliderProvider;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Page view adapter.
 */
public class PageViewAdapter extends PageSliderProvider {
    private static final String TAG = PageViewAdapter.class.getCanonicalName();
    private AbilitySlice slice;
    private List<? extends PageInfo> pageViews;

    /**
     * Instantiates a new Page view adapter.
     *
     * @param abilitySlice the ability slice
     * @param list         the list
     */
    public PageViewAdapter(AbilitySlice abilitySlice, List<? extends PageInfo> list) {
        slice = abilitySlice;
        if (list != null) {
            pageViews = list;
            return;
        }
        pageViews = new ArrayList();
    }

    /**
     * isPageMatchToObject
     *
     * @param component The Component
     * @param obj The Object
     * @return boolean
     */
    @Override
    public boolean isPageMatchToObject(Component component, Object obj) {
        return true;
    }

    /**
     * gets Page Title
     *
     * @param position The item position
     * @return String
     */
    @Override
    public String getPageTitle(int position) {
//        List<AbstractPageView> abstractPageViews = (List<AbstractPageView>) pageViews;
        return pageViews.get(position).getName();
    }

    /**
     * gets Adapter Count
     *
     * @return count
     */
    public int getCount() {
        return this.pageViews.size();
    }

    /**
     * creates Individual Pages
     *
     * @param componentContainer the ComponentContainer
     * @param position the Current Position
     * @return Object
     */
    public Object createPageInContainer(ComponentContainer componentContainer, int position) {
        Component directionalLayout = new DirectionalLayout(slice);
        if (position >= 0 && position < this.pageViews.size()) {
            directionalLayout = pageViews.get(position).getRootView();
        }
        componentContainer.addComponent(directionalLayout);
        return directionalLayout;
    }

    /**
     * Destroys Pages
     *
     * @param componentContainer the ComponentContainer
     * @param position the Current Position
     * @param obj Object
     */
    public void destroyPageFromContainer(ComponentContainer componentContainer, int position, Object obj) {
        if (componentContainer == null) {
            LogUtil.error(TAG, "destory item failed, container is null");
        } else if (obj instanceof Component) {
            componentContainer.removeComponent((Component) obj);
        }else {
            LogUtil.error(TAG, "container is not an instance of Component");
        }
    }
}
