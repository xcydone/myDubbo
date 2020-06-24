package com.crossyf.dubbo.redis.controller;

import com.crossyf.dubbo.common.constant.RedisConstants;
import com.crossyf.dubbo.common.constant.StateParameter;
import com.crossyf.dubbo.common.controller.BaseController;
import com.crossyf.dubbo.redis.api.IRedisService;
import com.crossyf.dubbo.redis.dto.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/redis")
public class RedisTestController extends BaseController {

    @Reference
    private IRedisService redisService;

    /*  value是String或者object类型的数据操作
    *  select 1 选择数据库
    *  set key value 存入string类型的key，value可以随意
    *  get key  查询string类型
    *  del key  删除操作
    *  key *   查看当前库中所有的key
    *  key apple* 查看当前库中，以apple开头的所有key
    */

    @RequestMapping(value="/setGetString")
    @ResponseBody
    public ModelMap setGetString(){
        try {
            redisService.setString(RedisConstants.datebase1,"redisString", "这是一条测试数据");
            String value = redisService.getString(RedisConstants.datebase1,"redisString").toString();
            logger.info("redisValue="+value);
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS, value, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

    @RequestMapping(value="/setGetUser")
    @ResponseBody
    public ModelMap setGetUser(){
        try {
            User user = new User();
            user.setName("隔壁老王");
            user.setAge(28);
            user.setId(getUuid());
            redisService.setString(RedisConstants.datebase1, "redisUser", user);
            User res = (User) redisService.getString(RedisConstants.datebase1, "redisUser");
            logger.info("res="+res.toString());
            logger.info("读取redis成功");
            return getModelMap(StateParameter.SUCCESS, res, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

    @RequestMapping(value="/delString")
    @ResponseBody
    public ModelMap delString(){
        try {
            if(!redisService.hasKey(RedisConstants.datebase1,"redisString")){
                logger.info("数据库中不存在要删除的数据");
                return getModelMap(StateParameter.FAULT, null, "数据库中不存在要删除的数据，操作失败");
            }
            redisService.delStringKey(RedisConstants.datebase1, "redisString");
            logger.info("删除redis成功");
            return getModelMap(StateParameter.SUCCESS, null, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }

    /*  value是List类型的数据操作
     *  select 1 选择数据库
     *  lpush key value 存入list类型
     *  lrange key start end 查询list类型
     *  del key  删除操作
     *  key *   查看当前库中所有的key
     *  key apple* 查看当前库中，以apple开头的所有key
     */
    @RequestMapping(value="/setGetList")
    @ResponseBody
    public ModelMap setGetList() {
        try{
            List<Object> llist = new ArrayList<>();
            llist.add("第n个list1元素");
            llist.add("第n个list2元素");
            redisService.setList(RedisConstants.datebase1, "L_list", llist);
            List<Object> result = redisService.getList(RedisConstants.datebase1, "L_list", 0, redisService.getListSize(RedisConstants.datebase1, "L_list"));
            logger.info("读取redis-list成功");
            return getModelMap(StateParameter.SUCCESS, Arrays.toString(result.toArray()), "操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return getModelMap(StateParameter.FAULT, null, "操作失败");
        }
    }
}
