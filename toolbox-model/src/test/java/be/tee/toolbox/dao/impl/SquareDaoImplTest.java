package be.tee.toolbox.dao.impl;

import be.tee.toolbox.dao.SquareDao;
import be.tee.toolbox.model.Square;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import org.unitils.UnitilsTestNG;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBeanByType;

@DataSet(
        {"square.xml"}
)
@SpringApplicationContext({"classpath:dao-test-datasource.xml"})
public class SquareDaoImplTest extends UnitilsTestNG {

    @SpringBeanByType
    private SquareDao squareDao;

    @Test
    public void testFindSquareByName() {
        Square actual = squareDao.findSquareByName("damien");
        Assertions.assertThat(actual.getId()).isEqualTo(1);
        Assertions.assertThat(actual.getName()).isEqualTo("damien");
        actual = squareDao.findSquareByName("marco");
        Assertions.assertThat(actual.getId()).isEqualTo(2);
        Assertions.assertThat(actual.getName()).isEqualTo("marco");
        actual = squareDao.findSquareByName("babla");
        Assertions.assertThat(actual).isNull();
    }
}