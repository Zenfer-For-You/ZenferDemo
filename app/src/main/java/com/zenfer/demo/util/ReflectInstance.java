package com.zenfer.demo.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射创建类
 *
 * @author Created by gold on 2018/3/13 21:06
 */
public class ReflectInstance {

    public static <T> T newTypeInstance(Class<?> cls, int index) {
        return newTypeInstance(cls, index, (Object) null);
    }

    /**
     * 通过反射创建泛型Type对象
     *
     * @param cls    class
     * @param index  泛型索引
     * @param params 参数
     * @param <T>    类型
     * @return 对象
     */
    public static <T> T newTypeInstance(Class<?> cls, int index, Object... params) {
        Type genType = cls.getGenericSuperclass();
        Type[] typeParams = ((ParameterizedType) genType).getActualTypeArguments();
        return newInstance((Class) typeParams[index], params);
    }

    public static <T> T newInstance(String className) {
        return newInstance(className, (Object) null);
    }

    public static <T> T newInstance(String className, Object... params) {
        T instance = null;
        try {
            instance = newInstance(Class.forName(className), params);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static <T> T newInstance(Class<?> cls) {
        return newInstance(cls, (Object) null);
    }

    /**
     * 通过反射创建对象，如果参数与构造函数不对应则返回空
     * 如果参数全部为空则默认实现无参数构造函数
     *
     * @param cls    class
     * @param params 参数
     * @param <T>    类型
     * @return 对象
     */
    public static <T> T newInstance(Class<?> cls, Object... params) {
        T instance = null;
        try {
            if (params == null) {
                return (T) cls.newInstance();
            }

            final Constructor<?>[] constructors = cls.getConstructors();
            Constructor<?> constructor = null;

            for (Constructor<?> temp : constructors) {
                final int length = temp.getParameterAnnotations().length;
                if (length == params.length) {
                    final Class[] types = temp.getParameterTypes();
                    boolean isAlike = true;
                    for (int i = 0; i < length; i++) {
                        final Class<?> type = types[i];
                        if (!compareType(type, params[i])) {
                            isAlike = false;
                            break;
                        }
                    }
                    if (isAlike) {
                        constructor = temp;
                        break;
                    }
                }
            }

            if (constructor != null) {
                instance = (T) constructor.newInstance(params);
            } else {
                if (isEmptyObject(params)) {
                    return (T) cls.newInstance();
                } else {
                    throw new IllegalArgumentException("未找到符合的构造函数：" + toClassName(params));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private static String toClassName(Object[] params) {
        if (params == null) {
            return "params empty";
        }
        final StringBuilder sb = new StringBuilder();
        for (Object param : params) {
            sb.append("\n");
            sb.append("---");
            if (param == null) {
                sb.append("null");
            } else {
                sb.append(param.getClass().getName());
            }
        }
        return sb.toString();
    }

    private static boolean isEmptyObject(Object[] params) {
        if (params == null) {
            return true;
        }
        for (Object param : params) {
            if (param != null) {
                return false;
            }
        }
        return true;
    }

    private static boolean compareType(Class<?> type, Object param) {
        if (param == null) {
            return !type.isPrimitive();
        }
        return compareType(type, param.getClass());
    }

    private static boolean compareType(Class<?> type, Class<?> paramType) {
        if (type.isPrimitive()) {
            return getType(type).isAssignableFrom(paramType);
        } else {
            return type.isAssignableFrom(paramType);
        }
    }

    private static Class<?> getType(Class<?> type) {
        if (type == Byte.TYPE) {
            return Byte.class;
        } else if (type == Short.TYPE) {
            return Short.class;
        } else if (type == Integer.TYPE) {
            return Integer.class;
        } else if (type == Long.TYPE) {
            return Long.class;
        } else if (type == Float.TYPE) {
            return Float.class;
        } else if (type == Double.TYPE) {
            return Double.class;
        } else if (type == Boolean.TYPE) {
            return Boolean.class;
        } else if (type == Character.TYPE) {
            return Character.class;
        } else {
            return type;
        }
    }

    /**
     * 是否为基本类型
     *
     * @param type 类型
     */
    public static boolean isPrimitive(Class<?> type) {
        return getType(type).isPrimitive();
    }

}
