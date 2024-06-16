package com.backend.flex.model.components.props;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DividerPropTest {

    private DividerProps dividerProps;

    @BeforeEach
    public void setUp() {
        dividerProps = new DividerProps();
    }

    @Test
    public void testSetGetColor() {
        dividerProps.setColor("Test Color");
        assert dividerProps.getColor().equals("Test Color");
    }

    @Test
    public void testSetGetSize() {
        dividerProps.setSize(10);
        assert dividerProps.getSize() == 10;
    }

    @Test
    public void testSetGetId() {
        dividerProps.setId(1L);
        assert dividerProps.getId() == 1L;
    }

    @Test
    public void testConstructor() {
        DividerProps dividerProps = new DividerProps("Test Color", 10);
        assert dividerProps.getColor().equals("Test Color");
        assert dividerProps.getSize() == 10;
    }

}
