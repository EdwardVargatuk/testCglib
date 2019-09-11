package cglibExample.dao;

import cglibExample.entities.Node;


/**
 * 08.09.2019 21:02
 *
 * @author Edward
 */

public interface NodeDao {
    void create(Node node);

    Node getById(Integer id);

    void update(Node bonus);

    void delete(Node bonus);

}
