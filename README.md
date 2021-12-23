# FlashyTabBar

[![GitHub license](https://img.shields.io/badge/license-MIT-lightgrey.svg)](https://raw.githubusercontent.com/Cuberto/flashy-tabbar-android/master/LICENSE)

![Animation](https://raw.githubusercontent.com/Cuberto/flashy-tabbar-android/master/Screenshots/animation.gif)


## Example

To run the example project, clone the repo, and run `MainAbilitySlice`


### As library

- inside dependencies of the build.gradle of entry module, use the following code
```
    dependencies {
        //consume library
        implementation fileTree(dir: 'libs', include: ['*.jar', '*.har'])
        ....
    }
```
Sync project and now you can use flashytabbar library

### Manual

Add `TabFlashyAnimator` and content of res package to your project

## Usage

Add TabLayout to your xml

```
    <com.cuberto.flashytabbarohos.TabLayoutv2
        ohos:id="$+id:tablayout"
        ohos:weight ="2"
        ohos:height="match_content"
        ohos:width="match_content">

    </com.cuberto.flashytabbarohos.TabLayoutv2>
        
```

Create adapter in your AbilitySlice, add some Component and set PageSlider adapter
```
    TabLayoutv2 tabLayout;
    private String[] titles = new String[]{"Home", "Clock", "Folder", "Menu"};
    private int[] colors = new int[]{ResourceTable.Color_home, ResourceTable.Color_clock, ResourceTable.Color_folder, ResourceTable.Color_menu};
    private Context context;
    private ComponentContainer rootLayout;
    private PageSlider mPager;
    private PageViewAdapter pageViewAdapter;
    private TabBubbleAnimator tabBubbleAnimator;
    private List<PageInfo> mPageViews;

    @Override
    protected void onStart(Intent intent) {

        super.onStart(intent);
        rootLayout = (ComponentContainer)LayoutScatter.getInstance(this)
                .parse(ResourceTable.Layout_ability_main, null, false);

        tabLayout = (TabLayoutv2)rootLayout.findComponentById(ResourceTable.Id_tablayout);
        mPager = (PageSlider) ResUtil.findComponentById(rootLayout, ResourceTable.Id_slider).get();

        mPageViews = new ArrayList();
        mPageViews.add(new TabComponent(this, titles[0], colors[0]));
        mPageViews.add(new TabComponent(this, titles[1], colors[1]));
        mPageViews.add(new TabComponent(this, titles[2], colors[2]));
        mPageViews.add(new TabComponent(this, titles[3], colors[3]));

        pageViewAdapter = new PageViewAdapter(this, mPageViews);
        mPager.setProvider(pageViewAdapter);
    }


```

Setup your TabLayout with ViewPager
```
        TabLayout tabLayout = (TabLayoutv2)rootLayout.findComponentById(ResourceTable.Id_tablayout);
        tabLayout.setupWithPageSlider(mPager);
```

Create TabFlashyAnimator and tabItem as title, image id and color for each tab item. 
```
        tabFlashyAnimator = new TabFlashyAnimator(tabLayout);
        tabFlashyAnimator.addTabItem(titles[0], ResourceTable.Media_ic_events, colors[0]);
        tabFlashyAnimator.addTabItem(titles[1], ResourceTable.Media_ic_highlights,colors[1]);
        tabFlashyAnimator.addTabItem(titles[2], ResourceTable.Media_ic_search, colors[2]);
        tabFlashyAnimator.addTabItem(titles[3], ResourceTable.Media_ic_settings, colors[3]);

```
Call highlightTab() for 0 position and add tabFlashyAnimator as addPageChangedListener to ViewPager
```
        tabFlashyAnimator.highLightTab(0);
        mPager.addPageChangedListener(tabFlashyAnimator);
```



Call tabFlashyAnimator onStart() to start 
```

            tabFlashyAnimator.onStart();

```

## iOS

Similar library [FlashyTabBar](https://github.com/Cuberto/flashy-tabbar) by [Cuberto](https://github.com/Cuberto)

## Author

Cuberto Design, info@cuberto.com

## License

FlashyTabbar is available under the MIT license. See the LICENSE file for more info.
