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

import com.cuberto.flashytabbarohos.util.ResUtil;
import com.cuberto.flashytabbarsampleapp.ResourceTable;
import com.cuberto.flashytabbarsampleapp.slice.PageInfo;
import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;

public class TabComponent extends Component implements PageInfo {
    private AbilitySlice slice;
    private String name;
    private int txtColor;
    private Component rootView;

    /**
     * Instantiates a new Sample page view.
     *
     * @param abilitySlice the ability slice
     * @param name         the name
     * @param txtColor     the txt color
     */
    public TabComponent(AbilitySlice abilitySlice, String name, int txtColor) {
        super(abilitySlice);

        this.slice = abilitySlice;
        this.name =name;
        this.txtColor = txtColor;
        setRootView(loadView());
    }

    private Component loadView() {
        ComponentContainer layout = (ComponentContainer) LayoutScatter.getInstance(slice).parse
                (ResourceTable.Layout_page_one, null, false);
        Text txtStatus = (Text) layout.findComponentById(ResourceTable.Id_txtStatus);
        txtStatus.setTextColor(new Color( ResUtil.getColor(slice, txtColor)));
        txtStatus.setText( getName());
        return layout;
    }


    public String getName() {
        return this.name;
    }

    public void setRootView(Component component) {
        this.rootView = component;
    }

    public Component getRootView() {
        return this.rootView;
    }

    /**
     * Gets txt color.
     *
     * @return the txt color
     */
    public int getTxtColor() {
        return this.txtColor;
    }

    /**
     * Sets txt color.
     *
     * @param txtColor the txt color
     */
    public void setTxtColor(int txtColor) {
        this.txtColor = txtColor;
    }
}
