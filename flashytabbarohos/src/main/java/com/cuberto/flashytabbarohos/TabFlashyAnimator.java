package com.cuberto.flashytabbarohos;

import com.cuberto.flashytabbarohos.util.LogUtil;
import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.components.*;
import ohos.agp.utils.Color;
import java.util.ArrayList;
import java.util.List;

public class TabFlashyAnimator implements PageSlider.PageChangedListener{
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private final List<Integer> mFragmentIconList = new ArrayList<>();
    private final List<Integer> mFragmentColorList = new ArrayList<>();
    private TabLayoutv2 tabLayout;
    private DirectionalLayout clickedtab;
    private int highlightposition =0;
    private boolean bStarted =false;
    private boolean bPendingPageChosen =false;
    int deSelectedImgHeight =0;
    int deSelectedImg_incr =0;
    int deSelectedUpdateCount =0;
    int selectedImgHeight =0;
    int selectedImg_incr =0;
    int selectedUpdateCount =0;
    private int unselectedColorId = Color.BLACK.getValue();

    public TabFlashyAnimator(TabLayoutv2 tabLayout) {
        this.tabLayout = tabLayout;
    }

    public void addTabItem(String title, int tabIcon, int tabColor) {
        mFragmentTitleList.add(title);
        mFragmentIconList.add(tabIcon);
        mFragmentColorList.add(tabColor);
    }

