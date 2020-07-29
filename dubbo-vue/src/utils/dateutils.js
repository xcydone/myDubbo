export function formatDate(dt,fmt) {
  if (dt == null || dt == undefined) return '- -'
  if (new Date(dt)) {
    let newDate = new Date(dt)
    let ret;
    let opt = {
      "y+": newDate.getFullYear().toString(),        // 年
      "M+": (newDate.getMonth() + 1).toString(),     // 月
      "d+": newDate.getDate().toString(),            // 日
      "H+": newDate.getHours().toString(),           // 时
      "m+": newDate.getMinutes().toString(),         // 分
      "S+": newDate.getSeconds().toString()          // 秒
      // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
      ret = new RegExp("(" + k + ")").exec(fmt);
      if (ret) {
        fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
      };
    };
    return fmt;
  } else if (dt instanceof Date) {
    let ret;
    let opt = {
      "y+": dt.getFullYear().toString(),        // 年
      "M+": (dt.getMonth() + 1).toString(),     // 月
      "d+": dt.getDate().toString(),            // 日
      "H+": dt.getHours().toString(),           // 时
      "m+": dt.getMinutes().toString(),         // 分
      "S+": dt.getSeconds().toString()          // 秒
      // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (let k in opt) {
      ret = new RegExp("(" + k + ")").exec(fmt);
      if (ret) {
        fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
      };
    };
    return fmt;
  }
  return '- -'
}
