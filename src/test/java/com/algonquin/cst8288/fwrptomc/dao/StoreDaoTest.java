package com.algonquin.cst8288.fwrptomc.dao;

import com.algonquin.cst8288.fwrptomc.model.Store;
import org.junit.jupiter.api.*;
import org.mockito.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author 
 */
public class StoreDaoTest {

    @Mock
    private JDBCClient jdbcClient;

    @InjectMocks
    private StoreDao dao;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(jdbcClient.getConnection()).thenReturn(connection);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddStore() throws SQLException {
        Store store = new Store();
        store.setStoreName("Test Store");
        store.setCity("Test City");
        store.setUid(1);

        String sql = "INSERT INTO store (store_name, city, uid) VALUES (?, ?, ?)";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        dao.addStore(store);

        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setString(1, store.getStoreName());
        verify(preparedStatement, times(1)).setString(2, store.getCity());
        verify(preparedStatement, times(1)).setInt(3, store.getUid());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testUpdateStore() throws SQLException {
        Store store = new Store();
        store.setStoreId(1);
        store.setStoreName("Updated Store");
        store.setCity("Updated City");
        store.setUid(2);

        String sql = "UPDATE store SET store_name = ?, city = ?, uid = ? WHERE store_id = ?";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        dao.updateStore(store);

        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setString(1, store.getStoreName());
        verify(preparedStatement, times(1)).setString(2, store.getCity());
        verify(preparedStatement, times(1)).setInt(3, store.getUid());
        verify(preparedStatement, times(1)).setInt(4, store.getStoreId());
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteStore() throws SQLException {
        int storeId = 1;
        String sql = "DELETE FROM store WHERE store_id = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        dao.deleteStore(storeId);

        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setInt(1, storeId);
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    public void testGetStoreById() throws SQLException {
        int storeId = 1;
        String sql = "SELECT * FROM store WHERE store_id = ?";
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        Store expected = new Store();
        expected.setStoreId(storeId);
        expected.setStoreName("Test Store");
        expected.setCity("Test City");
        expected.setUid(1);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("store_id")).thenReturn(storeId);
        when(resultSet.getString("store_name")).thenReturn("Test Store");
        when(resultSet.getString("city")).thenReturn("Test City");
        when(resultSet.getInt("uid")).thenReturn(1);

        Store actual = dao.getStoreById(storeId);

        assertEquals(expected.getStoreId(), actual.getStoreId());
        assertEquals(expected.getStoreName(), actual.getStoreName());
        assertEquals(expected.getCity(), actual.getCity());
        assertEquals(expected.getUid(), actual.getUid());

        verify(connection, times(1)).prepareStatement(sql);
        verify(preparedStatement, times(1)).setInt(1, storeId);
        verify(preparedStatement, times(1)).executeQuery();
        verify(resultSet, times(1)).next();
    }
}