    public void highLightTab(int position) {
        if (tabLayout != null) {
            highlightposition = position;

            if(bStarted) {
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    DirectionalLayout tab = tabLayout.getTabAt(i);
                    getTabView(i, tab, i == position);
                    tabLayout.invalidate();
                }
            }
            else{
                for (int i = 0; i < tabLayout.getTabCount(); i++) {
                    DirectionalLayout tab = tabLayout.getTabAt(i);
                    DirectionalLayout stackLayout =(DirectionalLayout)tab.findComponentById(ResourceTable.Id_innertab);
                    Text text =(Text)stackLayout.findComponentById(ResourceTable.Id_tabtext);

                    Image image = (Image)stackLayout.findComponentById(ResourceTable.Id_tabimage);
                    image.setPixelMap( mFragmentIconList.get(i));
                    text.setText( mFragmentTitleList.get(i));
                    text.setVisibility(Component.INVISIBLE);
                }
                tabLayout.invalidate();
            }
        }
    }

    private void handlePreviousCLickedTab(DirectionalLayout tab){
        DirectionalLayout stackLayout =(DirectionalLayout)tab.findComponentById(ResourceTable.Id_innertab);
        Text text =(Text)stackLayout.findComponentById(ResourceTable.Id_tabtext);
        Image image = (Image)stackLayout.findComponentById(ResourceTable.Id_tabimage);

        LogUtil.debug("TFA","Entering getTabView and tab ==clickedtab case");
        LogUtil.debug("TFA","Entering getTabView and image.getHeight() is " +image.getHeight());
        //BEGIN
        text.setVisibility(Component.INVISIBLE);
        deSelectedImgHeight = image.getHeight();
        if(0 != deSelectedImgHeight) {
            deSelectedImg_incr = deSelectedImgHeight / 10;
        }

        deSelectedUpdateCount =0;
        ValueAnimator mAnimation = ValueAnimator.ofFloat(0, image.getHeight());
        mAnimation.setDuration( 400);
        mAnimation.setCurveType(Animator.CurveType.LINEAR);
        mAnimation.setValueUpdateListener( new AnimatorValue.ValueUpdateListener(){

            @Override
            public void onUpdate(AnimatorValue animatorValue, float v) {
                image.setTranslationY( image.getTranslationY() + deSelectedImg_incr);
                text.setTranslationY( text.getTranslationY() + deSelectedImg_incr);
                deSelectedUpdateCount++;
                clickedtab.invalidate();
            }
        });

        mAnimation.setStateChangedListener(new Animator.StateChangedListener() {
            @Override
            public void onStart(Animator animator) {

            }

            @Override
            public void onStop(Animator animator) {

            }

            @Override
            public void onCancel(Animator animator) {

            }

            @Override
            public void onEnd(Animator animator) {

                LogUtil.debug("TFA","Entering onEnd and image deSelectedUpdateCount is " + deSelectedUpdateCount + " deSelectedImgHeight is "+ deSelectedImgHeight);
                int delta_height = deSelectedImgHeight -(deSelectedImg_incr*deSelectedUpdateCount);
                image.setTranslationY( image.getTranslationY() + delta_height);
                text.setTranslationY( text.getTranslationY() + delta_height);
                clickedtab.invalidate();
            }

            @Override
            public void onPause(Animator animator) {

            }

            @Override
            public void onResume(Animator animator) {

            }
        });

        clickedtab.invalidate();
        mAnimation.start();
    }

    private void getTabView(int position, DirectionalLayout tab, boolean isSelected) {

        LogUtil.debug("TFA","Entering getTabView and isSelected" + isSelected);
        DirectionalLayout stackLayout =(DirectionalLayout)tab.findComponentById(ResourceTable.Id_innertab);
        Text text =(Text)stackLayout.findComponentById(ResourceTable.Id_tabtext);

        Image image = (Image)stackLayout.findComponentById(ResourceTable.Id_tabimage);
        image.setPixelMap( mFragmentIconList.get(position));

        if (isSelected) {

            if( null ==clickedtab) {
                text.setVisibility(Component.VISIBLE);
                text.setText(mFragmentTitleList.get(position));
                clickedtab = tab;

                LogUtil.debug("TFA","Entering getTabView and image.getHeight() is " +image.getHeight());
                tabLayout.getContext()
                        .getUITaskDispatcher()
                        .asyncDispatch(
                                () ->{
                                    image.setTranslationY( image.getTranslationY()-image.getHeight());
                                    text.setTranslationY( text.getTranslationY()- image.getHeight());
                                    clickedtab.invalidate();
                                }
                        );
            }
            else if (clickedtab != tab)  //If clicked tab same as current one then do nothing
            {
                handlePreviousCLickedTab( clickedtab);  //First undo previously clicked tab then handle current clicked tab
                clickedtab = tab;

                text.setVisibility(Component.VISIBLE);
                selectedImgHeight = image.getHeight();
                if(0 != selectedImgHeight) {
                    selectedImg_incr = selectedImgHeight / 10;
                }

                selectedUpdateCount =0;
                ValueAnimator mAnimation = ValueAnimator.ofFloat(0, image.getHeight());
                mAnimation.setDuration( 400);
                mAnimation.setCurveType(Animator.CurveType.LINEAR);
                mAnimation.setValueUpdateListener( new AnimatorValue.ValueUpdateListener(){

                    @Override
                    public void onUpdate(AnimatorValue animatorValue, float v) {
                        image.setTranslationY( image.getTranslationY() - selectedImg_incr);
                        text.setTranslationY( text.getTranslationY() - selectedImg_incr);
                        selectedUpdateCount++;
                        clickedtab.invalidate();
                    }
                });

                mAnimation.setStateChangedListener(new Animator.StateChangedListener() {
                    @Override
                    public void onStart(Animator animator) {

                    }

                    @Override
                    public void onStop(Animator animator) {

                    }

                    @Override
                    public void onCancel(Animator animator) {

                    }

                    @Override
                    public void onEnd(Animator animator) {

                        LogUtil.debug("TFA","Entering onEnd and image selectedUpdateCount is " + selectedUpdateCount + " selectedImgHeight is "+ selectedImgHeight);
                        int delta_height = selectedImgHeight -(selectedImg_incr*selectedUpdateCount);
                        image.setTranslationY( image.getTranslationY() - delta_height);
                        text.setTranslationY( text.getTranslationY() - delta_height);
                        clickedtab.invalidate();
                    }

                    @Override
                    public void onPause(Animator animator) {

                    }

                    @Override
                    public void onResume(Animator animator) {

                    }
                });

                clickedtab.invalidate();
                mAnimation.start();
            }
        }
    }


    @Override
    public void onPageSliding(int i, float v, int i1) {

    }

    @Override
    public void onPageSlideStateChanged(int i) {

    }

    @Override
    public void onPageChosen(int position) {

        if(bPendingPageChosen) //This is from tabview
        {
            highLightTab(--position);
            bPendingPageChosen =false;
        }
        else {
            highLightTab(position);
        }
    }


    public void setUnselectedColorId(int unselectedColorId) {
        this.unselectedColorId = unselectedColorId;
    }

    public void Start() {
        bStarted =true;
        bPendingPageChosen =true;
        tabLayout.start( highlightposition);
    }

}

