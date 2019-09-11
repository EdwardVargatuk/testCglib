package cglibExample.proxy;

import cglibExample.MyDeprecated;
import cglibExample.dao.daoImpl.MySqlNodeDaoImpl;
import cglibExample.entities.Node;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * my enhancer looks for intercept annotated methods with different return types and replace with new value;
 * and if methods are void, then invoke super method
 * <p>
 * 11.09.2019 23:08
 *
 * @author Edward
 */
public class MyEnhancer {

    private final Enhancer enhancer;

    public Enhancer getEnhancer() {
        return enhancer;
    }

    public MyEnhancer() {
        enhancer = setUpEnhancer();
    }

    private Enhancer setUpEnhancer() {
        Enhancer enhancer;
        enhancer = new Enhancer();
        enhancer.setSuperclass(MySqlNodeDaoImpl.class);

        enhancer.setCallback((MethodInterceptor) (obj, method, argS, proxy) -> {
            if (method.getDeclaringClass() != Object.class && method.isAnnotationPresent(MyDeprecated.class)) {
                if (method.getReturnType() == Node.class) {
                    return new Node(555, "this is joke node, no existed, because method is deprecated", false);

                } else if (method.getReturnType() == Integer.class) {
                    Node node = (Node) argS[0];
                    System.out.print("in proxy. number is = ");
                    return node.getNumber();
                }
            }
            if (method.getDeclaringClass() != Object.class && method.getReturnType().getName().equals("void")) {
                return proxy.invokeSuper(obj, argS);
            }

            return null;

        });
        return enhancer;
    }
}
