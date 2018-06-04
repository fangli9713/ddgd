//index.js
//获取应用实例
const app = getApp();
var util = require('../../utils/util.js');

Page({
  data: {
    habit_list: [{
      habit_id: "12",
      habit_name: "晚上跑步",
      last_update_time: "2018/06/01 19:30",
      is_can_submit: "1"
    },
    {
      habit_id: "13",
      habit_name: "中午午休",
      last_update_time: "2018/06/01 13:00",
      is_can_submit: "0"
    }],
    disabled: false,
    date_unit: ["时", "天", "周","月","年"],
    index:1,
    rate_period:[1,2,3,4,5,6,7,8,9],
    rate_index:0,
    unit_index:0,
  },


  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    wx.setNavigationBarTitle({
      title: '',
    })
  },
  addHabit: function (e) {
    wx.navigateTo({
      url: '/pages/habit/habit',
    })
  },
  userCenter: function (e) {
    wx.navigateTo({
      url: '/pages/user/user',
    })
  },
  paymentTemp: function (e) {
    var secret = e.target.dataset.id;
    var str = { "secret": secret };
    //请求之前设置按钮不可用 防止重复提交
    console.log(e)
    this.setData({
      disabled: true
    })
    util.sendPost('tempAndRechargeFee', str, function success(result) {
      console.log(result)

    }, function fail(result) {


    }, this);
    //console.log(e.target.dataset.id)
  }, 
  bindPickerChange1: function (e) {
    this.setData({
      rate_index: e.detail.value
    })
  },
  bindPickerChange2: function (e) {
    this.setData({
      unit_index: e.detail.value
    })
  },
  bindPickerChange3: function (e) {
    this.setData({
      index: e.detail.value
    })
  },
})
