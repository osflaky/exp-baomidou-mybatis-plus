package com.baomidou.mybatisplus.core.toolkit.support;

import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleProxies;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntSupplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * IDEA 代理 Lambda 表达式测试
 * <p>
 * Create by hcl at 2022/8/12
 */
class IdeaProxyLambdaMetaTest {

    @Test
    void test() throws NoSuchMethodException, IllegalAccessException {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodHandle handle = lookup.findStatic(IdeaProxyLambdaMetaTest.class, "s", MethodType.methodType(int.class));

        // 模拟 IDEA 的 lambda 生成（JDK 22 起生成的实例不再是 java.lang.reflect.Proxy）
        IntSupplier supplier = MethodHandleProxies.asInterfaceInstance(IntSupplier.class, handle);
        IdeaProxyLambdaMeta meta = new IdeaProxyLambdaMeta(supplier);
        assertEquals("s", meta.getImplMethodName());
    }

    private static int s() {
        return ThreadLocalRandom.current().nextInt();
    }

}
