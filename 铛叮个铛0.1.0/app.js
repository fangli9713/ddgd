App({
 

  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    var that = this;
    wx.login({
      success: wxLoginRes => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        console.log(wxLoginRes);
      //拿到code 调用后台的登录接口 换取token
        var url = 'https://foundfun.fun/mini/public/login';
        var jsonString = { "code": wxLoginRes.code};
        wx.request({
          url: url,
          method: 'POST',
          data: jsonString,
          dataType: 'json',
          success: function (res) {
            console.log(res.data.data.token)
           // wx.hideLoading()
            if (res.statusCode == 200 && res.data.resp_code == 0) {
              that.globalData.token = res.data.data.token;
              console.log("token=" + that.globalData.token)
            } else {
              if (res.data && res.data.resp_des) {
                wx.showToast({
                  title: res.data.resp_des,
                  icon: 'none',
                  duration: 2000
                })
                
              } else {
                wx.showToast({
                  title: '请求失败，请您稍后重试.',
                  icon: 'none',
                  duration: 2000
                })
              }
             
            }

          },
          fail: function (res) {
            wx.hideLoading()
            fail()
          },
          complete: function (res) {

          }
        })
      }
    })
    // 获取用户信息
    // wx.getSetting({
    //   success: res => {
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
    //       wx.getUserInfo({
    //         success: res => {
    //           // 可以将 res 发送给后台解码出 unionId
    //           this.globalData.userInfo = res.userInfo

    //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //           // 所以此处加入 callback 以防止这种情况
    //           if (this.userInfoReadyCallback) {
    //             this.userInfoReadyCallback(res)
    //           }
    //         }
    //       })
    //     }
    //   }
    // })
  },
  globalData: {
    userInfo: null,
    baseUrl:"https://foundfun.fun",
    token:null,
    interfaceUrl:{
      login: "/mini/public/login", 
      index:"/mini/private/index"

    }



  }
})

///** 本APP内标准绿色 #2E8B57 **/