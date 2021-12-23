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

package com.cuberto.flashytabbarohos;


import ohos.agp.components.*;
import ohos.app.Context;
import java.util.ArrayList;

public class TabLayoutv2 extends DirectionalLayout implements Component.ClickedListener, Component.EstimateSizeListener{
    private Context context;
    private DirectionalLayout root;
    private PageSlider pageSlider;
    private ComponentContainer rootLayout;
    private PageSliderProvider provider;
    private DirectionalLayout clickedtab;
    public DependentLayout Tab;
    private boolean bstarted =false;
    private int highlightposition=0;
    private final ArrayList<DirectionalLayout> tabs = new ArrayList<>();

    public TabLayoutv2(Context context) {
        this(context, null);
    }
    public TabLayoutv2(Context context, AttrSet attrs) {
        this(context, attrs,"");
    }

    public TabLayoutv2(Context context, AttrSet attrs, String defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
        rootLayout = (ComponentContainer) LayoutScatter.getInstance(context)
                .parse(ResourceTable.Layout_tablayout, null, false);

        root = (DirectionalLayout)rootLayout.findComponentById(ResourceTable.Id_dlRootLayout);

        super.addComponent( rootLayout);
    }


    public void setupWithPageSlider(PageSlider pageSlider){
        this.pageSlider = pageSlider;

        this.provider = pageSlider.getProvider();
        initView();
        setEstimateSizeListener(this);
    }

    public void start(int highlightposition){
        bstarted =true;
        this.highlightposition = highlightposition;
        invalidate();
    }

    public int getTabCount(){

        if(null != provider)
            return provider.getCount();

        return 0;
    }

    public DirectionalLayout getTabAt(int index){
        if(null !=tabs)
            return tabs.get(index);

        return null;
    }

    private void initView(){

        for(int i=0; i < provider.getCount(); i++){
            DirectionalLayout tabitem = (DirectionalLayout) LayoutScatter.getInstance(context)
                    .parse(ResourceTable.Layout_tabitem, null, false);

            tabitem.setClickedListener( this);
            root.addComponent( tabitem);

            if( null ==clickedtab) {
                clickedtab = tabitem;
            }

            tabs.add( tabitem);
        }
    }

    @Override
    public void onClick(Component component) {
        if( null != pageSlider)
        {
            for( int i =0; i < tabs.size(); i++)
            {
                if( component == tabs.get(i)){
                    pageSlider.setCurrentPage( i);
                }
            }
        }
    }

    public Context getContext(){
        return context;
    }

    @Override
    public boolean onEstimateSize(int i, int i1) {

        //In Ohos UI measures after laying of views done. In ability onStart() its not getting values
        if(bstarted){
            pageSlider.setCurrentPage( ++highlightposition);
            pageSlider.invalidate();
            bstarted =false;

            //Below one is id required if user clicks on next tab, in that case page slide will not generate onPageChosen()
            context
                .getUITaskDispatcher()
                .asyncDispatch(
                        () ->{
                            pageSlider.setCurrentPage( --highlightposition);
                            pageSlider.invalidate();
                        }
                );
        }

        return false;
    }

    public class Tab extends DependentLayout {

        public Tab(Context context){
            super(context);
        }

        public Tab(Context context, AttrSet attrSet) {
            super(context, attrSet);

        }

        public Tab(Context context, AttrSet attrSet, String styleName) {
            super(context, attrSet, styleName);
        }
    }
}
