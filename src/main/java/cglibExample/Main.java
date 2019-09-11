package cglibExample;

import cglibExample.dao.daoImpl.MySqlNodeDaoImpl;
import cglibExample.entities.Node;
import cglibExample.proxy.MyEnhancer;
import net.sf.cglib.proxy.Enhancer;

/**
 *
 * 09.09.2019 21:49
 *
 * @author Edward
 */
public class Main {


    public static void main(String[] args) {

        MyEnhancer myEnhancer = new MyEnhancer();
        Enhancer enhancer = myEnhancer.getEnhancer();
        MySqlNodeDaoImpl proxy = (MySqlNodeDaoImpl) enhancer.create();
        Node testNodeFromInterceptedMethod = proxy.getById(1);

        System.out.println("Test Node from intercepted method: " + testNodeFromInterceptedMethod);

        Node node2 = new Node(12, "new name", false);
        proxy.update(node2);

        Integer numberOfNode = proxy.getNumberOfNode(node2);
        System.out.println(numberOfNode);

        proxy.create(new Node("home", true));
        proxy.delete(new Node(25));
    }
}
