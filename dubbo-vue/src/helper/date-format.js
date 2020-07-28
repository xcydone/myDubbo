import moment from 'moment'

/**
 * 非表格数据日期格式化
 * @param val
 * @param formatString
 * @returns {string}
 */
export function format(val, formatString = 'YYYY-MM-DD HH:mm:ss') {
  if (moment(val).isValid()) {
    return moment(val).format(formatString)
  }
  return '- -'
}

/**
 * 用于表格数据日期格式化
 * @param formatString
 * @returns {function(*, *, *=)}
 */
export function formatInTable(formatString = 'YYYY-MM-DD HH:mm:ss') {
  return (row, col, val) => {
    if (moment(val).isValid()) {
      return moment(val).format(formatString)
    }
    return '- -'
  }
}
