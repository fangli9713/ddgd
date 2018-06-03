const app = getApp()
Page({
  
  onLoad: function () {
    wx.setNavigationBarTitle({
      title: '个人中心',
    })
  },
  car:function(){
    wx.navigateTo({
      url: '/pages/user/Car',
    })
  },
  monthCard: function () {
    wx.navigateTo({
      url: '/pages/user/MonthCard',
    })
  }, 
  order: function () {
    wx.navigateTo({
      url: '/pages/user/Order',
    })
  }, 
  coupon: function () {
    wx.navigateTo({
      url: '/pages/user/Coupon',
    })
  },
})