import com.crossyf.dubbo.springtest.Application;
import com.crossyf.dubbo.springtest.test.testYml.Popo;
import com.crossyf.dubbo.springtest.test.testYml.Popo2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class testfunc {
    @Autowired
    public Popo popo;

    @Autowired
    public Popo2 popo2;

    @Test
    public void testYml() {
        System.out.println(popo.getHaha());
        System.out.println(popo2.getHaha2());
    }
}
