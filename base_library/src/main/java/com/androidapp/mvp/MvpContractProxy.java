package com.androidapp.mvp;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MvpContractProxy {
    private static final MvpContractProxy m_instance = new MvpContractProxy();

    public static MvpContractProxy getInstance() {
        return m_instance;
    }

    private MvpContractProxy() {
        m_objects = new HashMap<>();
    }

    private Map<Class, Object> m_objects;
    /**
     *   Presenter
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
     *
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
    public static Class<MvpBasePresenter> getPresnterClazz(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return MvpBasePresenter.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return MvpBasePresenter.class;
        }
        if (!(params[index] instanceof Class)) {
            return MvpBasePresenter.class;
        }
        return (Class) params[index];
    }
    /**
     *  Model
     * 通过反射, 获得定义Class时声明的父类的泛型参数的类型.
     *
     *@param clazz
     *            clazz The class to introspect
     * @param index
     *            the Index of the generic ddeclaration,start from 0.
     * @return the index generic declaration, or Object.class if cannot be
     *         determined
     */
    @SuppressWarnings("unchecked")
    public static Class<MvpBaseModel> getModelClazz(final Class clazz, final int index) {

        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return MvpBaseModel.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return MvpBaseModel.class;
        }
        if (!(params[index] instanceof Class)) {
            return MvpBaseModel.class;
        }
        return (Class) params[index];
    }

    /**
     * 获取presenter
     *
     * @param clzz
     * @param <T>
     * @return
     */
    public <T> T presenter(Class clzz) {
        if (!m_objects.containsKey(clzz)) {
            initInstance(clzz);
        }
        MvpBasePresenter presenter = null;
        try {
            presenter = ((MvpBasePresenter) clzz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) presenter;
    }
    /**
     * 进行初始化
     *
     * @param clss
     */
    public void initInstance(Class clss) {
        try {
            m_objects.put(clss,clss.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
    /**
     *  绑定 View
     * @param presenter
     * @param <V>
     * @return
     */
    public <V> V bindView(MvpBaseView view, MvpBasePresenter presenter) {

        if (view != presenter.getView()) {
            if (presenter.getView() != null) {
                presenter.detachView();
            }
            presenter.attachView(view);
        }
        return (V) view;
    }
    /**
     * 绑定Persenter
     *
     * @param clzz
     * @param var1
     * @param <T>
     * @return
     */
    public <T> T bindPresenter(Class clzz, MvpBaseView var1) {
        if (!m_objects.containsKey(clzz)) {
//            init(clzz);
        }
        MvpBasePresenter presenter = null;
        try {
            presenter = ((MvpBasePresenter)clzz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (var1 != presenter.getView()) {
            if (presenter.getView() != null) {
                presenter.detachView();
            }
            presenter.attachView(var1);
        }
        return (T) presenter;
    }

    // 初始化model add map
    public <M> M bindModel(Class clzz,MvpBasePresenter presenter) {
        if (!m_objects.containsKey(clzz)) {
            initInstance(clzz);
        }
        MvpBaseModel model = null;
        try {
            model = ((MvpBaseModel) clzz.newInstance());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (model != presenter.getModel()) {
            if (presenter.getModel() != null) {
                presenter.detachModel();
            }
            presenter.attachModel(model);
        }
        return (M) model;
    }

    // 解除绑定 移除map
    public void unbindPresenter(Class clzz, MvpBaseView var1) {
        if (m_objects.containsKey(clzz)) {
            MvpBasePresenter presenter = null;
            try {
                presenter = ((MvpBasePresenter) clzz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (var1 != presenter.getView()) {
                if (presenter.getView() != null)
                    presenter.detachView();
                m_objects.remove(clzz);
            }
        }
    }
    // 解除绑定 移除map
    public void unbindView(MvpBaseView view, MvpBasePresenter presenter) {

        if (view != presenter.getView()) {
            if (presenter.getView() != null)
                presenter.detachView();
        }
    }

    // 解除绑定 移除map
    public void unbindModel(Class clzz, MvpBasePresenter presenter) {
        if (m_objects.containsKey(clzz)) {
            MvpBaseModel model = null;
            try {
                model = ((MvpBaseModel) clzz.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (model != presenter.getModel()) {
                if (presenter.getModel() != null)
                    presenter.detachModel();
                m_objects.remove(clzz);
            }
        }
    }
}

