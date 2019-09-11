package cglibExample.dao.daoImpl;

import cglibExample.MyDeprecated;
import cglibExample.connection.MySqlConnectionPool;
import cglibExample.dao.NodeDao;
import cglibExample.entities.Node;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * 08.09.2019 21:40
 *
 * @author Edward
 */
public class MySqlNodeDaoImpl implements NodeDao {

    private static final String SQL_STATEMENT_CREATE = "INSERT INTO node (node_name, transaction_passed) VALUES (?, ?)";
    private static final String SQL_STATEMENT_GET_BY_ID = "SELECT * FROM node WHERE number = ?";
    private static final String SQL_STATEMENT_UPDATE = "UPDATE node SET node_name = ?, transaction_passed = ? WHERE number = ?";
    private static final String SQL_STATEMENT_DELETE = "DELETE FROM node WHERE number = ?";
    private static final Logger log = LogManager.getLogger(MySqlNodeDaoImpl.class.getName());


    /**
     * stupid method only for example
     *
     * @param node entity
     * @return node number
     */
    @MyDeprecated
    public Integer getNumberOfNode(Node node) {
        MySqlNodeDaoImpl mySqlNodeDao = new MySqlNodeDaoImpl();
        Node testNode = mySqlNodeDao.getById(node.getNumber());
        return testNode.getNumber();
    }

    @Override
    public void create(Node node) {
        try (Connection connection = MySqlConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, node.getName());
            statement.setBoolean(2, node.isTransactionPassed());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.warn("Creating node failed: no rows affected");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                log.warn("Creating node failed: no id obtained");
            }
            int number = generatedKeys.getInt(1);
            node.setNumber(number);
            generatedKeys.close();
            statement.close();
            log.debug("creation of node " + node.toString() + " was successful");
        } catch (SQLException e) {
            log.error("Can't create node" + e);
        }
    }

    @MyDeprecated
    @Override
    public Node getById(Integer number) {
        Node node;
        try (Connection connection = MySqlConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_GET_BY_ID);
            statement.setInt(1, number);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                log.warn("Node with number " + number + " doesn't exist");
            }
            String nodeName = resultSet.getString("node_name");
            boolean transactionPassed = resultSet.getBoolean("transaction_passed");

            node = new Node(number, nodeName, transactionPassed);
            resultSet.close();
            statement.close();
            log.debug("get node = " + node.toString());
        } catch (SQLException e) {
            log.error("Can't get node by number " + number + " " + e);
            return new Node(number, null, false);
        }
        return node;
    }


    @Override
    public void update(Node node) {
        try (Connection connection = MySqlConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_UPDATE);
            statement.setString(1, node.getName());
            statement.setBoolean(2, node.isTransactionPassed());
            statement.setInt(3, node.getNumber());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.warn("Updating node failed: no rows affected");
            }
            statement.close();
            log.debug("update node = " + node.toString());
        } catch (SQLException e) {
            log.error("Can't update node" + e);
        }
    }

    @Override
    public void delete(Node node) {
        try (Connection connection = MySqlConnectionPool.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SQL_STATEMENT_DELETE);
            statement.setInt(1, node.getNumber());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                log.warn("Deleting node failed: no rows affected");
            }
            statement.close();
            log.debug("node removal was successful");
        } catch (SQLException e) {
            log.error("Can't delete node" + e);
        }
    }
}
