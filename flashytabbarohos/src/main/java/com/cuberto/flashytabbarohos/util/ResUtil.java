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

package com.cuberto.flashytabbarohos.util;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.colors.RgbColor;
import ohos.agp.components.*;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.PixelMapElement;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.components.element.VectorElement;
import ohos.agp.render.Arc;
import ohos.agp.render.Canvas;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.utils.Rect;
import ohos.agp.utils.TextAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;
import ohos.global.resource.*;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Size;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;


/**
 * The type Res util.
 */
public class ResUtil {
    private static final String TAG = "ResUtil";

    private static final String CITY_ID_ATTR = "cityId_";

    private static final String STRING_ID_ATTR = "String_";

    private static Map<Integer, PixelMap> imageCache = new HashMap();


    private ResUtil() {
    }

    /**
     * get the path from id
     *
     * @param context the context
     * @param id      the id
     * @return the path from id
     */
    public static String getPathById(Context context, int id) {
        String path = "";
        if (context == null) {
            LogUtil.error(TAG, "getPathById -> get null context");
            return path;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getPathById -> get null ResourceManager");
            return path;
        }
        try {
            path = manager.getMediaPath(id);
        } catch (IOException e) {
            LogUtil.error(TAG, "getPathById -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getPathById -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getPathById -> WrongTypeException");
        }
        return path;
    }

    /**
     * get the new color
     *
     * @param context the context
     * @param id      the id
     * @return the color
     */
    public static Color getNewColor(Context context, int id) {
        Color result = new Color(getColor(context, id));
        return result;
    }

