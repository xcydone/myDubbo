const modulesFiles = require.context('./modules', true, /index.js$/)
let modules = {}
modulesFiles.keys().forEach(item => {
  modules = Object.assign({}, modules, modulesFiles(item).default)
})
// Object.assign 复制多个目标的内容到目标对象中，返回目标对象

/*// 同样的实现，都是返回一个modules，里面存放路径值 reduce累加器，每个元素都执行
const modules2 = modulesFiles.keys().reduce((modules,modulePath) => {
  const value = modulesFiles(modulePath)
  modules.push(value.default)
  return modules;
}, [])*/

export default modules
