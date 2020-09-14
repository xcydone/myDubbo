# 知晓小说阅读webapp
## 关于部分功能出错的说明
由于近期追书神器调整页面，且部分API也做了相应的调整，以至相应功能出错，有兴趣的同学可以自己尝试修复  
目前发现的错误：  
  1、分类/排行列表大部分二级分类书籍为空  
  2、二级评论页内容为空且报错  
PS：追书神器API中的盗版书源也被封了，所以本站所有书源均为追书神器正版源，VIP章节无法阅读
## 本地运行
```
#安装依赖
npm install
#开发模式
npm run serve
#生产模式
npm run build
```
### node-sass报错
* Found bindings for the following environments:
* -OS X 64-bit with Node.js 6.x<br>
```
npm rebuild node-sass
```
### 预览地址
项目放在我自己的云服务器上，[在线预览](http://reader.hucoo.cn)

### 关于API来源
API来至追书神器，前段时间盗版源被封，如今用的是追书神器正版源，VIP章节无法阅读

### 项目截图

![书架](https://images.gitee.com/uploads/images/2019/0613/204008_73dddc2f_1964871.png "image1.png")

![分类](https://images.gitee.com/uploads/images/2019/0613/204109_79cf17de_1964871.png "image2.png")

![排行](https://images.gitee.com/uploads/images/2019/0613/204131_bc45ddb3_1964871.png "image3.png")

![搜索](https://images.gitee.com/uploads/images/2019/0613/204142_505068f3_1964871.png "image4.png")

![分类列表](https://images.gitee.com/uploads/images/2019/0613/204218_fd075090_1964871.png "image5.png")

![书籍详情](https://images.gitee.com/uploads/images/2019/0613/204247_752ed174_1964871.png "image6.png")

![一级评论页](https://images.gitee.com/uploads/images/2019/0613/204301_b17a81f5_1964871.png "image7.png")

![二级评论页](https://images.gitee.com/uploads/images/2019/0613/204322_131c4603_1964871.png "image8.png")

![目录](https://images.gitee.com/uploads/images/2019/0613/204338_f2936d85_1964871.png "image9.png")

![阅读](https://images.gitee.com/uploads/images/2019/0613/204354_b236850f_1964871.png "image10.png")
