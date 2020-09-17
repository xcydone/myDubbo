import com.crossyf.dubbo.springtest.Application;
import com.crossyf.dubbo.springtest.dto.ConfTableExecutionDto;
import com.crossyf.dubbo.springtest.dto.PartnerDto;
import com.crossyf.dubbo.springtest.dto.PartnerQryDto;
import com.crossyf.dubbo.springtest.entity.Partner;
import com.crossyf.dubbo.springtest.service.IConfTableExecutionService;
import com.crossyf.dubbo.springtest.service.IPartnerService;
import com.crossyf.dubbo.springtest.test.testSort.BubbleSort;
import com.crossyf.dubbo.springtest.test.testSort.InsertSort;
import com.crossyf.dubbo.springtest.test.testSort.QuickSort;
import com.crossyf.dubbo.springtest.test.testSort.SelectionSort;
import com.crossyf.dubbo.springtest.test.testYml.Popo;
import com.crossyf.dubbo.springtest.test.testYml.Popo2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class testSort {
    @Autowired
    public BubbleSort bubbleSort;

    @Autowired
    public SelectionSort selectionSort;

    @Autowired
    public InsertSort insertSort;

    @Autowired
    public QuickSort quictSort;

    @Test
    public void testBubbleSort() {
        int[] sourceArr = new int[]{3,44,38,5,47,13};
        System.out.println(Arrays.toString(bubbleSort.sort(sourceArr)));
    }

    @Test
    public void testSelectionSort() {
        int[] sourceArr = new int[]{3,44,38,5,47,13};
        System.out.println(Arrays.toString(selectionSort.sort(sourceArr)));
    }

    @Test
    public void testInsertSort() {
        int[] sourceArr = new int[]{3,44,38,5,47,13};
        System.out.println(Arrays.toString(insertSort.sort(sourceArr)));
    }

    @Test
    public void testQuickSort() {
        int[] sourceArr = new int[]{44,3,38,5,47,13};
        try {
            System.out.println(Arrays.toString(quictSort.sort(sourceArr)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
