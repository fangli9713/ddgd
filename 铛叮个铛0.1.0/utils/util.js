const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}
//封装网络请求
function sendPost(urlKey, jsonString, success, fail, re) {
  var app = getApp();
  var url = app.globalData.baseUrl + app.globalData.interfaceUrl[urlKey];
  wx.request({
    url: url,
    method: 'POST',
    data: jsonString,
    dataType: 'json',
    success: function (res) {

      //console.log(res.data)
      wx.hideLoading()
      if (res.statusCode == 200 && res.data.resp_code == 0) {
        success(res.data);
        re.setData({
          disabled: false
        })
      } else {
        if (res.data && res.data.resp_des) {
          wx.showToast({
            title: res.data.resp_des,
            icon: 'none',
            duration: 2000
          })
          fail(res.data)
        } else {
          wx.showToast({
            title: '请求失败，请您稍后重试.',
            icon: 'none',
            duration: 2000
          })
        }
        re.setData({
          disabled: false
        })
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



module.exports = {
  formatTime: formatTime,
  sendPost: sendPost  
}
