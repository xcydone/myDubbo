import com.crossyf.dubbo.springtest.Application;
import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.entity.ConfTableExecution;
import com.crossyf.dubbo.springtest.entity.Partner;
import com.crossyf.dubbo.springtest.mapper.ConfTableExecutionMapper;
import com.crossyf.dubbo.springtest.service.IConfTableExecutionService;
import com.crossyf.dubbo.springtest.service.IPartnerService;
import com.crossyf.dubbo.springtest.test.testYml.Popo;
import com.crossyf.dubbo.springtest.test.testYml.Popo2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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

    @Autowired
    public IConfTableExecutionService confTableExecutionService;

    @Autowired
    public IPartnerService partnerService;

    @Test
    public void testMybatis() {
        List<ConfTableExecutionDto> qryListtwo = confTableExecutionService.getConfTableExecutionTwo();
        System.out.println(qryListtwo.toString());

        List<ConfTableExecutionDto> qryListwe = confTableExecutionService.getConfTableExecutionWe();
        System.out.println(qryListwe.toString());
    }

    @Test
    public void testMybatis20() {
        Partner po = new Partner();
        po.setId("de2d44f2rdsfgre-322gyu3-1243-3fwfr");
        po.setName20("kjde");
        po.setLevelHehis("haha");
        partnerService.insertGG(po);
    }

}
