package com.cuber.lgfw.tests.unit.mapper;

import com.cuber.lgfw.tests.unit.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class DatabaseConnectionTest extends BaseUnitTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Test
    void testDataSourceConnection() throws SQLException {
        assertNotNull(dataSource, "数据源不应为null");

        try (Connection connection = dataSource.getConnection()) {
            assertNotNull(connection, "数据库连接不应为null");
            assertFalse(connection.isClosed(), "数据库连接应该是打开的");
            System.out.println("数据库连接测试成功");
        }
    }

    @Test
    void testJdbcTemplateConnection() {
        assertNotNull(jdbcTemplate, "JdbcTemplate不应为null");

        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertEquals(1, result, "应该能执行简单的SQL查询");
        System.out.println("JdbcTemplate连接测试成功");
    }
}
