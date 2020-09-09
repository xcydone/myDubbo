import com.crossyf.dubbo.springtest.Application;
import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.PartnerQryDto;
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

import java.util.*;

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
        po.setName20("kjde");
        po.setLevelHehis("haha");
        partnerService.insertGG(po);
    }

    @Test
    public void testMybatisParam() {
        String name = "尚";
        String leveName = "体育";
        List<PartnerDto> pls = partnerService.findPartnerByParam(name, leveName);
        System.out.println(pls.toString());
    }

    @Test
    public void testMybatisEntityLike() {
        PartnerQryDto qryDto = new PartnerQryDto();
        qryDto.setName("尚");
        qryDto.setLevelName("体育");
        List<PartnerDto> pls = partnerService.findPartnerByEntity(qryDto);
        System.out.println(pls.toString());
    }

    @Test
    public void testMybatisEntityChoose() {
        PartnerQryDto qryDto = new PartnerQryDto();
        /*qryDto.setName("尚");*/
        qryDto.setLevelName("体育");
        List<PartnerDto> pls = partnerService.findPartnerTwoOne(qryDto);
        System.out.println(pls.toString());
    }

    @Test
    public void testMybatisUpdate() {
        PartnerQryDto qryDto = new PartnerQryDto();
        qryDto.setId(11997);
        qryDto.setName("尚可可");
        qryDto.setLevelName("体育");
        partnerService.updatePartnerById(qryDto);
        System.out.println(partnerService.findPartnerByParam("尚可可",null).toString());
    }

    @Test
    public void testMybatisSelectIn() {
        List status = new ArrayList();
        status.add(1100);
        status.add(1200);
        List<PartnerDto> pls = partnerService.findPartnerInStatus(status);
        System.out.println(pls.toString());
    }

    @Test
    public void testMybatisSelectLang() {
        String name = "尚";
        String leveName = "体育";
        List<PartnerDto> pls = partnerService.findPartnerByLang(name, leveName);
        System.out.println(pls.toString());
    }

    @Test
    public void testMybatisSelectScript() {
        String name = "尚";
        String leveName = "体育";
        List<PartnerDto> pls = partnerService.findPartnerByScript(name, leveName);
        System.out.println(pls.toString());
    }

    @Test
    public void testMybatisSelectMap() {
        int id = 11988;
        Map<String, Object> cols = partnerService.findPartnerMap(id);
        System.out.println(cols.toString());
    }

}
