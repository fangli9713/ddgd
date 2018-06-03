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
      },
      {
        habit_id: "14",
        habit_name: "中午午休",
        last_update_time: "2018/06/01 13:00",
        is_can_submit: "0"
    },
    {
      habit_id: "15",
      habit_name: "中午午休",
      last_update_time: "2018/06/01 13:00",
      is_can_submit: "0"
      },
      {
        habit_id: "16",
        habit_name: "中午午休",
        last_update_time: "2018/06/01 13:00",
        is_can_submit: "0"
    },
    {
      habit_id: "17",
      habit_name: "中午午休",
      last_update_time: "2018/06/01 13:00",
      is_can_submit: "0"
      },
      {
        habit_id: "18",
        habit_name: "中午午休",
        last_update_time: "2018/06/01 13:00",
        is_can_submit: "0"
    },
    {
      habit_id: "19",
      habit_name: "中午午休",
      last_update_time: "2018/06/01 13:00",
      is_can_submit: "0"
      },
      {
        habit_id: "20",
        habit_name: "中午午休",
        last_update_time: "2018/06/01 13:00",
        is_can_submit: "0"
      }],
    disabled: false,
  },


  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    wx.setNavigationBarTitle({
      title: '铛叮个铛',
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
  bindTouchStart: function (e) {
    this.startTime = e.timeStamp;
  },
bindTouchEnd: function (e) {
    this.endTime = e.timeStamp;
  },

  finish: function (e) {
    if (this.endTime - this.startTime < 350) {
      console.log("点击");

    }else{
      console.log("长按，不处理")
      return;
    }
    var id = e.currentTarget.dataset.id;
    var str = { "id": id };
    //请求之前设置按钮不可用 防止重复提交
    console.log(str)
    return;
    this.setData({
      disabled: true
    })
    util.sendPost('', str, function success(result) {
      console.log(result)

    }, function fail(result) {


    }, this);
    //console.log(e.target.dataset.id)
  },
  //长按
  finishLong: function (e) {
    var id = e.currentTarget.dataset.id;
    var str = { "idLong": id };
    //请求之前设置按钮不可用 防止重复提交
    console.log(str)
   // wx.vibrateShort();
    return;
    this.setData({
      disabled: true
    })
    util.sendPost('', str, function success(result) {
      console.log(result)

    }, function fail(result) {


    }, this);
    //console.log(e.target.dataset.id)
  }

})