    /**
     * get the color
     *
     * @param context the context
     * @param id      the id
     * @return the color
     */
    public static int getColor(Context context, int id) {
        int result = 0;
        if (context == null) {
            LogUtil.error(TAG, "getColor -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getColor -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getColor();
        } catch (IOException e) {
            LogUtil.error(TAG, "getColor -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getColor -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getColor -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the dimen value
     *
     * @param context the context
     * @param id      the id
     * @return get the float dimen value
     */
    public static float getDimen(Context context, int id) {
        float result = 0;
        if (context == null) {
            LogUtil.error(TAG, "getDimen -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getDimen -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getFloat();
        } catch (IOException e) {
            LogUtil.error(TAG, "getDimen -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getDimen -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getDimen -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the boolean value
     *
     * @param context the context
     * @param id      the id
     * @return get the boolean dimen value
     */
    public static boolean getBoolean(Context context, int id) {
        boolean result = false;
        if (context == null) {
            LogUtil.error(TAG, "getBoolean -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getBoolean -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getBoolean();
        } catch (IOException e) {
            LogUtil.error(TAG, "getBoolean -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getBoolean -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getBoolean -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the dimen value
     *
     * @param context the context
     * @param id      the id
     * @return get the int dimen value
     */
    public static int getIntDimen(Context context, int id) {
        float value = getDimen(context, id);
        return (int) (value >= 0 ? value + Const.FLOAT_HALF : value - Const.FLOAT_HALF);
    }

    /**
     * get string
     *
     * @param context the context
     * @param id      the string id
     * @return string of the given id
     */
    public static String getString(Context context, int id) {
        String result = "";
        if (context == null) {
            LogUtil.error(TAG, "getString -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getString -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getString();
        } catch (IOException e) {
            LogUtil.error(TAG, "getString -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getString -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getString -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the string array
     *
     * @param context the context
     * @param id      the string array id
     * @return the string array
     */
    public static String[] getStringArray(Context context, int id) {
        String[] result = null;
        if (context == null) {
            LogUtil.error(TAG, "getStringArray -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getStringArray -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getStringArray();
        } catch (IOException e) {
            LogUtil.error(TAG, "getStringArray -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getStringArray -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getStringArray -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the int
     *
     * @param context the context
     * @param id      the int array
     * @return the int array
     */
    public static int getInt(Context context, int id) {
        int result = 0;
        if (context == null) {
            LogUtil.error(TAG, "getInt -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getInt -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getInteger();
        } catch (IOException e) {
            LogUtil.error(TAG, "getInt -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getInt -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getInt -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the int array
     *
     * @param context the context
     * @param id      the int array
     * @return the int array
     */
    public static int[] getIntArray(Context context, int id) {
        int[] result = null;
        if (context == null) {
            LogUtil.error(TAG, "getIntArray -> get null context");
            return result;
        }
        ResourceManager manager = context.getResourceManager();
        if (manager == null) {
            LogUtil.error(TAG, "getIntArray -> get null ResourceManager");
            return result;
        }
        try {
            result = manager.getElement(id).getIntArray();
        } catch (IOException e) {
            LogUtil.error(TAG, "getIntArray -> IOException");
        } catch (NotExistException e) {
            LogUtil.error(TAG, "getIntArray -> NotExistException");
        } catch (WrongTypeException e) {
            LogUtil.error(TAG, "getIntArray -> WrongTypeException");
        }
        return result;
    }

    /**
     * get the vector drawable
     *
     * @param context the context
     * @param id      the drawable id
     * @return the vector drawable
     */
    public static Optional<VectorElement> getVectorDrawable(Context context, int id) {
        if (context == null) {
            LogUtil.error(TAG, "getVectorDrawable -> get null context");
            return Optional.empty();
        }
        return  Optional.of(new VectorElement(context, id));
    }

    /**
     * get the pixel map
     *
     * @param context the context
     * @param id      the id
     * @return the pixel map
     */
    public static Optional<PixelMap> getPixelMap(Context context, int id) {
        String path = getPathById(context, id);
        if (TextUtils.isEmpty(path)) {
            LogUtil.error(TAG, "getPixelMap -> get empty path");
            return Optional.empty();
        }
        RawFileEntry assetManager = context.getResourceManager().getRawFileEntry(path);
        ImageSource.SourceOptions options = new ImageSource.SourceOptions();
        options.formatHint = "image/png";
        ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
        try {
            Resource asset = assetManager.openRawFile();
            ImageSource source = ImageSource.create(asset, options);
            return Optional.ofNullable(source.createPixelmap(decodingOptions));
        } catch (IOException e) {
            LogUtil.error(TAG, "getPixelMap -> IOException");
        }
        return Optional.empty();
    }

    /**
     * get the Pixel Map Element
     *
     * @param context the context
     * @param resId   the res id
     * @return the Pixel Map Element
     */
    public static PixelMapElement getPixelMapDrawable(Context context, int resId) {
        Optional<PixelMap> optional = getPixelMap(context, resId);
        return optional.map(PixelMapElement::new).orElse(null);
    }

    /**
     * get the Element
     *
     * @param color the color
     * @return the Element
     */
    public static Element buildDrawableByColor(int color) {
        ShapeElement drawable = new ShapeElement();
        drawable.setShape(ShapeElement.RECTANGLE);
        drawable.setRgbColor(RgbColor.fromArgbInt(color));
        return drawable;
    }

    /**
     * get the Element By ColorRadius
     *
     * @param color  the color
     * @param radius the radius
     * @return the Element By ColorRadius
     */
    public static Element buildDrawableByColorRadius(int color, float radius) {
        ShapeElement drawable = new ShapeElement();
        drawable.setShape(ShapeElement.RECTANGLE);
        drawable.setRgbColor(RgbColor.fromArgbInt(color));
        drawable.setCornerRadius(radius);
        return drawable;
    }

    /**
     * get the ShapeElement
     *
     * @param thickness  the thickness
     * @param inside     the inside color
     * @param border     the border color
     * @param startAngle the start angle
     * @param sweepAngle the sweep angle
     * @return the ShapeElement
     */
    public static ShapeElement getCustomArcGradientDrawable(int thickness, Color inside, Color border, float startAngle,
                                                            float sweepAngle) {
        ShapeElement drawable = new ShapeElement();
        drawable.setShape(ShapeElement.ARC);
        drawable.setRgbColor(RgbColor.fromArgbInt(inside.getValue())); // keep it transparent for main(inner) part
        drawable.setArc(new Arc(startAngle, sweepAngle, false));
        drawable.setStroke(thickness, RgbColor.fromArgbInt(border.getValue()));
        return drawable;
    }

    /**
     * get the Element
     *
     * @param strokeWidth thickness
     * @param inside      the inside color
     * @param border      the border color
     * @param rect        the rect
     * @return the Element
     */
    public static Element getCustomCircleGradientDrawable(int strokeWidth, Color inside, Color border, Rect rect) {
        ShapeElement element = new ShapeElement();
        element.setShape(ShapeElement.OVAL);
        element.setRgbColor(RgbColor.fromArgbInt(inside.getValue()));
        element.setStroke(strokeWidth, RgbColor.fromArgbInt(border.getValue()));
        element.setBounds(rect);
        return element;
    }

    /**
     * get the Element
     *
     * @param inside the inside color
     * @param rect   the rect
     * @return the Element
     */
    public static Element getCustomRectGradientDrawable(Color inside, Rect rect) {
        ShapeElement element = new ShapeElement();
        element.setShape(ShapeElement.RECTANGLE);
        element.setRgbColor(RgbColor.fromArgbInt(inside.getValue()));
        element.setBounds(rect);
        return element;
    }

    /**
     * Create tab icon.
     *
     * @param abilitySlice the ability slice
     * @param tab          the tab
     * @param id           the id
     */
    public static void createTabIcon(AbilitySlice abilitySlice, TabList.Tab tab, int id) {
        if (tab == null) {
            LogUtil.error(TAG, "createTabIcon failed");
            return;
        }
        try {
            PixelMap pixelMap = createByResourceId(abilitySlice, id, "image/png");
            PixelMapElement pixelMapElement = new PixelMapElement(pixelMap);
            pixelMapElement.setBounds(0, 0, 50, 50);
            tab.setAroundElements(pixelMapElement, null, null, null);
            tab.setPadding(30, 0, 0, 0);
        } catch (NotExistException | IOException e) {
            LogUtil.error(TAG, "createTabIcon " + e.getLocalizedMessage());
        }
    }

    /**
     * Create icons.
     *
     * @param abilitySlice the ability slice
     * @param tab          the tab
     * @param id           the id
     */
    public static void createIcons(AbilitySlice abilitySlice, TabList.Tab tab, int id) {
        if (tab == null) {
            LogUtil.error(TAG, "createTabIcon failed");
            return;
        }
        try {
            PixelMap pixelMap = createByResourceId(abilitySlice, id, "image/png");
            PixelMapElement pixelMapElement = new PixelMapElement(pixelMap);
            pixelMapElement.setBounds(0, 0, 150, 150);
            //tab.setIconElement(pixelMapElement);  //TODO: Mahesh

//            int width =tab.getWidth();
//            int height =tab.getHeight();

//            Element element = new CustomElement(abilitySlice, id);  //TODO:Mahesh commented
//            element.setBounds(0, 0, 400, 250);
//            tab.setIconElement(element);
//
            tab.addDrawTask(new Component.DrawTask() {
                @Override
                public void onDraw(Component component, Canvas canvas) {

                    int width =component.getWidth();
                    int height =component.getHeight();
                    int toppadding =component.getPaddingTop();
                    int bottompadding =component.getPaddingBottom();

                    TabList.Tab tab = (TabList.Tab )component;
                    tab.getIconElement().drawToCanvas( canvas);

                }
            });

//            ShapeElement element = new ShapeElement();
//            element.setShape(ShapeElement.OVAL);
//            element.setRgbColor(RgbColor.fromArgbInt(Color.BLACK.getValue()));
//            element.setStroke(5, RgbColor.fromArgbInt(Color.RED.getValue()));
//            element.setBounds(0, 0, 90, 90);
//            tab.setIconElement(element);


//            tab.setPadding(5, 5, 5, 5);
        } catch (NotExistException | IOException e) {
            LogUtil.error(TAG, "createTabIcon " + e.getLocalizedMessage());
        }
    }

    /**
     * Create by resource id pixel map.
     *
     * @param abilitySlice the ability slice
     * @param id           the id
     * @param str          the str
     * @return the pixel map
     * @throws IOException       the io exception
     * @throws NotExistException the not exist exception
     */
    public static PixelMap createByResourceId(AbilitySlice abilitySlice, int id, String str)
            throws IOException, NotExistException {
        if (abilitySlice == null) {
            LogUtil.error(TAG, "createByResourceId but slice is null");
            throw new IOException();
        } else if (imageCache.containsKey(Integer.valueOf(id))) {
            return imageCache.get(Integer.valueOf(id));
        } else {
            ResourceManager resourceManager = abilitySlice.getResourceManager();
            if (resourceManager != null) {
                Resource resource = resourceManager.getResource(id);
                if (resource != null) {
                    ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
                    sourceOptions.formatHint = str;
                    ImageSource create = ImageSource.create(readResource(resource), sourceOptions);
                    resource.close();
                    if (create != null) {
                        ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
                        decodingOptions.desiredSize = new Size(0, 0);
                        decodingOptions.desiredRegion = new ohos.media.image.common.Rect(0, 0, 0, 0);
                        decodingOptions.desiredPixelFormat = PixelFormat.ARGB_8888;
                        PixelMap pixelMap = create.createPixelmap(decodingOptions);
                        imageCache.put(Integer.valueOf(id), pixelMap);
                        return pixelMap;
                    }
                    LogUtil.error(TAG, "imageSource is null");
                    throw new FileNotFoundException();
                }
                LogUtil.error(TAG, "get resource failed");
                throw new IOException();
            }
            LogUtil.error(TAG, "get resource manager failed");
            throw new IOException();
        }
    }

    public static PixelMap createByResourceId2(AbilitySlice abilitySlice, int id, String str)
            throws IOException, NotExistException {
        if (abilitySlice == null) {
            LogUtil.error(TAG, "createByResourceId but slice is null");
            throw new IOException();
        } else if (imageCache.containsKey(Integer.valueOf(id))) {
            return imageCache.get(Integer.valueOf(id));
        } else {
            ResourceManager resourceManager = abilitySlice.getResourceManager();
            if (resourceManager != null) {
                Resource resource = resourceManager.getResource(id);
                if (resource != null) {
                    ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
                    sourceOptions.formatHint = str;
                    ImageSource create = ImageSource.create(readResource(resource), sourceOptions);
                    resource.close();
                    if (create != null) {
                        ImageSource.DecodingOptions decodingOptions = new ImageSource.DecodingOptions();
                        decodingOptions.desiredSize = new Size(0, 0);
                        decodingOptions.desiredRegion = new ohos.media.image.common.Rect(0, 0, 0, 0);
                        decodingOptions.desiredPixelFormat = PixelFormat.ARGB_8888;
                        PixelMap pixelMap = create.createPixelmap(decodingOptions);
                        imageCache.put(Integer.valueOf(id), pixelMap);
                        return pixelMap;
                    }
                    LogUtil.error(TAG, "imageSource is null");
                    throw new FileNotFoundException();
                }
                LogUtil.error(TAG, "get resource failed");
                throw new IOException();
            }
            LogUtil.error(TAG, "get resource manager failed");
            throw new IOException();
        }
    }

    private static byte[] readResource(Resource resource) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int read = resource.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (IOException e) {
                LogUtil.error(TAG, "readResource failed " + e.getLocalizedMessage());
            }finally {
                byteArrayOutputStream.close();
            }
        }
        LogUtil.debug(TAG, "readResource finish");
        LogUtil.debug(TAG, "readResource len: " + byteArrayOutputStream.size());
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * Show toast.
     *
     * @param abilityContext the ability context
     * @param str            the str
     * @param duration       the duration
     */
    public static void showToast(Context abilityContext, String str, int duration) {
        Text text = new Text(abilityContext);
        text.setWidth(ComponentContainer.LayoutConfig.MATCH_PARENT);
        text.setHeight(ComponentContainer.LayoutConfig.MATCH_CONTENT);
        text.setTextSize(48);
        text.setText(str);
        text.setMultipleLine(true);
        text.setTextAlignment(TextAlignment.CENTER);
        ShapeElement shapeElement = (ShapeElement) buildDrawableByColor(Color.WHITE.getValue());
        text.setBackground(shapeElement);
        DirectionalLayout directionalLayout = new DirectionalLayout(abilityContext);
        directionalLayout.setBackground(shapeElement);
        DirectionalLayout.LayoutConfig layoutConfig = new DirectionalLayout.LayoutConfig
                (DirectionalLayout.LayoutConfig.MATCH_PARENT, DirectionalLayout.LayoutConfig.MATCH_CONTENT);
        layoutConfig.setMarginBottom(100);
        directionalLayout.setLayoutConfig(layoutConfig);
        directionalLayout.setPadding(20, 30, 20, 30);
        directionalLayout.addComponent(text);
        ToastDialog toastDialog = new ToastDialog(abilityContext);
        toastDialog.setComponent(directionalLayout);
        toastDialog.setAlignment(LayoutAlignment.BOTTOM).setTransparent(true).show();
    }

    /**
     * get res id by reflect
     *
     * @param resClass res class
     * @param resName  res name
     * @return res id
     */
    public static OptionalInt getResIdByReflect(Class resClass, String resName) {
        return OptionalInt.empty();
    }


    /**
     * get native city name
     *
     * @param context the context
     * @param cityId  cityId
     * @return city name from cityId
     */
    public static Optional<String> getNativeCityName(Context context, String cityId) {
        return Optional.empty();
    }

    /**
     * find view by id
     *
     * @param <T>  type
     * @param view rootView
     * @param id   res id
     * @return view optional
     */
    public static <T extends Component> Optional<T> findComponentById(Component view, int id) {
        if (view != null && view.findComponentById(id) instanceof Component) {
            return Optional.of((T) view.findComponentById(id));
        }else {
            return Optional.empty();
        }
    }

    /**
     * Vp to px int.
     *
     * @param context the context
     * @param vp      the vp
     * @return the int
     */
    public static int vpToPx(Context context, int vp) {
        final float scale = context.getResourceManager().getDeviceCapability().screenDensity / 160;
        return Math.round(vp * scale);
    }
}

