//index.js
//获取应用实例
const app = getApp();
var util = require('../../utils/util.js');

Page({
  data: {
    habit_list: [],
    //   {
    //   habit_id: "12",
    //   habit_name: "晚上跑步",
    //   last_update_time: "2018/06/01 19:30",
    //   is_can_submit: "1"
    // }
  
    disabled: false,
  },


  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    
    var that = this;
    var token = null;
    wx.showLoading({
      title: '正在加载中...',
    });
    var tm = 0;    
    var timer =  setInterval(function () {
      tm ++;
        if (token != null || tm>30) {
          clearInterval(timer)
          var jsonString = '{"token":' + token + '}'
          //加载首页数据
          that.index(jsonString);
          wx.hideLoading();
          return;
        }
        token = app.globalData.token;
       
      }, 1000); 
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
    console.log("长按")
    //请求之前设置按钮不可用 防止重复提交
    console.log(str)
    wx.vibrateShort();
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

  index: function (jsonString){
    var that = this;

    util.sendPost('index', jsonString, function success(result) {
      console.log("result.code=" + result.resp_code)
      if (result.resp_code == 0) {
       
        that.setData({
          habit_list: result.data
        })
      }
      console.log('habit_list=' + JSON.stringify(that.data.habit_list[0]));

    }, function fail(result) {


    }, this,false);
  }  

})
