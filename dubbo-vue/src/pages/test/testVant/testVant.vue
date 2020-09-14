<template>
    <div>
      <div>
        <van-image
          width="10rem"
          height="10rem"
          fit="contain"
          src="https://img.yzcdn.cn/vant/cat.jpeg"
        />
      </div>
      <div>
        <van-button type="primary">主要按钮</van-button>
        <van-button type="info">信息按钮</van-button>
        <van-button type="default">默认按钮</van-button>
        <van-button type="warning">警告按钮</van-button>
        <van-button type="danger">危险按钮</van-button>
      </div>
      <div width="200px">
        <van-sku
          v-model="show"
          :sku="sku"
          :goods="goods"
          :goods-id="goodsId"
          :quota="quota"
          :quota-used="quotaUsed"
          :hide-stock="sku.hide_stock"
          :message-config="messageConfig"
          @buy-clicked="onBuyClicked"
          @add-cart="onAddCartClicked"
        />
      </div>
    </div>
</template>

<style>
    body {
        background-color: white;
    }
</style>

<script>
    export default {
        data() {
            return {
              msg: "Long long time ago",

              show: true,
              sku: {
                // 所有sku规格类目与其值的从属关系，比如商品有颜色和尺码两大类规格，颜色下面又有红色和蓝色两个规格值。
                // 可以理解为一个商品可以有多个规格类目，一个规格类目下可以有多个规格值。
                tree: [
                  {
                    k: '颜色', // skuKeyName：规格类目名称
                    k_s: 's1', // skuKeyStr：sku 组合列表（下方 list）中当前类目对应的 key 值，value 值会是从属于当前类目的一个规格值 id
                    v: [
                      {
                        id: '1', // skuValueId：规格值 id
                        name: '红色', // skuValueName：规格值名称
                        imgUrl: 'https://img.yzcdn.cn/1.jpg', // 规格类目图片，只有第一个规格类目可以定义图片
                        previewImgUrl: 'https://img.yzcdn.cn/1p.jpg', // 用于预览显示的规格类目图片
                      },
                      {
                        id: '1',
                        name: '蓝色',
                        imgUrl: 'https://img.yzcdn.cn/2.jpg',
                        previewImgUrl: 'https://img.yzcdn.cn/2p.jpg',
                      }
                    ],
                    largeImageMode: true, //  是否展示大图模式
                  }
                ],
                // 所有 sku 的组合列表，比如红色、M 码为一个 sku 组合，红色、S 码为另一个组合
                list: [
                  {
                    id: 2259, // skuId
                    s1: '1', // 规格类目 k_s 为 s1 的对应规格值 id
                    s2: '1', // 规格类目 k_s 为 s2 的对应规格值 id
                    price: 100, // 价格（单位分）
                    stock_num: 110 // 当前 sku 组合对应的库存
                  }
                ],
                price: '1.00', // 默认价格（单位元）
                stock_num: 227, // 商品总库存
                collection_id: 2261, // 无规格商品 skuId 取 collection_id，否则取所选 sku 组合对应的 id
                none_sku: false, // 是否无规格商品
                messages: [
                  {
                    // 商品留言
                    datetime: '0', // 留言类型为 time 时，是否含日期。'1' 表示包含
                    multiple: '0', // 留言类型为 text 时，是否多行文本。'1' 表示多行
                    name: '留言', // 留言名称
                    type: 'text', // 留言类型，可选: id_no（身份证）, text, tel, date, time, email
                    required: '1', // 是否必填 '1' 表示必填
                    placeholder: '' // 可选值，占位文本
                  }
                ],
                hide_stock: false // 是否隐藏剩余库存
              },
              goods: {
                picture: 'https://img.yzcdn.cn/1.jpg'
              },
              messageConfig: {
                // 图片上传回调，需要返回一个promise，promise正确执行的结果需要是一个图片url
                uploadImg: () => {
                  return new Promise((resolve) => {
                    setTimeout(() => resolve('https://img.yzcdn.cn/upload_files/2017/02/21/FjKTOxjVgnUuPmHJRdunvYky9OHP.jpg!100x100.jpg'), 1000);
                  });
                },
                // 最大上传体积 (MB)
                uploadMaxSize: 3,
                // placeholder 配置
                placeholderMap: {
                  text: 'xxx',
                  tel: 'xxx',
                },
                // 初始留言信息
                // 键：留言 name
                // 值：留言内容
                initialMessages: {
                  留言: '留言信息'
                }
              },
            }
        }
    }
</script>
